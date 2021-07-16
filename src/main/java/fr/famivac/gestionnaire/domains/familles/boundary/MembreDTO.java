package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.commons.entity.Coordonnees;
import java.util.Date;
import java.util.Objects;

public class MembreDTO {

  private Long id;

  private String nom;

  private String nomDeNaissance;

  private String prenom;

  private String sexe;

  private Date dateNaissance;

  private Boolean referent;

  private Commune communeDeNaissance;

  private String profession;

  private String lienDeParente;

  private Coordonnees coordonnees;

  public MembreDTO() {
    communeDeNaissance = new Commune();
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

  public String getNomDeNaissance() {
    return nomDeNaissance;
  }

  public void setNomDeNaissance(String nomDeNaissance) {
    this.nomDeNaissance = nomDeNaissance;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getSexe() {
    return sexe;
  }

  public void setSexe(String sexe) {
    this.sexe = sexe;
  }

  public Date getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public Boolean getReferent() {
    return referent;
  }

  public void setReferent(Boolean referent) {
    this.referent = referent;
  }

  public Commune getCommuneDeNaissance() {
    return communeDeNaissance;
  }

  public void setCommuneDeNaissance(Commune communeDeNaissance) {
    this.communeDeNaissance = communeDeNaissance;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
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
