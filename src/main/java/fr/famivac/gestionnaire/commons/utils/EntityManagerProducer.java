package fr.famivac.gestionnaire.commons.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author paoesco
 */
@ApplicationScoped
public class EntityManagerProducer {

  @Produces
  @PersistenceContext(name = "gestionnairePU")
  private EntityManager entityManager;

}
