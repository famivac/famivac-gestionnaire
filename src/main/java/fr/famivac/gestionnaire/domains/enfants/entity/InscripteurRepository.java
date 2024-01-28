package fr.famivac.gestionnaire.domains.enfants.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * @author paoesco
 */
@ApplicationScoped
public class InscripteurRepository {

  @Inject
  private EntityManager entityManager;

  public List<Inscripteur> search(String nom, String prenom, String organisme) {
    List<Inscripteur> results = new ArrayList<>();
    if (nom != null && !nom.isEmpty() || prenom != null && !prenom.isEmpty()) {
      results.addAll(searchParticulier(nom, prenom));
    }
    if (organisme != null && !organisme.isEmpty()) {
      results.addAll(searchOrganisme(organisme));
    }
    return results;
  }

  private List<Inscripteur> searchParticulier(String nom, String prenom) {
    StringBuilder sQueryParticulier = new StringBuilder(" select i from Inscripteur i ");
    if (nom != null && !nom.isEmpty()) {
      sQueryParticulier.append(" and lower(i.nom) like :nom ");
    }
    if (prenom != null && !prenom.isEmpty()) {
      sQueryParticulier.append(" and lower(i.prenom) like :prenom ");
    }
    sQueryParticulier.append(" and type = :typeParticulier ");
    Query queryParticulier = entityManager.createQuery(
        sQueryParticulier.toString().replaceFirst("and", "where"), Inscripteur.class);
    if (nom != null && !nom.isEmpty()) {
      queryParticulier.setParameter("nom", "%" + nom.toLowerCase() + "%");
    }
    if (prenom != null && !prenom.isEmpty()) {
      queryParticulier.setParameter("prenom", "%" + prenom.toLowerCase() + "%");
    }
    queryParticulier.setParameter("typeParticulier", TypeInscripteur.PARTICULIER);
    return queryParticulier.getResultList();
  }

  private List<Inscripteur> searchOrganisme(String organisme) {
    StringBuilder sQueryAutre = new StringBuilder(" select i from Inscripteur i ");
    if (organisme != null && !organisme.isEmpty()) {
      sQueryAutre.append(" and lower(i.organisme) like :organisme ");
    }
    sQueryAutre.append(" and (type = :typeAutre or type = :typeServiceSocial) ");
    Query queryAutre = entityManager.createQuery(
        sQueryAutre.toString().replaceFirst("and", "where"), Inscripteur.class);
    if (organisme != null && !organisme.isEmpty()) {
      queryAutre.setParameter("organisme", "%" + organisme.toLowerCase() + "%");
    }
    queryAutre.setParameter("typeAutre", TypeInscripteur.AUTRE);
    queryAutre.setParameter("typeServiceSocial", TypeInscripteur.SERVICE_SOCIAL);
    return queryAutre.getResultList();
  }

  public Long count() {
    String jpql = "select count(i.id) from Inscripteur i";
    Query q = entityManager.createQuery(jpql);
    return (long) q.getSingleResult();
  }

}
