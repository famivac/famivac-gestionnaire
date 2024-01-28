package fr.famivac.gestionnaire.web.accompagnateurs;

import fr.famivac.gestionnaire.domains.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AccompagnateurAjoutBean implements Serializable {

  private Accompagnateur form;

  @Inject
  private AccompagnateurService accompagnateurService;

  public void init() {
    form = new Accompagnateur();
  }

  public String ajouter() {
    Long accId = accompagnateurService.create(form);
    return "/accompagnateurs/details.xhtml?id=" + accId + "&faces-redirect=true";
  }

  public Accompagnateur getForm() {
    return form;
  }

  public void setForm(Accompagnateur form) {
    this.form = form;
  }

}
