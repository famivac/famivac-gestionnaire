package fr.famivac.gestionnaire.domains.enfants.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

/**
 * @author paoesco
 */
@Embeddable
public class InformationsComplementairesEnfant implements Serializable {

  private Boolean attestationCMU;

  private Boolean carteVitale;

  private String contactUrgence;

  private String telephoneUrgence;

  private Boolean enuretique;

  private Boolean familleASE;

  public InformationsComplementairesEnfant() {
    this.attestationCMU = false;
    this.carteVitale = false;
    this.enuretique = false;
    this.familleASE = false;
  }

  public Boolean getAttestationCMU() {
    return attestationCMU;
  }

  public void setAttestationCMU(Boolean attestationCMU) {
    this.attestationCMU = attestationCMU;
  }

  public Boolean getCarteVitale() {
    return carteVitale;
  }

  public void setCarteVitale(Boolean carteVitale) {
    this.carteVitale = carteVitale;
  }

  public String getContactUrgence() {
    return contactUrgence;
  }

  public void setContactUrgence(String contactUrgence) {
    this.contactUrgence = contactUrgence;
  }

  public String getTelephoneUrgence() {
    return telephoneUrgence;
  }

  public void setTelephoneUrgence(String telephoneUrgence) {
    this.telephoneUrgence = telephoneUrgence;
  }

  public Boolean getEnuretique() {
    return enuretique;
  }

  public void setEnuretique(Boolean enuretique) {
    this.enuretique = enuretique;
  }

  public Boolean getFamilleASE() {
    return familleASE;
  }

  public void setFamilleASE(Boolean familleASE) {
    this.familleASE = familleASE;
  }

}
