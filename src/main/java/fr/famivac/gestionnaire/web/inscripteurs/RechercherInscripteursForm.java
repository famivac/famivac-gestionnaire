package fr.famivac.gestionnaire.web.inscripteurs;

import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class RechercherInscripteursForm implements Serializable {

  private String nom;

  private String prenom;

  private String organisme;

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

  public String getOrganisme() {
    return organisme;
  }

  public void setOrganisme(String organisme) {
    this.organisme = organisme;
  }

}
