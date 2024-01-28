package fr.famivac.gestionnaire.domains.sejours.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Voyage.QUERY_VOYAGES_PROCHAINS, query = "select v from Voyage v where v.dateVoyage >= :date order by v.dateVoyage")
})
public class Voyage implements Serializable {

  public static final String QUERY_VOYAGES_PROCHAINS = "queryVoyagesProchains";

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne(mappedBy = "aller")
  private Sejour sejourAller;
  @OneToOne(mappedBy = "retour")
  private Sejour sejourRetour;
  @Temporal(TemporalType.DATE)
  private Date dateVoyage;
  @Temporal(TemporalType.TIME)
  private Date heureDepart;
  private String lieuDepart;
  @Column(name = "NOM_PERSONNE_DEPART")
  private String nomPersonneDepart;
  @Column(name = "TELEPHONE_PERSONNE_DEPART")
  private String telephonePersonneDepart;
  @Enumerated(EnumType.STRING)
  private Transport transport;
  private String lieuRendezVous;
  private String lieuArrivee;
  @Temporal(TemporalType.TIME)
  private Date heureArrivee;
  private String nomPersonneAReception;
  private String telephonePersonneAReception;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "VOYAGE_ACCOMPAGNATEUR", joinColumns = @JoinColumn(name = "VOYAGE_ID"), inverseJoinColumns = @JoinColumn(name = "ACCOMPAGNATEUR_ID"))
  private Set<Accompagnateur> accompagnateurs;
  @Column(name = "NUMERO_TRAIN")
  private String numeroTrain;

  public Voyage() {
    this.accompagnateurs = new HashSet<>();
  }

  public Long getId() {
    return id;
  }

  public Date getDateVoyage() {
    return Objects.isNull(dateVoyage) ? null : (Date) dateVoyage.clone();
  }

  public void setDateVoyage(Date dateVoyage) {
    this.dateVoyage = Objects.isNull(dateVoyage) ? null : (Date) dateVoyage.clone();
  }

  public Date getHeureDepart() {
    return Objects.isNull(heureDepart) ? null : (Date) heureDepart.clone();
  }

  public void setHeureDepart(Date heureDepart) {
    this.heureDepart = Objects.isNull(heureDepart) ? null : (Date) heureDepart.clone();
  }

  public Transport getTransport() {
    return transport;
  }

  public void setTransport(Transport transport) {
    this.transport = transport;
  }

  public String getLieuRendezVous() {
    return lieuRendezVous;
  }

  public void setLieuRendezVous(String lieuRendezVous) {
    this.lieuRendezVous = lieuRendezVous;
  }

  public String getLieuArrivee() {
    return lieuArrivee;
  }

  public void setLieuArrivee(String lieuArrivee) {
    this.lieuArrivee = lieuArrivee;
  }

  public Date getHeureArrivee() {
    return Objects.isNull(heureArrivee) ? null : (Date) heureArrivee.clone();
  }

  public void setHeureArrivee(Date heureArrivee) {
    this.heureArrivee = Objects.isNull(heureArrivee) ? null : (Date) heureArrivee.clone();
  }

  public String getNomPersonneAReception() {
    return nomPersonneAReception;
  }

  public void setNomPersonneAReception(String nomPersonneAReception) {
    this.nomPersonneAReception = nomPersonneAReception;
  }

  public String getTelephonePersonneAReception() {
    return telephonePersonneAReception;
  }

  public void setTelephonePersonneAReception(String telephonePersonneAReception) {
    this.telephonePersonneAReception = telephonePersonneAReception;
  }

  public String getLieuDepart() {
    return lieuDepart;
  }

  public void setLieuDepart(String lieuDepart) {
    this.lieuDepart = lieuDepart;
  }

  public Set<Accompagnateur> getAccompagnateurs() {
    return accompagnateurs;
  }

  public void setAccompagnateurs(Set<Accompagnateur> accompagnateurs) {
    this.accompagnateurs = accompagnateurs;
  }

  public Sejour getSejour() {
    return sejourRetour == null ? sejourAller : sejourRetour;
  }

  public String getNomPersonneDepart() {
    return nomPersonneDepart;
  }

  public void setNomPersonneDepart(String nomPersonneDepart) {
    this.nomPersonneDepart = nomPersonneDepart;
  }

  public String getTelephonePersonneDepart() {
    return telephonePersonneDepart;
  }

  public void setTelephonePersonneDepart(String telephonePersonneDepart) {
    this.telephonePersonneDepart = telephonePersonneDepart;
  }

  public String getNumeroTrain() {
    return numeroTrain;
  }

  public void setNumeroTrain(String numeroTrain) {
    this.numeroTrain = numeroTrain;
  }

  public Boolean getRetour() {
    return sejourRetour != null;
  }

}
