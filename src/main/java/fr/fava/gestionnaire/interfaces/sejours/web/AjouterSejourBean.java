package fr.fava.gestionnaire.interfaces.sejours.web;

import fr.fava.gestionnaire.application.enfant.EnfantDTO;
import fr.fava.gestionnaire.application.enfant.EnfantService;
import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.application.famille.FamilleDTO;
import fr.fava.gestionnaire.application.sejour.AjouterSejourDTO;
import fr.fava.gestionnaire.application.sejour.SejourService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterSejourBean implements Serializable {

    @Inject
    private FamilleService familleService;

    @Inject
    private EnfantService enfantService;

    @Inject
    private SejourService sejourService;

    private AjouterSejourDTO form;

    public void init() {
        form = new AjouterSejourDTO();
    }

    public void ajouter() {
        sejourService.create(form);
        form = new AjouterSejourDTO();
    }

    public List<FamilleDTO> completeFamille(String query) {
        if (query == null || query.isEmpty()) {
            return familleService.retrieve("%", "%");
        }
        return familleService.retrieve(query, "%");
    }

    public List<EnfantDTO> completeEnfant(String query) {
        if (query == null || query.isEmpty()) {
            return enfantService.retrieve("%", "%");
        }
        return enfantService.retrieve(query, "%");
    }

    public AjouterSejourDTO getForm() {
        return form;
    }

    public void setForm(AjouterSejourDTO form) {
        this.form = form;
    }

}
