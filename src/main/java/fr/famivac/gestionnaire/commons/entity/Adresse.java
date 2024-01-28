package fr.famivac.gestionnaire.commons.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

/**
 * Conserver l'historique des adresses.
 *
 * @author paoesco
 */
@Embeddable
public class Adresse implements Serializable {

  @Column
  private String ligneAdresseUne;

  @Column
  private String ligneAdresseDeux;

  @ManyToOne
  private Commune commune;

  public Adresse() {
    this.commune = new Commune();
  }

  public Adresse(String ligneAdresseUne, String ligneAdresseDeux, Commune commune) {
    this.ligneAdresseUne = ligneAdresseUne;
    this.ligneAdresseDeux = ligneAdresseDeux;
    this.commune = commune;
  }

  public String getLigneAdresseUne() {
    return ligneAdresseUne;
  }

  public void setLigneAdresseUne(String ligneAdresseUne) {
    this.ligneAdresseUne = ligneAdresseUne;
  }

  public String getLigneAdresseDeux() {
    return ligneAdresseDeux;
  }

  public void setLigneAdresseDeux(String ligneAdresseDeux) {
    this.ligneAdresseDeux = ligneAdresseDeux;
  }

  public Commune getCommune() {
    return commune;
  }

  public void setCommune(Commune commune) {
    if (commune != null && (commune.getCode() == null || commune.getCode().isEmpty())) {
      throw new IllegalArgumentException("Le code commune est obligatoire");
    }
    this.commune = commune;
  }

}
