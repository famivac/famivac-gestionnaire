package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.domains.familles.boundary.CreateFamillePayload;
import fr.famivac.gestionnaire.domains.familles.boundary.FamilleService;
import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import fr.famivac.gestionnaire.web.communes.CompleteCommune;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterFamilleBean implements Serializable, CompleteCommune {

  private static final long serialVersionUID = -8761462578741880134L;
  private final FamilleService familleService;
  private final CommuneService communeService;
  private CreateFamillePayload form;

  @Inject
  public AjouterFamilleBean(FamilleService familleService, CommuneService communeService) {
    this.familleService = familleService;
    this.communeService = communeService;
  }

  public void init() {
    form = new CreateFamillePayload();
  }

  public String ajouter() {
    Long familleId = familleService.create(form);
    return "/familles/details.xhtml?id=" + familleId + "&faces-redirect=true";
  }

  public CreateFamillePayload getForm() {
    return form;
  }

  public void setForm(CreateFamillePayload form) {
    this.form = form;
  }

  @Override
  public CommuneService getCommuneService() {
    return communeService;
  }
}
