package fr.famivac.gestionnaire.domains.enfants.entity;

import fr.famivac.gestionnaire.commons.entity.Coordonnees;
import java.io.Serializable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * @author paoesco
 */
@Entity
public class ResponsableInscripteur implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private String nom;

  private String prenom;

  @Embedded
  private Coordonnees coordonnees;

  @OneToOne(mappedBy = "responsable")
  private Inscripteur inscripteur;

  public ResponsableInscripteur() {
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

  public Coordonnees getCoordonnees() {
    return coordonnees;
  }

  public void setCoordonnees(Coordonnees coordonnees) {
    this.coordonnees = coordonnees;
  }

  public Inscripteur getInscripteur() {
    return inscripteur;
  }

  public void setInscripteur(Inscripteur inscripteur) {
    this.inscripteur = inscripteur;
  }

}
