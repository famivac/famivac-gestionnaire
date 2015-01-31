package fr.fava.gestionnaire.domain;

import fr.fava.gestionnaire.domain.model.enfant.Enfant;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author paoesco
 */
@RequestScoped
public class EnfantRepository {

    @Inject
    private EntityManager entityManager;

    public List<Enfant> retrieve(String nomEnfant, String prenomEnfant) {
        StringBuilder sQuery = new StringBuilder(" select e from Enfant e ");
        if (nomEnfant != null && !nomEnfant.isEmpty()) {
            sQuery.append(" and lower(e.nom) like :nomEnfant ");
        }
        if (prenomEnfant != null && !prenomEnfant.isEmpty()) {
            sQuery.append(" and lower(e.prenom) like :prenomEnfant ");
        }

        Query query = entityManager.createQuery(sQuery.toString().replaceFirst("and", "where"), Enfant.class);
        if (nomEnfant != null && !nomEnfant.isEmpty()) {
            query.setParameter("nomEnfant", "%" + nomEnfant.toLowerCase() + "%");
        }
        if (prenomEnfant != null && !prenomEnfant.isEmpty()) {
            query.setParameter("prenomEnfant", "%" + prenomEnfant.toLowerCase() + "%");
        }
        return query.getResultList();
    }

//    private String stripAccents(String s) {
//        s = Normalizer.normalize(s, Normalizer.Form.NFD);
//        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
//        return s;
//    }
}
