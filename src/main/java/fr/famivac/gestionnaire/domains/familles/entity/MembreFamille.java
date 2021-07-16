package fr.famivac.gestionnaire.domains.familles.entity;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.commons.entity.Coordonnees;
import fr.famivac.gestionnaire.commons.entity.Sexe;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Personnes composants la famille.
 *
 * @author paoesco
 */
@Entity
public class MembreFamille implements Serializable {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String nom;

  private String nomDeNaissance;

  @Column(nullable = false)
  private String prenom;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Sexe sexe;

  @Column
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date dateNaissance;

  @Column(nullable = false)
  private Boolean referent;

  @ManyToOne @JoinColumn private Commune communeDeNaissance;

  private String profession;

  private String lienDeParente;

  @Embedded private Coordonnees coordonnees;

  protected MembreFamille() {
    coordonnees = new Coordonnees();
  }

  public MembreFamille(
      String nom,
      String nomDeNaissance,
      String prenom,
      Sexe sexe,
      Date dateNaissance,
      String profession,
      Commune communeDeNaissance,
      Coordonnees coordonnees) {
    this(
        nom,
        nomDeNaissance,
        prenom,
        sexe,
        dateNaissance,
        profession,
        false,
        communeDeNaissance,
        coordonnees);
  }

  public MembreFamille(
      String nom,
      String nomDeNaissance,
      String prenom,
      Sexe sexe,
      Date dateNaissance,
      String profession,
      Boolean referent,
      Commune communeDeNaissance,
      Coordonnees coordonnees) {
    if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || sexe == null) {
      throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
    }
    this.nom = nom;
    this.nomDeNaissance = nomDeNaissance;
    this.prenom = prenom;
    this.sexe = sexe;
    setDateNaissance(dateNaissance);
    this.profession = profession;
    this.referent = referent;
    this.communeDeNaissance = communeDeNaissance;
    if (Objects.isNull(coordonnees)) {
      this.coordonnees = new Coordonnees();
    } else {
      this.coordonnees = coordonnees;
    }
  }

  public Long getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    if (nom == null || nom.isEmpty()) {
      throw new IllegalArgumentException("Le nom est obligatoire");
    }
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
    if (prenom == null || prenom.isEmpty()) {
      throw new IllegalArgumentException("Le prénom est obligatoire");
    }
    this.prenom = prenom;
  }

  public Sexe getSexe() {
    return sexe;
  }

  public void setSexe(Sexe sexe) {
    if (sexe == null) {
      throw new IllegalArgumentException("Le sexe est obligatoire");
    }
    this.sexe = sexe;
  }

  public Date getDateNaissance() {
    return dateNaissance == null ? null : (Date) dateNaissance.clone();
  }

  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance == null ? null : (Date) dateNaissance.clone();
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
    if (communeDeNaissance == null
        || communeDeNaissance.getCode() == null
        || communeDeNaissance.getCode().isEmpty()) {
      throw new IllegalArgumentException("La commune de naissance est obligatoire");
    }
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

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + Objects.hashCode(id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    MembreFamille other = (MembreFamille) obj;
    return Objects.equals(id, other.id);
  }
}
