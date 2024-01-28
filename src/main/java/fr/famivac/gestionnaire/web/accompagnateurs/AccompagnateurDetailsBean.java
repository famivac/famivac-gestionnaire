package fr.famivac.gestionnaire.web.accompagnateurs;

import fr.famivac.gestionnaire.domains.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AccompagnateurDetailsBean implements Serializable {

  private Long id;

  private Accompagnateur form;

  @Inject
  private AccompagnateurService accompagnateurService;

  public void init() {
    form = accompagnateurService.get(id);
  }

  public void update() {
    accompagnateurService.update(form);
    FacesContext.getCurrentInstance()
        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Accompagnateur getForm() {
    return form;
  }

  public void setForm(Accompagnateur form) {
    this.form = form;
  }

}
