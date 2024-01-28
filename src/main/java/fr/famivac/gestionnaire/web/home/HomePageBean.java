package fr.famivac.gestionnaire.web.home;

import fr.famivac.gestionnaire.domains.dashboard.control.DashboardDTO;
import fr.famivac.gestionnaire.domains.dashboard.control.DashboardService;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Backing bean index.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class HomePageBean implements Serializable {

  private Long nombreFamilles;
  private Long nombreEnfants;
  private Long nombreInscripteurs;
  private Long nombreSejoursEnCours;

  @Inject
  private DashboardService dashboardService;

  public void init() {
    DashboardDTO dashboardDTO = dashboardService.getDefault();
    nombreEnfants = dashboardDTO.getNombreEnfants();
    nombreFamilles = dashboardDTO.getNombreFamilles();
    nombreInscripteurs = dashboardDTO.getNombreInscripteurs();
    nombreSejoursEnCours = dashboardDTO.getNombreSejoursEnCours();
  }

  public Long getNombreFamilles() {
    return nombreFamilles;
  }

  public void setNombreFamilles(Long nombreFamilles) {
    this.nombreFamilles = nombreFamilles;
  }

  public Long getNombreEnfants() {
    return nombreEnfants;
  }

  public void setNombreEnfants(Long nombreEnfants) {
    this.nombreEnfants = nombreEnfants;
  }

  public Long getNombreInscripteurs() {
    return nombreInscripteurs;
  }

  public void setNombreInscripteurs(Long nombreInscripteurs) {
    this.nombreInscripteurs = nombreInscripteurs;
  }

  public Long getNombreSejoursEnCours() {
    return nombreSejoursEnCours;
  }

  public void setNombreSejoursEnCours(Long nombreSejoursEnCours) {
    this.nombreSejoursEnCours = nombreSejoursEnCours;
  }

  public DashboardService getDashboardService() {
    return dashboardService;
  }

  public void setDashboardService(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

}
