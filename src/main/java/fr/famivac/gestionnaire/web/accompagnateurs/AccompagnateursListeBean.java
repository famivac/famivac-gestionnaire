package fr.famivac.gestionnaire.web.accompagnateurs;

import fr.famivac.gestionnaire.domains.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.domains.sejours.entity.AccompagnateurRepository;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Backing bean des accompagnateurs.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class AccompagnateursListeBean implements Serializable {

  /**
   * Liste des accompagnateurs.
   */
  private LazyAccompagnateurDataModel lazyModel;

  @Inject
  private AccompagnateurRepository accompagnateurRepository;

  @Inject
  private AccompagnateurService accompagnateurService;

  /**
   * Initialisation du bean.
   */
  public void init() {
    rechercher();
  }

  public void rechercher() {
    lazyModel = new LazyAccompagnateurDataModel(accompagnateurRepository.get());
  }

  public void supprimer(Long id) {
    accompagnateurService.delete(id);
    init();
  }

  public LazyAccompagnateurDataModel getLazyModel() {
    return lazyModel;
  }

}
