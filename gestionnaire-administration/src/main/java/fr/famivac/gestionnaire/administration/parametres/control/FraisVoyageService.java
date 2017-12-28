package fr.famivac.gestionnaire.administration.parametres.control;

import fr.famivac.gestionnaire.administration.parametres.entity.FraisVoyage;
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
public class FraisVoyageService {

    @Inject
    private EntityManager entityManager;

    public Long create(FraisVoyage entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public List<FraisVoyage> retrieve() {
        return entityManager
                .createNamedQuery(FraisVoyage.QUERY_LISTE_ALL, FraisVoyage.class)
                .getResultList();
    }

    public void update(FraisVoyage entity) {
        entityManager.merge(entity);
    }

    public void delete(Long id) {
        FraisVoyage entity = entityManager.find(FraisVoyage.class, id);
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException("L'entité n'existe pas");
        }
        entityManager.remove(entity);
    }

    public Optional<BigDecimal> getCurrentMontant(Date date) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery(FraisVoyage.QUERY_GET_CURRENT, FraisVoyage.class)
                    .setParameter("date", date)
                    .getSingleResult()
                    .getMontant());
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

}
