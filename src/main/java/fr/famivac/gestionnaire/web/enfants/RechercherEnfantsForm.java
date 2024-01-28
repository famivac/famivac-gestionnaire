package fr.famivac.gestionnaire.web.enfants;

import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class RechercherEnfantsForm implements Serializable {

  private String nomEnfant;

  private String prenomEnfant;

  public String getNomEnfant() {
    return nomEnfant;
  }

  public void setNomEnfant(String nomEnfant) {
    this.nomEnfant = nomEnfant;
  }

  public String getPrenomEnfant() {
    return prenomEnfant;
  }

  public void setPrenomEnfant(String prenomEnfant) {
    this.prenomEnfant = prenomEnfant;
  }

}
