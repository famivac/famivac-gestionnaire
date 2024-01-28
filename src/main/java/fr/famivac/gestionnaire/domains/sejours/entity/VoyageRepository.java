package fr.famivac.gestionnaire.domains.sejours.entity;

import java.util.Date;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TemporalType;

/**
 * @author paoesco
 */
@ApplicationScoped
public class VoyageRepository {

  @Inject
  private EntityManager entityManager;

  public List<Voyage> prochainsVoyages(Date date) {
    return entityManager
        .createNamedQuery(Voyage.QUERY_VOYAGES_PROCHAINS, Voyage.class)
        .setParameter("date", date, TemporalType.DATE)
        .getResultList();

  }

}
