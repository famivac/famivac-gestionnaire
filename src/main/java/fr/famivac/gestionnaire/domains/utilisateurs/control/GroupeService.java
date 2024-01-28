package fr.famivac.gestionnaire.domains.utilisateurs.control;

import fr.famivac.gestionnaire.domains.utilisateurs.entity.Groupe;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

/**
 * @author paoesco
 */
@Stateless
public class GroupeService {

  @Inject
  private EntityManager entityManager;

  public List<Groupe> retrieve() {
    return entityManager.createNamedQuery(Groupe.QUERY_LISTE_ALL).getResultList();
  }

  public Groupe retrieve(String nom) {
    return entityManager.find(Groupe.class, nom);
  }

}
