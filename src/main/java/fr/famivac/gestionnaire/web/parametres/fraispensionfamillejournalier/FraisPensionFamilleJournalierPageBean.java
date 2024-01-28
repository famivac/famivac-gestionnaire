package fr.famivac.gestionnaire.web.parametres.fraispensionfamillejournalier;

import fr.famivac.gestionnaire.domains.parametres.control.FraisPensionFamilleJournalierService;
import fr.famivac.gestionnaire.domains.parametres.entity.FraisPensionFamilleJournalier;
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
public class FraisPensionFamilleJournalierPageBean implements Serializable {

  private FraisPensionFamilleJournalier form;

  /**
   * Liste.
   */
  private LazyFraisPensionFamilleJournalierDataModel lazyModel;

  @Inject
  private FraisPensionFamilleJournalierService service;

  /**
   * Initialisation du bean.
   */
  public void init() {
    lazyModel = new LazyFraisPensionFamilleJournalierDataModel(service.retrieve());
    form = FraisPensionFamilleJournalier._DEFAULT;
  }

  public void onRowEdit(RowEditEvent event) {
    FraisPensionFamilleJournalier entity = (FraisPensionFamilleJournalier) event.getObject();
    service.update(entity);
    FacesMessage msg = new FacesMessage("Le frais pension famille journalier a été modifié");
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void create() {
    service.create(form);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Le frais pension famille journalier a été ajouté", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public void supprimer(Long id) {
    service.delete(id);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Le frais pension famille journalier a été supprimé", null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public LazyFraisPensionFamilleJournalierDataModel getLazyModel() {
    return lazyModel;
  }

  public FraisPensionFamilleJournalier getForm() {
    return form;
  }

  public void setForm(FraisPensionFamilleJournalier form) {
    this.form = form;
  }

}
