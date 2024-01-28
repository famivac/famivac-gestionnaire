package fr.famivac.gestionnaire.domains.sejours.entity;

import fr.famivac.gestionnaire.commons.utils.DateUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Sejour.QUERY_SEJOURS_DE_LA_FAMILLE, query = "select s from Sejour s where s.familleId = :familleId order by s.dateDebut")
    ,
    @NamedQuery(name = Sejour.QUERY_SEJOURS_DE_L_ENFANT, query = "select s from Sejour s where s.enfantId = :enfantId order by s.dateDebut")
    ,
    @NamedQuery(name = Sejour.QUERY_SEJOURS_RETRIEVE, query = "select s from Sejour s order by s.dateDebut, s.dateFin")
    ,
    @NamedQuery(name = Sejour.QUERY_SEJOURS_TERMINES_DANS_LA_PERIODE, query = "select s from Sejour s where :dateDebut <= s.dateFin and s.dateFin <= :dateFin")
})
public class Sejour implements Serializable {

  public static final String QUERY_SEJOURS_DE_LA_FAMILLE = "querySejoursDeLaFamille";
  public static final String QUERY_SEJOURS_DE_L_ENFANT = "querySejoursDeLEnfant";
  public static final String QUERY_SEJOURS_RETRIEVE = "querySejoursRetrieve";
  public static final String QUERY_SEJOURS_RECHERCHER = "querySejoursRechercher";
  public static final String QUERY_SEJOURS_TERMINES_DANS_LA_PERIODE = "querySejoursTerminesDansLaPeriode";

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "famille_id", nullable = false)
  @NotNull
  private Long familleId;

  @Column(name = "FAMILLE_NOM", nullable = false)
  private String familleNom;

  @Column(name = "FAMILLE_PRENOM", nullable = false)
  private String famillePrenom;

  @Column(name = "enfant_id", nullable = false)
  @NotNull
  private Long enfantId;

  @Column(name = "ENFANT_NOM", nullable = false)
  private String enfantNom;

  @Column(name = "ENFANT_PRENOM", nullable = false)
  private String enfantPrenom;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Voyage aller;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Voyage retour;

  @Temporal(TemporalType.DATE)
  @NotNull
  private Date dateDebut;

  @Column(name = "PERIODE_JOURNEE_DATE_DEBUT")
  @Enumerated(EnumType.STRING)
  @NotNull
  private PeriodeJournee periodeJourneeDateDebut;

  @Temporal(TemporalType.DATE)
  @NotNull
  private Date dateFin;

  @Column(name = "PERIODE_JOURNEE_DATE_FIN")
  @Enumerated(EnumType.STRING)
  @NotNull
  private PeriodeJournee periodeJourneeDateFin;

  @Column(name = "FRAIS_SEJOUR_JOURNALIER")
  private BigDecimal forfaitJournalier;

  @Column(name = "FRAIS_DOSSIER")
  private BigDecimal fraisDossier;

  @Column(name = "FRAIS_PENSION_FAMILLE_JOURNALIER")
  private BigDecimal fraisPensionFamilleJournalier;

  @Column(name = "FRAIS_VOYAGE")
  private BigDecimal fraisVoyage;

  @Temporal(TemporalType.DATE)
  @Column(name = "DATE_ANNULATION")
  private Date dateAnnulation;
  @Column(length = 1000, name = "MOTIF_ANNULATION")
  private String motifAnnulation;
  @Column(length = 1000, name = "MOTIF_FIN_SEJOUR")
  private String motifFinSejour;

  @OneToMany(mappedBy = "sejour")
  private Set<Payeur> payeurs;

  protected Sejour() {
    this.forfaitJournalier = BigDecimal.ZERO;
    this.fraisDossier = BigDecimal.ZERO;
    this.fraisVoyage = BigDecimal.ZERO;
    this.fraisPensionFamilleJournalier = BigDecimal.ZERO;
    this.payeurs = new HashSet<>();
  }

  public Sejour(Date dateDebut, PeriodeJournee periodeJourneeDebut, Date dateFin,
      PeriodeJournee periodeJourneeFin) {
    this();
    if (Objects.isNull(dateDebut)
        || Objects.isNull(periodeJourneeDebut)
        || Objects.isNull(dateFin)
        || Objects.isNull(periodeJourneeFin)) {
      throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
    }
    this.dateDebut = (Date) dateDebut.clone();
    this.periodeJourneeDateDebut = periodeJourneeDebut;
    this.dateFin = (Date) dateFin.clone();
    this.periodeJourneeDateFin = periodeJourneeFin;
  }

  public Sejour(Long familleId,
      String familleNom,
      String famillePrenom,
      Long enfantId,
      String enfantNom,
      String enfantPrenom,
      Date dateDebut,
      PeriodeJournee periodeJourneeDebut,
      Date dateFin,
      PeriodeJournee periodeJourneeFin) {
    this();
    if (Objects.isNull(familleId)
        || Objects.isNull(familleNom)
        || Objects.isNull(famillePrenom)
        || Objects.isNull(enfantId)
        || Objects.isNull(enfantNom)
        || Objects.isNull(enfantPrenom)
        || Objects.isNull(dateDebut)
        || Objects.isNull(periodeJourneeDebut)
        || Objects.isNull(dateFin)
        || Objects.isNull(periodeJourneeFin)) {
      throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
    }
    this.familleId = familleId;
    this.familleNom = familleNom;
    this.famillePrenom = famillePrenom;
    this.enfantId = enfantId;
    this.enfantNom = enfantNom;
    this.enfantPrenom = enfantPrenom;
    this.aller = new Voyage();
    this.retour = new Voyage();
    this.dateDebut = (Date) dateDebut.clone();
    this.periodeJourneeDateDebut = periodeJourneeDebut;
    this.dateFin = (Date) dateFin.clone();
    this.periodeJourneeDateFin = periodeJourneeFin;
    this.payeurs = new HashSet<>();
  }

  public Sejour withFraisSejourJournalier(BigDecimal fraisSejourJournalier) {
    this.forfaitJournalier = fraisSejourJournalier;
    return this;
  }

  public Sejour withFraisDossier(BigDecimal fraisDossier) {
    this.fraisDossier = fraisDossier;
    return this;
  }

  public Sejour withFraisVoyage(BigDecimal fraisVoyage) {
    this.fraisVoyage = fraisVoyage;
    return this;
  }

  public Sejour withFraisPensionFamilleJournalier(BigDecimal fraisPensionFamilleJournalier) {
    this.fraisPensionFamilleJournalier = fraisPensionFamilleJournalier;
    return this;
  }

  public Integer nombreJours() {
    if (getDateAnnulation() != null) {
      return 0;
    }
    LocalDate lDateDebut = DateUtils.toLocalDate(getDateDebut());
    LocalDate lDateFin = DateUtils.toLocalDate(getDateFin());
    int between = (int) ChronoUnit.DAYS.between(lDateDebut, lDateFin); // date fin exclusive
    if (PeriodeJournee.APRES_MIDI.equals(getPeriodeJourneeDateFin())) {
      between++; // Le jour de fin est comptée si terminé l'apres-midi.
    }
    if (PeriodeJournee.APRES_MIDI.equals(getPeriodeJourneeDateDebut())) {
      // Si le séjour commence l'apres-midi, la premiere journée n'est pas comptée.
      between--;
    }
    return between;
  }

  public Long getId() {
    return id;
  }

  public Long getFamilleId() {
    return familleId;
  }

  public String getFamilleNom() {
    return familleNom;
  }

  public void setFamilleNom(String familleNom) {
    this.familleNom = familleNom;
  }

  public String getFamillePrenom() {
    return famillePrenom;
  }

  public void setFamillePrenom(String famillePrenom) {
    this.famillePrenom = famillePrenom;
  }

  public Long getEnfantId() {
    return enfantId;
  }

  public String getEnfantNom() {
    return enfantNom;
  }

  public void setEnfantNom(String enfantNom) {
    this.enfantNom = enfantNom;
  }

  public String getEnfantPrenom() {
    return enfantPrenom;
  }

  public void setEnfantPrenom(String enfantPrenom) {
    this.enfantPrenom = enfantPrenom;
  }

  public Voyage getAller() {
    return aller;
  }

  public Voyage getRetour() {
    return retour;
  }

  public Date getDateDebut() {
    return Objects.isNull(dateDebut) ? null : (Date) dateDebut.clone();
  }

  public void setDateDebut(Date dateDebut) {
    if (Objects.isNull(dateDebut)) {
      throw new IllegalArgumentException("La date de début est obligatoire");
    }
    this.dateDebut = (Date) dateDebut.clone();
  }

  public PeriodeJournee getPeriodeJourneeDateDebut() {
    return periodeJourneeDateDebut;
  }

  public void setPeriodeJourneeDateDebut(PeriodeJournee periodeJourneeDateDebut) {
    if (Objects.isNull(periodeJourneeDateDebut)) {
      throw new IllegalArgumentException("La période de journée de début est obligatoire");
    }
    this.periodeJourneeDateDebut = periodeJourneeDateDebut;
  }

  public Date getDateFin() {
    return Objects.isNull(dateFin) ? null : (Date) dateFin.clone();
  }

  public void setDateFin(Date dateFin) {
    if (Objects.isNull(dateFin)) {
      throw new IllegalArgumentException("La date de fin est obligatoire");
    }
    this.dateFin = (Date) dateFin.clone();
  }

  public PeriodeJournee getPeriodeJourneeDateFin() {
    return periodeJourneeDateFin;
  }

  public void setPeriodeJourneeDateFin(PeriodeJournee periodeJourneeDateFin) {
    this.periodeJourneeDateFin = periodeJourneeDateFin;
  }

  public BigDecimal getForfaitJournalier() {
    return Objects.isNull(forfaitJournalier) ? BigDecimal.ZERO : forfaitJournalier;
  }

  public void setForfaitJournalier(BigDecimal forfaitJournalier) {
    this.forfaitJournalier = forfaitJournalier;
  }

  public BigDecimal getFraisDossier() {
    return Objects.isNull(fraisDossier) ? BigDecimal.ZERO : fraisDossier;
  }

  public void setFraisDossier(BigDecimal fraisDossier) {
    this.fraisDossier = fraisDossier;
  }

  public BigDecimal getFraisPensionFamilleJournalier() {
    return Objects.isNull(fraisPensionFamilleJournalier) ? BigDecimal.ZERO
        : fraisPensionFamilleJournalier;
  }

  public void setFraisPensionFamilleJournalier(BigDecimal fraisPensionFamilleJournalier) {
    this.fraisPensionFamilleJournalier = fraisPensionFamilleJournalier;
  }

  public BigDecimal getFraisVoyage() {
    return Objects.isNull(fraisVoyage) ? BigDecimal.ZERO : fraisVoyage;
  }

  public void setFraisVoyage(BigDecimal fraisVoyage) {
    this.fraisVoyage = fraisVoyage;
  }

  public Set<Payeur> getPayeurs() {
    return Collections.unmodifiableSet(payeurs);
  }

  public void setPayeurs(Set<Payeur> payeurs) {
    this.payeurs = new HashSet<>(payeurs);
  }

  public void ajouterPayeur(Payeur payeur) {
    if (payeur == null) {
      throw new IllegalArgumentException("Le payeur est obligatoire");
    }
    this.payeurs.add(payeur);
  }

  public void retirerPayeur(Payeur payeur) {
    if (payeur == null) {
      throw new IllegalArgumentException("Le payeur est obligatoire");
    }
    this.payeurs.remove(payeur);
  }

  public Date getDateAnnulation() {
    return Objects.isNull(dateAnnulation) ? null : (Date) dateAnnulation.clone();
  }

  public void setDateAnnulation(Date dateAnnulation) {
    this.dateAnnulation = Objects.isNull(dateAnnulation) ? null : (Date) dateAnnulation.clone();
  }

  public String getMotifAnnulation() {
    return motifAnnulation;
  }

  public void setMotifAnnulation(String motifAnnulation) {
    this.motifAnnulation = motifAnnulation;
  }

  public String getMotifFinSejour() {
    return motifFinSejour;
  }

  public void setMotifFinSejour(String motifFinSejour) {
    this.motifFinSejour = motifFinSejour;
  }

  public Optional<StatutSejour> statut(Date date) {
    if (!Objects.isNull(getDateAnnulation())) {
      return Optional.of(StatutSejour.ANNULE);
    }
    if (date.before(getDateDebut())) {
      return Optional.of(StatutSejour.A_VENIR);
    }
    if (DateUtils.between(date, getDateDebut(), getDateFin())) {
      return Optional.of(StatutSejour.EN_COURS);
    }
    if (DateUtils.after(date, getDateFin())) {
      if (Objects.nonNull(motifFinSejour) && motifFinSejour.length() > 0) {
        return Optional.of(StatutSejour.TERMINE_PREMATUREMENT);
      } else {
        return Optional.of(StatutSejour.TERMINE);
      }
    }
    return Optional.empty();
  }

  public StatutSejour statutDuJour() {
    return statut(new Date()).get();
  }

}
