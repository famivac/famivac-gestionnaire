package fr.famivac.gestionnaire.domains.enfants.entity;

import fr.famivac.gestionnaire.domains.enfants.entity.views.EnfantListView;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author paoesco
 */
@ApplicationScoped
public class EnfantRepository {

  @Inject
  private EntityManager entityManager;

  public List<EnfantListView> search(String nomEnfant, String prenomEnfant) {
    Query query = entityManager.createQuery(
        "select new fr.famivac.gestionnaire.domains.enfants.entity.views.EnfantListView(" +
            " e.id," +
            " e.nom," +
            " e.prenom" +
            " )" +
            " from Enfant e" +
            " where lower(e.nom) like :nom" +
            " and lower(e.prenom) like :prenom" +
            " order by e.nom"
        , EnfantListView.class);
    query.setParameter("nom",
        "%" + (Objects.isNull(nomEnfant) ? "" : nomEnfant.toLowerCase() + "%"));
    query.setParameter("prenom",
        "%" + (Objects.isNull(prenomEnfant) ? "" : prenomEnfant.toLowerCase() + "%"));
    return query.getResultList();
  }

  public Long count() {
    String jpql = "select count(e.id) from Enfant e";
    Query q = entityManager.createQuery(jpql);
    return (long) q.getSingleResult();
  }

//    private String stripAccents(String s) {
//        s = Normalizer.normalize(s, Normalizer.Form.NFD);
//        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
//        return s;
//    }
}
