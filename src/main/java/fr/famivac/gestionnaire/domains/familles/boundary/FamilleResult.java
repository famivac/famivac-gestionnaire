package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.domains.familles.entity.Famille;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilleResult implements Serializable {

  private Long id;

  private String nomReferent;

  private String prenomReferent;

  private String telephoneReferent;

  private String emailReferent;

  private Boolean radiee;

  private Boolean candidature;

  private Boolean archivee;

  public FamilleResult(Famille entity) {
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
}
