package fr.famivac.gestionnaire.domains.familles.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.commons.entity.BaseEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Famille extends BaseEntity {

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  private Set<MembreFamille> membres;

  @Embedded private Adresse adresse;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<PeriodeAccueil> periodesSouhaitees;

  private Boolean sejoursComplets;
  private String precisionsSejoursNonComplets;

  @Column(length = 2000)
  private String projet;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Chambre> chambres;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<TrancheAgeEnfant> tranchesAges;

  @Column(length = 2000)
  private String connaissanceAssociation;

  private Integer nombreFillesSouhaitees;
  private Integer nombreGarconsSouhaites;
  private Boolean accepteHandicap;
  private Boolean accepteMalade;
  private Boolean extraitCasierJudiciaire;

  @Temporal(TemporalType.DATE)
  private Date dateReceptionCasierJudiciaire;

  private String nomRecruteur;

  @Temporal(TemporalType.DATE)
  private Date dateRecrutement;

  @Enumerated(EnumType.STRING)
  private Avis avisRecrutement;

  private Boolean visiteDdcs;

  @Temporal(TemporalType.DATE)
  private Date dateVisiteDdcs;

  @Enumerated(EnumType.STRING)
  private Avis avisDdcs;

  @Column(length = 2000)
  private String remarque;

  @OneToOne(mappedBy = "famille", cascade = CascadeType.ALL, orphanRemoval = true)
  private InformationsHabitation informationsHabitation;

  @OneToOne(mappedBy = "famille", cascade = CascadeType.ALL, orphanRemoval = true)
  private InformationsVehicule informationsVehicule;

  @Column(name = "DATE_RADIATION")
  @Temporal(TemporalType.DATE)
  private Date dateRadiation;

  @Column(name = "CANDIDATURE")
  private Boolean candidature;

  @Column(name = "ARCHIVEE")
  private Boolean archivee;

  public Famille() {
    adresse = new Adresse();
    membres = new HashSet<>();
    chambres = new HashSet<>();
    tranchesAges = new HashSet<>();
    periodesSouhaitees = new HashSet<>();
    informationsHabitation = new InformationsHabitation(this);
    informationsVehicule = new InformationsVehicule(this);
    accepteHandicap = false;
    accepteMalade = false;
    extraitCasierJudiciaire = false;
    visiteDdcs = false;
    candidature = false;
    archivee = false;
    sejoursComplets = false;
  }

  public void ajouterChambre(Chambre chambre) {
    if (chambre == null) {
      throw new IllegalArgumentException("Renseigner la chambre");
    }
    chambres.add(chambre);
  }

  public void retirerChambre(Chambre chambre) {
    if (chambre == null) {
      throw new IllegalArgumentException("Renseigner la chambre");
    }
    chambres.remove(chambre);
  }

  public void ajouterMembre(MembreFamille membre) {
    if (membre == null) {
      throw new IllegalArgumentException("Tous les paramètre sont obligatoires");
    }
    membres.add(membre);
  }

  public void retirerMembre(MembreFamille membre) {
    if (membre == null) {
      throw new IllegalArgumentException("Tous les paramètre sont obligatoires");
    }
    membres.remove(membre);
  }

  public MembreFamille getMembreReferent() {
    return membres.stream()
        .filter(
            (MembreFamille m) -> {
              return m.getReferent();
            })
        .findFirst()
        .get();
  }

  public void definirReferent(Long id) {
    membres.stream()
        .forEach(
            (MembreFamille m) -> {
              if (m.getId().equals(id)) {
                m.setReferent(true);
              } else {
                m.setReferent(false);
              }
            });
  }

  public String[] getPeriodesSouhaitees() {
    return periodesSouhaitees.stream()
        .map(
            (PeriodeAccueil p) -> {
              return p.name();
            })
        .collect(Collectors.toList())
        .toArray(new String[periodesSouhaitees.size()]);
  }

  public void setPeriodesSouhaitees(String[] periodesSouhaitees) {
    this.periodesSouhaitees.clear();
    for (String p : periodesSouhaitees) {
      this.periodesSouhaitees.add(PeriodeAccueil.valueOf(p));
    }
  }

  public String[] getTranchesAges() {
    return tranchesAges.stream()
        .map((TrancheAgeEnfant t) -> t.name())
        .collect(Collectors.toList())
        .toArray(new String[tranchesAges.size()]);
  }

  public void setTranchesAges(String[] tranchesAges) {
    this.tranchesAges.clear();
    for (String t : tranchesAges) {
      this.tranchesAges.add(TrancheAgeEnfant.valueOf(t));
    }
  }

  public Boolean getRadiee() {
    return Objects.nonNull(dateRadiation);
  }
}
