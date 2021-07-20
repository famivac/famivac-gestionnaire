package fr.famivac.gestionnaire.domains.enfants.boundary;

import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;
import fr.famivac.gestionnaire.domains.enfants.entity.Inscripteur;
import fr.famivac.gestionnaire.domains.enfants.entity.InscripteurRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import net.bull.javamelody.MonitoringInterceptor;

/** @author paoesco */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class InscripteurService {

  @Inject private EntityManager entityManager;

  @Inject private InscripteurRepository inscripteurRepository;

  public List<RetrieveInscripteursResponseDTO> retrieve() {
    return entityManager
        .createNamedQuery(Inscripteur.QUERY_RETRIEVE_ALL, Inscripteur.class)
        .getResultList()
        .stream()
        .map(
            (Inscripteur entity) -> {
              RetrieveInscripteursResponseDTO dto = new RetrieveInscripteursResponseDTO();
              dto.setId(entity.getId());
              dto.setNom(entity.getNom());
              dto.setPrenom(entity.getPrenom());
              dto.setOrganisme(entity.getOrganisme());
              dto.setType(entity.getType());
              return dto;
            })
        .collect(Collectors.toList());
  }

  public List<RetrieveInscripteursResponseDTO> search(String nom, String prenom, String organisme) {
    if ((nom == null || nom.isEmpty())
        && (prenom == null || prenom.isEmpty())
        && (organisme == null || organisme.isEmpty())) {
      return retrieve();
    }
    return inscripteurRepository.search(nom, prenom, organisme).stream()
        .map(
            (Inscripteur entity) -> {
              RetrieveInscripteursResponseDTO dto = new RetrieveInscripteursResponseDTO();
              dto.setId(entity.getId());
              dto.setNom(entity.getNom());
              dto.setPrenom(entity.getPrenom());
              dto.setOrganisme(entity.getOrganisme());
              dto.setType(entity.getType());
              return dto;
            })
        .collect(Collectors.toList());
  }

  public Long create(Inscripteur entity) {
    entityManager.persist(entity);
    return entity.getId();
  }

  public void delete(Long id) {
    Inscripteur entity = entityManager.find(Inscripteur.class, id);
    if (entity == null) {
      throw new IllegalArgumentException("L'inscripteur n'existe pas");
    }
    entityManager.remove(entity);
  }

  public Inscripteur retrieve(Long id) {
    return entityManager.find(Inscripteur.class, id);
  }

  public void update(Inscripteur entity) {
    entityManager.merge(entity);
  }

  public void retirerEnfant(Long enfantId) {
    Enfant enfant = entityManager.find(Enfant.class, enfantId);
    enfant.getInscripteur().retirerEnfant(enfant);
    entityManager.remove(enfant);
  }
}
