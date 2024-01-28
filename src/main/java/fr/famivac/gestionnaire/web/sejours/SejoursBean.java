package fr.famivac.gestionnaire.web.sejours;

import fr.famivac.gestionnaire.domains.sejours.control.SejourDTO;
import fr.famivac.gestionnaire.domains.sejours.control.SejourService;
import fr.famivac.gestionnaire.domains.sejours.entity.StatutSejour;
import java.io.Serializable;
import java.util.List;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.commons.lang3.StringUtils;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class SejoursBean implements Serializable {

  private static final long serialVersionUID = -4128093359342288762L;

  /**
   * Liste des séjours.
   */
  private LazySejourDataModel lazyModel;

  @Inject
  private SejourService sejourService;

  @Inject
  private RechercherSejoursForm rechercherForm;

  public void init() {
    rechercherForm.setStatutSejour(StatutSejour.EN_COURS.name());
    rechercher();
  }

  public void supprimer(Long id) {
    sejourService.delete(id);
    init(); // recharge des séjours
  }

  public void rechercher() {
    StatutSejour statut = null;
    if (!StringUtils.isEmpty(rechercherForm.getStatutSejour())) {
      statut = StatutSejour.valueOf(rechercherForm.getStatutSejour());
    }
    List<SejourDTO> sejours = sejourService.rechercher(rechercherForm.getNomReferent(),
        rechercherForm.getPrenomReferent(), rechercherForm.getNomEnfant(),
        rechercherForm.getPrenomEnfant(), statut);
    lazyModel = new LazySejourDataModel(sejours);
  }

  public LazySejourDataModel getLazyModel() {
    return lazyModel;
  }

}
