package fr.famivac.gestionnaire.web.sejours;

import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class RechercherSejoursForm implements Serializable {

  private String nomReferent;

  private String prenomReferent;

  private String nomEnfant;

  private String prenomEnfant;

  private String statutSejour;

  public String getNomReferent() {
    return nomReferent;
  }

  public void setNomReferent(String nomReferent) {
    this.nomReferent = nomReferent;
  }

  public String getPrenomReferent() {
    return prenomReferent;
  }

  public void setPrenomReferent(String prenomReferent) {
    this.prenomReferent = prenomReferent;
  }

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

  public String getStatutSejour() {
    return statutSejour;
  }

  public void setStatutSejour(String statutSejour) {
    this.statutSejour = statutSejour;
  }

}
