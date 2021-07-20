package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.domains.familles.entity.Famille;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/** @author paoesco */
public class FamilleDTO implements Serializable {

  private Long id;

  private String nomReferent;

  private String prenomReferent;

  private String telephoneReferent;

  private String emailReferent;

  private Boolean radiee;

  private Boolean candidature;

  private Boolean archivee;

  public FamilleDTO(Famille entity) {
    id = entity.getId();
    nomReferent = entity.getMembreReferent().getNom();
    prenomReferent = entity.getMembreReferent().getPrenom();
    if (entity.getMembreReferent().getCoordonnees() != null) {
      telephoneReferent = entity.getMembreReferent().getCoordonnees().getTelephone1();
      emailReferent = entity.getMembreReferent().getCoordonnees().getEmail();
    }
    radiee = entity.getRadiee();
    candidature = entity.getCandidature();
    archivee = entity.getArchivee();
  }

  public FamilleDTO(
      Long id,
      String nomReferent,
      String prenomReferent,
      String telephoneReferent,
      String emailReferent,
      Date dateRadiation,
      Boolean candidature,
      Boolean archivee) {
    this.id = id;
    this.nomReferent = nomReferent;
    this.prenomReferent = prenomReferent;
    this.telephoneReferent = telephoneReferent;
    this.emailReferent = emailReferent;
    radiee = Objects.nonNull(dateRadiation);
    this.archivee = archivee;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getTelephoneReferent() {
    return telephoneReferent;
  }

  public void setTelephoneReferent(String telephoneReferent) {
    this.telephoneReferent = telephoneReferent;
  }

  public String getEmailReferent() {
    return emailReferent;
  }

  public void setEmailReferent(String emailReferent) {
    this.emailReferent = emailReferent;
  }

  public Boolean getRadiee() {
    return radiee;
  }

  public void setRadiee(Boolean radiee) {
    this.radiee = radiee;
  }

  public Boolean getCandidature() {
    return candidature;
  }

  public void setCandidature(Boolean candidature) {
    this.candidature = candidature;
  }

  public Boolean getArchivee() {
    return archivee;
  }

  public void setArchivee(Boolean archivee) {
    this.archivee = archivee;
  }
}
