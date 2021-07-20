package fr.famivac.gestionnaire.domains.familles.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INFORMATIONS_HABITATION")
@Getter
@Setter
public class InformationsHabitation implements Serializable {

  @Id @GeneratedValue private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FAMILLE_ID", nullable = false)
  private Famille famille;

  @Column(name = "TYPE_HABITATION")
  @Enumerated(EnumType.STRING)
  private TypeHabitation typeHabitation;

  @Column(name = "SITUATION_HABITATION")
  @Enumerated(EnumType.STRING)
  private SituationHabitation situationHabitation;

  @Column(name = "VILLE_LA_PLUS_PROCHE")
  private String villeLaPlusProche;

  @Column(name = "JARDIN_ESCPACE_JEU")
  private Boolean jardinEspaceJeu = false;

  @Column(name = "PISCINE")
  private Boolean piscine = false;

  @Column(name = "PISCINE_SECURISE")
  private Boolean piscineSecurisee;

  @Column(name = "NOMBRE_CHIENS")
  private Integer nombreChiens = 0;

  @Column(name = "NOMBRE_CHATS")
  private Integer nombreChats = 0;

  @Column(name = "AUTRES_ANIMAUX", length = 1000)
  private String autresAnimaux;

  @Column(name = "POINT_VIGILANCE", length = 1000)
  private String pointVigilance;

  protected InformationsHabitation() {}

  public InformationsHabitation(Famille famille) {
    this.famille = famille;
  }
}
