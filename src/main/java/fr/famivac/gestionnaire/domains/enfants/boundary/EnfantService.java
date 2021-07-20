package fr.famivac.gestionnaire.domains.enfants.boundary;

import fr.famivac.gestionnaire.commons.events.UpdateEnfantEvent;
import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;
import fr.famivac.gestionnaire.domains.enfants.entity.EnfantRepository;
import fr.famivac.gestionnaire.domains.enfants.entity.ResponsableLegal;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import net.bull.javamelody.MonitoringInterceptor;

/** @author paoesco */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class EnfantService {

  @Inject Event<UpdateEnfantEvent> updateEnfantEvent;
  @Inject private EntityManager entityManager;
  @Inject private EnfantRepository enfantRepository;

  public Long create(Enfant enfant) {
    if (enfant.getInscripteurEstResponsableLegal()) {
      enfant.setResponsableLegal(new ResponsableLegal(enfant.getInscripteur()));
    }
    entityManager.persist(enfant);
    return enfant.getId();
  }

  public Enfant retrieve(Long id) {
    return entityManager.find(Enfant.class, id);
  }

  public void update(Enfant enfant) {
    entityManager.merge(enfant.getInscripteur());
    if (enfant.getInscripteurEstResponsableLegal()) {
      enfant.getResponsableLegal().setAdresse(enfant.getInscripteur().getAdresse());
      enfant.getResponsableLegal().setCoordonnees(enfant.getInscripteur().getCoordonnees());
      // enfant.getResponsableLegal().setLienDeParente();
      enfant.getResponsableLegal().setNom(enfant.getInscripteur().getNom());
      enfant.getResponsableLegal().setOrganisme(enfant.getInscripteur().getOrganisme());
      enfant.getResponsableLegal().setPrenom(enfant.getInscripteur().getPrenom());
      enfant.getResponsableLegal().setType(enfant.getInscripteur().getType());
    }
    entityManager.merge(enfant);

    // Publish message
    UpdateEnfantEvent event = new UpdateEnfantEvent();
    event.setId(enfant.getId());
    event.setNom(enfant.getNom());
    event.setPrenom(enfant.getPrenom());
    updateEnfantEvent.fire(event);
  }

  public List<EnfantDTO> retrieve(String nomEnfant, String prenomEnfant) {
    List<Enfant> entities = enfantRepository.retrieve(nomEnfant, prenomEnfant);
    return entities.stream()
        .map(
            (Enfant entity) -> {
              return new EnfantDTO(entity);
            })
        .collect(Collectors.toList());
  }

  public void delete(@PathParam("id") Long id) {
    Enfant entity = entityManager.find(Enfant.class, id);
    if (entity == null) {
      throw new IllegalArgumentException("L'enfant n'existe pas");
    }
    entityManager.remove(entity);
  }
}
