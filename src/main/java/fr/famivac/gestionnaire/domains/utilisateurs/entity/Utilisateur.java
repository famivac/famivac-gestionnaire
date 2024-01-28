package fr.famivac.gestionnaire.domains.utilisateurs.entity;

import fr.famivac.gestionnaire.commons.utils.Email;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Utilisateur.QUERY_LISTE_ALL, query = "select u from Utilisateur u"),})
public class Utilisateur implements Serializable {

  public static final String QUERY_LISTE_ALL = "retrieveUtilisateurs";
  public static final Utilisateur fakeUtilisateur = new Utilisateur();
  private static final long serialVersionUID = -2714545423052661888L;
  @Id
  private String login;

  @Column(length = 2000, nullable = false)
  @Email
  private String email;

  @Column(length = 2000, nullable = false)
  private String password;

  @Column(nullable = false)
  private String nom;

  @Column(nullable = false)
  private String prenom;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Groupe> groupes;

  private boolean enabled;

  protected Utilisateur() {
    groupes = new HashSet<>();
  }

  public Utilisateur(String login, String email, String password, Set<Groupe> groupes, String nom,
      String prenom) {
    this.login = login.trim().toLowerCase();
    this.email = email.trim();
    this.password = password;
    this.groupes = new HashSet<>(groupes);
    this.nom = nom;
    this.prenom = prenom;
    this.enabled = true;
  }

  public String getLogin() {
    return login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Set<Groupe> getGroupes() {
    return Collections.unmodifiableSet(groupes);
  }

  public void setGroupes(Set<Groupe> groupes) {
    this.groupes = new HashSet<>(groupes);
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
