package fr.famivac.gestionnaire.domains.enfants.entity;

import fr.famivac.gestionnaire.commons.entity.Sexe;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * @author paoesco
 */
@Entity
public class Enfant implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private String nom;

  private String prenom;

  private Boolean inscripteurEstResponsableLegal;

  @Enumerated(EnumType.STRING)
  private Sexe sexe;

  @Temporal(TemporalType.DATE)
  private Date dateNaissance;

  private String classeFrequentee;

  @ManyToOne
  private Inscripteur inscripteur;

  @OneToOne(cascade = CascadeType.ALL)
  private ResponsableLegal responsableLegal;

  @Embedded
  private InformationsComplementairesEnfant informationsComplementairesEnfant;

  @OneToOne(cascade = CascadeType.ALL)
  private FamilleAccueil familleAccueil;

  @Column(length = 2000)
  private String remarque;

  public Enfant() {
    inscripteur = new Inscripteur();
    responsableLegal = new ResponsableLegal();
    informationsComplementairesEnfant = new InformationsComplementairesEnfant();
  }

  public Long getId() {
    return id;
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

  public Sexe getSexe() {
    return sexe;
  }

  public void setSexe(Sexe sexe) {
    this.sexe = sexe;
  }

  public Date getDateNaissance() {
    return dateNaissance == null ? null : (Date) dateNaissance.clone();
  }

  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = (dateNaissance == null ? null : (Date) dateNaissance.clone());
  }

  public String getClasseFrequentee() {
    return classeFrequentee;
  }

  public void setClasseFrequentee(String classeFrequentee) {
    this.classeFrequentee = classeFrequentee;
  }

  public Inscripteur getInscripteur() {
    return inscripteur;
  }

  public void setInscripteur(Inscripteur inscripteur) {
    this.inscripteur = inscripteur;
  }

  public ResponsableLegal getResponsableLegal() {
    return responsableLegal;
  }

  public void setResponsableLegal(ResponsableLegal responsableLegal) {
    this.responsableLegal = responsableLegal;
  }

  public InformationsComplementairesEnfant getInformationsComplementairesEnfant() {
    return informationsComplementairesEnfant;
  }

  public String getRemarque() {
    return remarque;
  }

  public void setRemarque(String remarque) {
    this.remarque = remarque;
  }

  public Boolean getInscripteurEstResponsableLegal() {
    return inscripteurEstResponsableLegal;
  }

  public void setInscripteurEstResponsableLegal(Boolean inscripteurEstResponsableLegal) {
    this.inscripteurEstResponsableLegal = inscripteurEstResponsableLegal;
  }

  public FamilleAccueil getFamilleAccueil() {
    return familleAccueil;
  }

  public void setFamilleAccueil(FamilleAccueil familleAccueil) {
    this.familleAccueil = familleAccueil;
  }

}
