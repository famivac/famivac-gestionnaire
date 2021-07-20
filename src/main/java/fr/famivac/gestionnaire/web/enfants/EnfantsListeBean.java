package fr.famivac.gestionnaire.web.enfants;

import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantService;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des enfants.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class EnfantsListeBean implements Serializable {

  /** Liste des enfants. */
  private LazyEnfantDataModel lazyModel;

  @Inject private EnfantService enfantService;

  @Inject private RechercherEnfantsForm rechercherForm;

  /** Initialisation du bean. */
  public void init() {
    rechercher();
  }

  public void rechercher() {
    lazyModel =
        new LazyEnfantDataModel(
            enfantService.retrieve(
                rechercherForm.getNomEnfant(), rechercherForm.getPrenomEnfant()));
  }

  public void supprimer(Long id) {
    enfantService.delete(id);
    init(); // recharge des entités
  }

  public LazyEnfantDataModel getLazyModel() {
    return lazyModel;
  }

  public EnfantService getEnfantService() {
    return enfantService;
  }

  public void setEnfantService(EnfantService enfantService) {
    this.enfantService = enfantService;
  }

  public RechercherEnfantsForm getRechercherForm() {
    return rechercherForm;
  }

  public void setRechercherForm(RechercherEnfantsForm rechercherForm) {
    this.rechercherForm = rechercherForm;
  }
}
