package fr.famivac.gestionnaire.domains.sejours.entity;

import fr.famivac.gestionnaire.commons.entity.BaseEntity;
import fr.famivac.gestionnaire.commons.utils.Email;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/** @author paoesco */
@Entity
@Table(name = "ACCOMPAGNATEUR")
@NamedQueries({
  @NamedQuery(
      name = Accompagnateur.QUERY_GET_ALL,
      query =
          "select acc from Accompagnateur acc where acc.deletedAt is null order by acc.nom, acc.prenom"),
  @NamedQuery(
      name = Accompagnateur.QUERY_RECHERCHER,
      query =
          "select acc from Accompagnateur acc where acc.deletedAt is null and lower(acc.nom) like :nom or lower(acc.prenom) like :prenom order by acc.nom, acc.prenom")
})
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Accompagnateur extends BaseEntity {

  public static final String QUERY_GET_ALL = "queryAccompagnateurGetAll";
  public static final String QUERY_RECHERCHER = "queryAccompagnateurRechercher";

  @Column(name = "NOM")
  private String nom;

  @Column(name = "PRENOM")
  private String prenom;

  @Column(name = "TELEPHONE")
  private String telephone;

  @Column(name = "EMAIL")
  @Email
  private String email;

  @Column(name = "deleted_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletedAt;
}
