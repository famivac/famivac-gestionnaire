package fr.famivac.gestionnaire.domains.familles.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "INFORMATIONS_VEHICULE")
@Data
public class InformationsVehicule implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FAMILLE_ID", nullable = false)
  private Famille famille;

  @Column(name = "NOMBRE_VEHICULES")
  private Integer nombreVehicules;

  @Column(name = "TYPE_VEHICULE_1")
  private String typeVehicule1;

  @Column(name = "NOMBRE_PLACES_VEHICULE_1")
  private Integer nombrePlacesVehicule1;

  @Column(name = "CONDUCTEUR_PRINCIPAL_VEHICULE_1")
  private String conducteurPrincipalVehicule1;

  @Column(name = "TYPE_VEHICULE_2")
  private String typeVehicule2;

  @Column(name = "NOMBRE_PLACES_VEHICULE_2")
  private Integer nombrePlacesVehicule2;

  @Column(name = "CONDUCTEUR_PRINCIPAL_VEHICULE_2")
  private String conducteurPrincipalVehicule2;

  @Column(name = "ASSURANCE")
  private String assurance;

  @Column(name = "NOMBRE_REHAUSSEURS")
  private Integer nombreRehausseurs;

  @Column(name = "NOMBRE_SIEGES_ATUO_BEBE")
  private Integer nombreSiegesAutoBebe;

  protected InformationsVehicule() {}

  public InformationsVehicule(Famille famille) {
    this.famille = famille;
  }
}
