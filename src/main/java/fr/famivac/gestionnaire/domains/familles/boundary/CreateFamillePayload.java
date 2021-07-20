package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.commons.entity.Adresse;

public class CreateFamillePayload {

  private Adresse adresse;

  private String projet;

  private MembreDTO membrePrincipal;

  private Boolean candidature;

  public CreateFamillePayload() {
    adresse = new Adresse();
    membrePrincipal = new MembreDTO();
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  public String getProjet() {
    return projet;
  }

  public void setProjet(String projet) {
    this.projet = projet;
  }

  public MembreDTO getMembrePrincipal() {
    return membrePrincipal;
  }

  public void setMembrePrincipal(MembreDTO membrePrincipal) {
    this.membrePrincipal = membrePrincipal;
  }

  public Boolean getCandidature() {
    return candidature;
  }

  public void setCandidature(Boolean candidature) {
    this.candidature = candidature;
  }
}
