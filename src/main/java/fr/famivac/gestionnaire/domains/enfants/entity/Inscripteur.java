package fr.famivac.gestionnaire.domains.enfants.entity;

import fr.famivac.gestionnaire.commons.entity.Adresse;
import fr.famivac.gestionnaire.commons.entity.Coordonnees;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/** @author paoesco */
@Entity
@NamedQuery(
    name = Inscripteur.QUERY_RETRIEVE_ALL,
    query = "select i from Inscripteur i order by i.nom,i.prenom,i.organisme")
public class Inscripteur implements Serializable {

  public static final String QUERY_RETRIEVE_ALL = "inscripteurRetrieveAll";
  private static final long serialVersionUID = -5080832858740621543L;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inscripteur")
  private final Set<Enfant> enfants;
  @Id @GeneratedValue private Long id;
  @Enumerated(EnumType.STRING)
  private TypeInscripteur type;

  private String nom;

  private String prenom;

  private String organisme;

  @Embedded private Adresse adresse;

  @Embedded private Coordonnees coordonnees;

  private String numeroSiret;

  @Column(length = 2000)
  private String remarque;

  @OneToOne(cascade = CascadeType.ALL)
  private ResponsableInscripteur responsable;

  public Inscripteur() {
    enfants = new HashSet<>();
    adresse = new Adresse();
    type = TypeInscripteur.SERVICE_SOCIAL;
    coordonnees = new Coordonnees();
    responsable = new ResponsableInscripteur();
  }

  public Long getId() {
    return id;
  }

  public Set<Enfant> getEnfants() {
    return Collections.unmodifiableSet(enfants);
  }

  public void ajouterEnfant(Enfant enfant) {
    enfants.add(enfant);
  }

  public void retirerEnfant(Enfant enfant) {
    enfants.remove(enfant);
  }

  public TypeInscripteur getType() {
    return type;
  }

  public void setType(TypeInscripteur type) {
    this.type = type;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getOrganisme() {
    return organisme;
  }

  public void setOrganisme(String organisme) {
    this.organisme = organisme;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  public Coordonnees getCoordonnees() {
    return coordonnees;
  }

  public void setCoordonnees(Coordonnees coordonnees) {
    if (Objects.isNull(coordonnees)) {
      this.coordonnees = new Coordonnees();
    } else {
      this.coordonnees = coordonnees;
    }
  }

  public String getNumeroSiret() {
    return numeroSiret;
  }

  public void setNumeroSiret(String numeroSiret) {
    this.numeroSiret = numeroSiret;
  }

  public Boolean getParticulier() {
    return TypeInscripteur.PARTICULIER.equals(type);
  }

  public Boolean getTypeServiceSocialOuAutre() {
    return TypeInscripteur.SERVICE_SOCIAL.equals(type) || TypeInscripteur.AUTRE.equals(type);
  }

  public String getRemarque() {
    return remarque;
  }

  public void setRemarque(String remarque) {
    this.remarque = remarque;
  }

  public ResponsableInscripteur getResponsable() {
    return responsable;
  }

  public void setResponsable(ResponsableInscripteur responsable) {
    this.responsable = responsable;
  }
}
