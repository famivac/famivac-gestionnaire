package fr.famivac.gestionnaire.domains.parametres.control;

import fr.famivac.gestionnaire.domains.parametres.entity.FraisPensionFamilleJournalier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class FraisPensionFamilleJournalierService {

    @Inject
    private EntityManager entityManager;

    public Long create(FraisPensionFamilleJournalier entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public List<FraisPensionFamilleJournalier> retrieve() {
        return entityManager
                .createNamedQuery(FraisPensionFamilleJournalier.QUERY_LISTE_ALL, FraisPensionFamilleJournalier.class)
                .getResultList();
    }

    public void update(FraisPensionFamilleJournalier entity) {
        entityManager.merge(entity);
    }

    public void delete(Long id) {
        FraisPensionFamilleJournalier entity = entityManager.find(FraisPensionFamilleJournalier.class, id);
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException("L'entité n'existe pas");
        }
        entityManager.remove(entity);
    }

    public Optional<BigDecimal> getCurrentMontant(Date date) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery(FraisPensionFamilleJournalier.QUERY_GET_CURRENT, FraisPensionFamilleJournalier.class)
                    .setParameter("date", date)
                    .getSingleResult()
                    .getMontant());
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

}
