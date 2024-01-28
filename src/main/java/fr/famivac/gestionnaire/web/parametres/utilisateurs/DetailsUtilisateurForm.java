package fr.famivac.gestionnaire.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.commons.utils.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author paoesco
 */
public class DetailsUtilisateurForm {

  @NotNull
  private String login;

  @Size(max = 255)
  private String password;

  @Size(max = 255)
  private String confirmPassword;

  @NotNull
  @Size(min = 1, max = 255)
  private String nom;

  @NotNull
  @Size(min = 1, max = 255)
  private String prenom;

  @NotNull
  @Size(min = 1, max = 255)
  private String groupe;

  @NotNull
  @Email
  @Size(min = 1, max = 2000)
  private String email;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
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

  public String getGroupe() {
    return groupe;
  }

  public void setGroupe(String groupe) {
    this.groupe = groupe;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

}
