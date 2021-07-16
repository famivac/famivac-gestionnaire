package fr.famivac.gestionnaire.domains.enfants.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.commons.entity.Coordonnees;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** @author paoesco */
@Entity
public class ResponsableLegal implements Serializable {

  @Id @GeneratedValue private Long id;

  @Enumerated(EnumType.STRING)
  private TypeInscripteur type;

  private String nom;

  private String prenom;

  private String organisme;

  @Embedded private Adresse adresse;

  private String lienDeParente;

  @Embedded private Coordonnees coordonnees;

  public ResponsableLegal() {
    adresse = new Adresse();
    coordonnees = new Coordonnees();
  }

  public ResponsableLegal(Inscripteur inscripteur) {
    type = inscripteur.getType();
    nom = inscripteur.getNom();
    prenom = inscripteur.getPrenom();
    adresse = inscripteur.getAdresse();
    organisme = inscripteur.getOrganisme();
    coordonnees = inscripteur.getCoordonnees();
  }

  public Long getId() {
    return id;
  }

  public TypeInscripteur getType() {
    return type;
  }

  public void setType(TypeInscripteur type) {
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

  public String getOrganisme() {
    return organisme;
  }

  public void setOrganisme(String organisme) {
    this.organisme = organisme;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  public String getLienDeParente() {
    return lienDeParente;
  }

  public void setLienDeParente(String lienDeParente) {
    this.lienDeParente = lienDeParente;
  }

  public Coordonnees getCoordonnees() {
    return coordonnees;
  }

  public void setCoordonnees(Coordonnees coordonnees) {
    if (Objects.isNull(coordonnees)) {
      this.coordonnees = new Coordonnees();
    } else {
      this.coordonnees = coordonnees;
    }
  }
}
