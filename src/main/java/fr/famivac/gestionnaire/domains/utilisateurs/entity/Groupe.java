package fr.famivac.gestionnaire.domains.utilisateurs.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Groupe.QUERY_LISTE_ALL, query = "select g from Groupe g")
})
public class Groupe implements Serializable {

  public static final String QUERY_LISTE_ALL = "getGroupes";

  public static final String ROLE_GESTIONNAIRE = "ROLE_GESTIONNAIRE";

  @Id
  @Column(nullable = false)
  private String nom;

  private String libelle;

  @ManyToMany(mappedBy = "groupes")
  private Set<Utilisateur> utilisateurs;

  protected Groupe() {
    utilisateurs = new HashSet<>();
  }

  public Groupe(String nom, String libelle) {
    if (nom == null || nom.isEmpty()) {
      throw new IllegalArgumentException("Le nom est obligatoire");
    }
    this.utilisateurs = new HashSet<>();
    this.nom = nom;
    this.libelle = libelle;
  }

  public String getNom() {
    return nom;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Set<Utilisateur> getUtilisateurs() {
    return Collections.unmodifiableSet(utilisateurs);
  }

}
