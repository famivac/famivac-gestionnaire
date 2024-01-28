package fr.famivac.gestionnaire.domains.sejours.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import java.io.Serializable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * @author paoesco
 */
@Entity
public class Payeur implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private TypePayeur type;

  private String nom;

  private String prenom;

  @Embedded
  private Adresse adresse;

  @ManyToOne
  private Sejour sejour;

  public Payeur() {
    adresse = new Adresse();
  }

  public Long getId() {
    return id;
  }

  public TypePayeur getType() {
    return type;
  }

  public void setType(TypePayeur type) {
    this.type = type;
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

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  public Sejour getSejour() {
    return sejour;
  }

  public void setSejour(Sejour sejour) {
    this.sejour = sejour;
  }

}
