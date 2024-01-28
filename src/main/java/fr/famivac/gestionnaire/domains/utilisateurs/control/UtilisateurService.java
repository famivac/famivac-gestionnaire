package fr.famivac.gestionnaire.domains.utilisateurs.control;

import fr.famivac.gestionnaire.domains.utilisateurs.entity.Groupe;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Utilisateur;
import fr.famivac.gestionnaire.commons.exceptions.WrongCredentialsException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author paoesco
 */
@Stateless
public class UtilisateurService {

  @Inject
  private EntityManager entityManager;

  public String create(AjouterUtilisateurDTO dto) {
    String password = generatePassword();
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Set<Groupe> groupes = new HashSet<>(dto.getGroupes());
    Utilisateur entity = new Utilisateur(
        dto.getLogin(),
        dto.getEmail(),
        passwordEncoder.encode(password),
        groupes,
        dto.getNom(),
        dto.getPrenom());
    entityManager.persist(entity);
    return password;
  }

  public Utilisateur login(String login, String password) throws WrongCredentialsException {
    Utilisateur entity = entityManager.find(Utilisateur.class, login);
    if (Objects.isNull(entity) || !entity.isEnabled()) {
      throw new WrongCredentialsException();
    }
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (!passwordEncoder.matches(password, entity.getPassword())) {
      throw new WrongCredentialsException();
    }
    return entity;
  }

  public Utilisateur retrieve(String login) {
    Utilisateur entity = entityManager.find(Utilisateur.class, login);
    return entity;
  }

  public List<RetrieveUtilisateursDTO> retrieve() {
    List<Utilisateur> entities = entityManager.createNamedQuery(Utilisateur.QUERY_LISTE_ALL,
        Utilisateur.class).getResultList();
    return entities.stream().map(entity -> {
      RetrieveUtilisateursDTO dto = new RetrieveUtilisateursDTO();
      dto.setLogin(entity.getLogin());
      dto.setNom(entity.getNom());
      dto.setPrenom(entity.getPrenom());
      dto.setGroupes(new ArrayList<>(entity.getGroupes()));
      dto.setEmail(entity.getEmail());
      dto.setEnabled(entity.isEnabled());
      return dto;
    }).collect(Collectors.toList());
  }

  public void update(Utilisateur entity) {
    entityManager.merge(entity);
  }

  public void delete(String login) {
    Utilisateur bean = entityManager.find(Utilisateur.class, login);
    entityManager.remove(bean);
  }

  public void changePassword(String login, String actualPassword, String newPassword)
      throws WrongCredentialsException {
    Utilisateur entity = entityManager.find(Utilisateur.class, login);
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (!passwordEncoder.matches(actualPassword, entity.getPassword())) {
      throw new WrongCredentialsException();
    }
    entity.setPassword(passwordEncoder.encode(newPassword));
  }

  public String reinitPassword(String login) {
    String password = generatePassword();
    Utilisateur entity = entityManager.find(Utilisateur.class, login);
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    entity.setPassword(passwordEncoder.encode(password));
    return password;
  }

  private String generatePassword() {
    return UUID.randomUUID().toString().substring(0, 8);
  }

  public void lock(String login) {
    Utilisateur entity = entityManager.find(Utilisateur.class, login);
    entity.setEnabled(false);

  }

  public void unlock(String login) {
    Utilisateur entity = entityManager.find(Utilisateur.class, login);
    entity.setEnabled(true);
  }

}
