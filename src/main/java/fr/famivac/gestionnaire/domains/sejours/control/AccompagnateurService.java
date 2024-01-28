package fr.famivac.gestionnaire.domains.sejours.control;

import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
import java.util.Date;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class AccompagnateurService {

  @Inject
  private EntityManager entityManager;

  public Long create(Accompagnateur bean) {
    entityManager.persist(bean);
    return bean.getId();
  }

  public void update(Accompagnateur bean) {
    entityManager.merge(bean);
  }

  public void delete(Long id) {
    Accompagnateur accompagnateur = entityManager.find(Accompagnateur.class, id);
    accompagnateur.setDeletedAt(new Date());
  }

  public Accompagnateur get(Long id) {
    return entityManager.find(Accompagnateur.class, id);
  }

}
