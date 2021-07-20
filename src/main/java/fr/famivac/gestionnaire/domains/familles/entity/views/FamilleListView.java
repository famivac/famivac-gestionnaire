package fr.famivac.gestionnaire.domains.familles.entity.views;

import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilleListView {

  private Long id;

  private String nomReferent;

  private String prenomReferent;

  private String telephoneReferent;

  private String emailReferent;

  private Boolean radiee;

  private Boolean candidature;

  private Boolean archivee;

  public FamilleListView(
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
    this.candidature = candidature;
    this.archivee = archivee;
  }
}
