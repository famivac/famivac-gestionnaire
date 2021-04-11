package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import fr.famivac.gestionnaire.domains.familles.boundary.CreateFamilleRequestDTO;
import fr.famivac.gestionnaire.domains.familles.boundary.FamilleService;
import fr.famivac.gestionnaire.web.communes.CompleteCommune;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterFamilleBean implements Serializable, CompleteCommune {

	private static final long serialVersionUID = -8761462578741880134L;

	private CreateFamilleRequestDTO form;

    private final FamilleService familleService;

    private final CommuneService communeService;

    @Inject
    public AjouterFamilleBean(FamilleService familleService,
        CommuneService communeService) {
        this.familleService = familleService;
        this.communeService = communeService;
    }

    public void init() {
        form = new CreateFamilleRequestDTO();
    }

    public String ajouter() {
        Long familleId = familleService.create(form);
        return "/familles/details.xhtml?id=" + familleId + "&faces-redirect=true";
    }

    public CreateFamilleRequestDTO getForm() {
        return form;
    }

    public void setForm(CreateFamilleRequestDTO form) {
        this.form = form;
    }

    @Override
    public CommuneService getCommuneService() {
        return communeService;
    }

}
