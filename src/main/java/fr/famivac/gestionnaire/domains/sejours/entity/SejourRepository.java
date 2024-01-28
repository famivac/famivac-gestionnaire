package fr.famivac.gestionnaire.domains.sejours.entity;

import fr.famivac.gestionnaire.domains.sejours.entity.views.SejoursFamilleDTO;
import java.util.Date;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;

/**
 * @author paoesco
 */
@ApplicationScoped
public class SejourRepository {

  @Inject
  private EntityManager entityManager;

  public List<Sejour> sejoursFamille(Long familleId) {
    return entityManager
        .createNamedQuery(Sejour.QUERY_SEJOURS_DE_LA_FAMILLE, Sejour.class)
        .setParameter("familleId", familleId)
        .getResultList();
  }

  public Sejour get(Long id) {
    return entityManager.find(Sejour.class, id);
  }

  public List<Sejour> getSejoursTerminesDansLaPeriode(Date dateDebut, Date dateFin) {
    return entityManager
        .createNamedQuery(Sejour.QUERY_SEJOURS_TERMINES_DANS_LA_PERIODE, Sejour.class)
        .setParameter("dateDebut", dateDebut, TemporalType.DATE)
        .setParameter("dateFin", dateFin, TemporalType.DATE)
        .getResultList();
  }

  public Long countActifs() {
    String jpql = "select count(s.id) from Sejour s where s.dateDebut <= :date and :date < s.dateFin";
    Query q = entityManager.createQuery(jpql);
    q.setParameter("date", new Date(), TemporalType.DATE);
    return (long) q.getSingleResult();
  }

  public List<SejoursFamilleDTO> getSejoursFamille(Long familleId) {
    return entityManager.createQuery(
            "SELECT NEW fr.famivac.gestionnaire.sejours.entity.views.SejoursFamilleDTO(s.id, s.familleId, s.famillePrenom, s.familleNom, s.enfantId, s.enfantPrenom, s.enfantNom, s.dateDebut, s.dateFin) "
                + " FROM Sejour s "
                + " WHERE s.familleId = :familleId "
                + " ORDER BY s.dateDebut ",
            SejoursFamilleDTO.class)
        .setParameter("familleId", familleId)
        .getResultList();
  }

}
