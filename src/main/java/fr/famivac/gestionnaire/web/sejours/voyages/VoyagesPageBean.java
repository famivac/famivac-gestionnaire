package fr.famivac.gestionnaire.web.sejours.voyages;

import fr.famivac.gestionnaire.domains.sejours.control.SejourService;
import fr.famivac.gestionnaire.domains.sejours.control.VoyageDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class VoyagesPageBean implements Serializable {

  private static final long serialVersionUID = -4851235383383368367L;

  private LazyVoyagesDataModel lazyModel;

  @Inject
  private SejourService sejourService;

  public void init() {
    List<VoyageDTO> voyages = sejourService.prochainsVoyages(new Date());
    lazyModel = new LazyVoyagesDataModel(voyages);
  }

  public LazyVoyagesDataModel getLazyModel() {
    return lazyModel;
  }

}
