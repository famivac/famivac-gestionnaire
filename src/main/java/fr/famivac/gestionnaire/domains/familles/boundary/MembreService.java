package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.commons.entity.Sexe;
import fr.famivac.gestionnaire.commons.events.UpdateFamilleEvent;
import fr.famivac.gestionnaire.domains.familles.entity.MembreFamille;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import net.bull.javamelody.MonitoringInterceptor;

@Stateless
@Interceptors({MonitoringInterceptor.class})
public class MembreService {

  @Inject Event<UpdateFamilleEvent> updateFamilleEvent;
  @Inject private EntityManager entityManager;

  public MembreDTO retrieve(Long id) {
    MembreFamille entity = entityManager.find(MembreFamille.class, id);
    MembreDTO dto = new MembreDTO();
    dto.setId(entity.getId());
    Commune commune = null;
    if (entity.getCommuneDeNaissance() != null) {
      commune =
          new Commune(
              entity.getCommuneDeNaissance().getCode(), entity.getCommuneDeNaissance().getVille());
    }
    dto.setCommuneDeNaissance(commune);
    dto.setDateNaissance(entity.getDateNaissance());
    dto.setLienDeParente(entity.getLienDeParente());
    dto.setNom(entity.getNom());
    dto.setNomDeNaissance(entity.getNomDeNaissance());
    dto.setPrenom(entity.getPrenom());
    dto.setProfession(entity.getProfession());
    dto.setReferent(entity.getReferent());
    dto.setSexe(entity.getSexe().name());
    dto.setCoordonnees(entity.getCoordonnees());
    return dto;
  }

  public void update(Long familleId, MembreDTO payload) {
    MembreFamille entity = entityManager.find(MembreFamille.class, payload.getId());
    Commune commune =
        new Commune(
            payload.getCommuneDeNaissance().getCode(), payload.getCommuneDeNaissance().getVille());
    entity.setCommuneDeNaissance(commune);
    entity.setDateNaissance(payload.getDateNaissance());
    entity.setLienDeParente(payload.getLienDeParente());
    entity.setNom(payload.getNom());
    entity.setNomDeNaissance(payload.getNomDeNaissance());
    entity.setPrenom(payload.getPrenom());
    entity.setProfession(payload.getProfession());
    entity.setReferent(payload.getReferent());
    entity.setSexe(Sexe.valueOf(payload.getSexe()));
    entity.setCoordonnees(payload.getCoordonnees());
    entityManager.merge(entity);

    // Publish message
    UpdateFamilleEvent event = new UpdateFamilleEvent();
    event.setId(familleId);
    event.setNom(entity.getNom());
    event.setPrenom(entity.getPrenom());
    event.setTelephone(entity.getCoordonnees().getTelephone1());
    event.setReferent(entity.getReferent());
    updateFamilleEvent.fire(event);
  }
}
