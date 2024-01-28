package fr.famivac.gestionnaire.web.parametres.forfaitjournalier;

import fr.famivac.gestionnaire.domains.parametres.control.ForfaitJournalierService;
import fr.famivac.gestionnaire.domains.parametres.entity.ForfaitJournalier;
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
public class ForfaitJournalierBean implements Serializable {

  private ForfaitJournalier form;

  /**
   * Liste des tarifs.
   */
  private LazyForfaitJournalierDataModel lazyModel;

  @Inject
  private ForfaitJournalierService forfaitJournalierService;

  /**
   * Initialisation du bean.
   */
  public void init() {
    lazyModel = new LazyForfaitJournalierDataModel(forfaitJournalierService.retrieve());
    form = new ForfaitJournalier();
  }

  public void onRowEdit(RowEditEvent event) {
    ForfaitJournalier entity = (ForfaitJournalier) event.getObject();
    forfaitJournalierService.update(entity);
    FacesMessage msg = new FacesMessage("Le tarif a été modifié");
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void create() {
    forfaitJournalierService.create(form);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Le tarif a été ajouté",
        null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public void supprimer(Long id) {
    forfaitJournalierService.delete(id);
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Le tarif a été supprimé",
        null);
    FacesContext.getCurrentInstance().addMessage(null, message);
    init();
  }

  public LazyForfaitJournalierDataModel getLazyModel() {
    return lazyModel;
  }

  public ForfaitJournalier getForm() {
    return form;
  }

  public void setForm(ForfaitJournalier form) {
    this.form = form;
  }

}
