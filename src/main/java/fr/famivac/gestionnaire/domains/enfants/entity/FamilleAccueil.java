package fr.famivac.gestionnaire.domains.enfants.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.commons.entity.Coordonnees;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author paoesco
 */
@Entity
public class FamilleAccueil implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private String nom;
  private String prenom;
  private Adresse adresse;
  private Coordonnees coordonnees;

  public FamilleAccueil() {
    adresse = new Adresse();
    coordonnees = new Coordonnees();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Coordonnees getCoordonnees() {
    return coordonnees;
  }

  public void setCoordonnees(Coordonnees coordonnees) {
    this.coordonnees = coordonnees;
  }

}
