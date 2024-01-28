package fr.famivac.gestionnaire.web.enfants;

import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantService;
import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;
import fr.famivac.gestionnaire.domains.enfants.entity.FamilleAccueil;
import fr.famivac.gestionnaire.domains.enfants.entity.TypeInscripteur;
import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import fr.famivac.gestionnaire.web.communes.CompleteCommune;
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
public class EnfantDetailsBean implements Serializable, CompleteCommune {

  private static final long serialVersionUID = -3744243098082552244L;

  private Long id;

  private Enfant form;

  private FamilleAccueil formFamilleAccueil;

  @Inject
  private CommuneService communeService;

  @Inject
  private EnfantService enfantService;

  public void init() {
    form = enfantService.search(id);
    if (form.getFamilleAccueil() == null) {
      formFamilleAccueil = new FamilleAccueil();
    } else {
      formFamilleAccueil = form.getFamilleAccueil();
    }
  }

  public void update() {
    enfantService.update(form);
    FacesContext.getCurrentInstance()
        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
  }

  public void updateFamilleAccueil() {
    form.setFamilleAccueil(formFamilleAccueil);
    enfantService.update(form);
    FacesContext.getCurrentInstance()
        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Enfant getForm() {
    return form;
  }

  public void setForm(Enfant form) {
    this.form = form;
  }

  @Override
  public CommuneService getCommuneService() {
    return communeService;
  }

  public Boolean getTypeInscripteurParticulier() {
    return TypeInscripteur.PARTICULIER.equals(form.getInscripteur().getType());
  }

  public Boolean getTypeServiceSocialOuAutre() {
    return TypeInscripteur.SERVICE_SOCIAL.equals(form.getInscripteur().getType())
        || TypeInscripteur.AUTRE.equals(form.getInscripteur().getType());
  }

  public Boolean getInscripteurEstResponsableLegal() {
    return form.getInscripteurEstResponsableLegal();
  }

  public FamilleAccueil getFormFamilleAccueil() {
    return formFamilleAccueil;
  }

  public void setFormFamilleAccueil(FamilleAccueil formFamilleAccueil) {
    this.formFamilleAccueil = formFamilleAccueil;
  }
}
