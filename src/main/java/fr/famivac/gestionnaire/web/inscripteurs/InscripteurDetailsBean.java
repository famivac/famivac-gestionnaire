package fr.famivac.gestionnaire.web.inscripteurs;

import fr.famivac.gestionnaire.domains.enfants.boundary.InscripteurService;
import fr.famivac.gestionnaire.domains.enfants.entity.Inscripteur;
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
public class InscripteurDetailsBean implements Serializable, CompleteCommune {

  /**
   *
   */
  private static final long serialVersionUID = -2554779234643713339L;

  private Long id;

  private Inscripteur form;

  @Inject
  private CommuneService communeService;

  @Inject
  private InscripteurService inscripteurService;

  public void init() {
    form = inscripteurService.retrieve(id);
  }

  public void update() {
    inscripteurService.update(form);
    FacesContext.getCurrentInstance()
        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
  }

  public void supprimerEnfant(Long enfantId) {
    inscripteurService.retirerEnfant(enfantId);
    init();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Inscripteur getForm() {
    return form;
  }

  public void setForm(Inscripteur form) {
    this.form = form;
  }

  @Override
  public CommuneService getCommuneService() {
    return communeService;
  }

  public Boolean getTypeInscripteurParticulier() {
    return TypeInscripteur.PARTICULIER.equals(form.getType());
  }

  public Boolean getTypeServiceSocialOuAutre() {
    return TypeInscripteur.SERVICE_SOCIAL.equals(form.getType())
        || TypeInscripteur.AUTRE.equals(form.getType());
  }
}
