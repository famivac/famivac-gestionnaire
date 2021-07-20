package fr.famivac.gestionnaire.domains.dashboard.control;

import fr.famivac.gestionnaire.domains.enfants.entity.EnfantRepository;
import fr.famivac.gestionnaire.domains.enfants.entity.InscripteurRepository;
import fr.famivac.gestionnaire.domains.familles.entity.FamilleRepository;
import fr.famivac.gestionnaire.domains.sejours.entity.SejourRepository;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import net.bull.javamelody.MonitoringInterceptor;

/** @author paoesco */
@Stateless
@Interceptors({MonitoringInterceptor.class})
@TransactionAttribute(TransactionAttributeType.NEVER)
public class DashboardService {

  @Inject private FamilleRepository familleRepository;
  @Inject private EnfantRepository enfantRepository;
  @Inject private SejourRepository sejourRepository;
  @Inject private InscripteurRepository inscripteurRepository;

  public DashboardDTO getDefault() {
    DashboardDTO dashboardDTO = new DashboardDTO();
    dashboardDTO.setNombreFamilles(familleRepository.countActives());
    dashboardDTO.setNombreEnfants(enfantRepository.count());
    dashboardDTO.setNombreInscripteurs(inscripteurRepository.count());
    dashboardDTO.setNombreSejoursEnCours(sejourRepository.countActifs());
    return dashboardDTO;
  }
}
