package fr.famivac.gestionnaire.domains.familles.entity;

import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/** @author paoesco */
@ApplicationScoped
public class FamilleRepository {

  @Inject private EntityManager entityManager;

  public List<Famille> retrieve(
      String nomReferent,
      String prenomReferent,
      Set<PeriodeAccueil> periodesAccueil,
      boolean archivee) {

    // First we select all the ids
    // Then we select the entities

    // SELECT / JOIN
    StringBuilder sIdsQuery = new StringBuilder(" select f.id from Famille f join f.membres m ");
    if (periodesAccueil != null && !periodesAccueil.isEmpty()) {
      sIdsQuery.append(" join f.periodesSouhaitees periode ");
    }

    // WHERE
    sIdsQuery.append(" where m.referent = true ");

    if (nomReferent != null && !nomReferent.isEmpty()) {
      sIdsQuery.append(" and lower(m.nom) like :nomReferent ");
    }
    if (prenomReferent != null && !prenomReferent.isEmpty()) {
      sIdsQuery.append(" and lower(m.prenom) like :prenomReferent ");
    }
    if (periodesAccueil != null && !periodesAccueil.isEmpty()) {
      sIdsQuery.append(" and periode in :periodesAccueil ");
    }
    sIdsQuery.append(" and archivee = :archivee ");

    // PARAMETERS
    Query idQuery = entityManager.createQuery(sIdsQuery.toString(), Long.class);
    if (nomReferent != null && !nomReferent.isEmpty()) {
      idQuery.setParameter("nomReferent", "%" + nomReferent.toLowerCase() + "%");
    }
    if (prenomReferent != null && !prenomReferent.isEmpty()) {
      idQuery.setParameter("prenomReferent", "%" + prenomReferent.toLowerCase() + "%");
    }
    if (periodesAccueil != null && !periodesAccueil.isEmpty()) {
      idQuery.setParameter("periodesAccueil", periodesAccueil);
    }
    idQuery.setParameter("archivee", archivee);

    List<Long> ids = idQuery.getResultList();

    Query familleQuery =
        entityManager.createQuery("""
        SELECT f 
        FROM Famille f 
        JOIN FETCH f.membres m
        JOIN FETCH f.tranchesAges
        JOIN FETCH f.periodesSouhaitees
        JOIN FETCH f.informationsHabitation
        JOIN FETCH f.informationsVehicule
        JOIN FETCH m.communeDeNaissance
        JOIN FETCH f.adresse.commune
        WHERE f.id in :id
        AND m.referent = true
        ORDER BY m.nom, m.prenom
    """, Famille.class);
    familleQuery.setParameter("id", ids);

    return familleQuery.getResultList();
  }

  public Long countActives() {
    String jpql =
        "SELECT count(f.id) FROM Famille f WHERE f.dateRadiation IS NULL AND f.candidature = false AND f.archivee = false ";
    Query q = entityManager.createQuery(jpql);
    return (long) q.getSingleResult();
  }

}
