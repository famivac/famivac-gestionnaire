package fr.famivac.gestionnaire.web.parametres.fraisdossier;

import fr.famivac.gestionnaire.domains.parametres.control.FraisDossierService;
import fr.famivac.gestionnaire.domains.parametres.entity.FraisDossier;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class FraisDossierPageBean implements Serializable {

  private FraisDossier form;

  /**
   * Liste.
   */
  private LazyFraisDossierDataModel lazyModel;

  @Inject
  private FraisDossierService service;

  /**
   * Initialisation du bean.
   */
  public void init() {
    lazyModel = new LazyFraisDossierDataModel(service.retrieve());
    form = new FraisDossier();
  }

  public void onRowEdit(RowEditEvent event) {
    FraisDossier entity = (FraisDossier) event.getObject();
    service.update(entity);
    FacesMessage msg = new FacesMessage("Le frais dossier a été modifié");
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void create() {
    service.create(form);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Le frais dossier a été ajouté", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public void supprimer(Long id) {
    service.delete(id);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Le frais dossier a été supprimé", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public LazyFraisDossierDataModel getLazyModel() {
    return lazyModel;
  }

  public FraisDossier getForm() {
    return form;
  }

  public void setForm(FraisDossier form) {
    this.form = form;
  }

}
