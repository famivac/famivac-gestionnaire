package fr.famivac.gestionnaire.web.parametres.fraisvoyage;

import fr.famivac.gestionnaire.domains.parametres.control.FraisVoyageService;
import fr.famivac.gestionnaire.domains.parametres.entity.FraisVoyage;
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
public class FraisVoyagePageBean implements Serializable {

  private FraisVoyage form;

  /**
   * Liste des tarifs.
   */
  private LazyFraisVoyageDataModel lazyModel;

  @Inject
  private FraisVoyageService service;

  /**
   * Initialisation du bean.
   */
  public void init() {
    lazyModel = new LazyFraisVoyageDataModel(service.retrieve());
    form = FraisVoyage._DEFAULT;
  }

  public void onRowEdit(RowEditEvent event) {
    FraisVoyage entity = (FraisVoyage) event.getObject();
    service.update(entity);
    FacesMessage msg = new FacesMessage("Le frais voyage a été modifié");
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void create() {
    service.create(form);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Le frais voyage a été ajouté", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public void supprimer(Long id) {
    service.delete(id);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Le frais voyage a été supprimé", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public LazyFraisVoyageDataModel getLazyModel() {
    return lazyModel;
  }

  public FraisVoyage getForm() {
    return form;
  }

  public void setForm(FraisVoyage form) {
    this.form = form;
  }

}
