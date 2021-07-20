package fr.famivac.gestionnaire.web.enfants;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantService;
import fr.famivac.gestionnaire.domains.enfants.boundary.InscripteurService;
import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;
import fr.famivac.gestionnaire.domains.enfants.entity.Inscripteur;
import fr.famivac.gestionnaire.domains.enfants.entity.TypeInscripteur;
import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/** @author paoesco */
@Named
@ViewScoped
public class AjouterEnfantBean implements Serializable {

  private Long inscripteurId;

  private List<Commune> communes;

  private Enfant form;

  @Inject private CommuneService communeService;

  @Inject private EnfantService enfantService;

  @Inject private InscripteurService inscripteurService;

  public void init() {
    form = new Enfant();
    Inscripteur inscripteur = inscripteurService.retrieve(inscripteurId);
    form.setInscripteur(inscripteur);
    communes = communeService.retrieve();
  }

  public String ajouter() {
    Long id = enfantService.create(form);
    return "/enfants/details.xhtml?id=" + id + "&faces-redirect=true";
  }

  public List<Commune> completeCommune(String query) {
    if (query == null || query.isEmpty()) {
      return communes;
    } else {
      return communes.stream()
          .filter(
              (Commune t) -> {
                return t.getLabel().toLowerCase().trim().contains(query.toLowerCase().trim());
              })
          .collect(Collectors.toList());
    }
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

  public List<Commune> getCommunes() {
    return communes;
  }

  public void setCommunes(List<Commune> communes) {
    this.communes = communes;
  }

  public CommuneService getCommuneService() {
    return communeService;
  }

  public Enfant getForm() {
    return form;
  }

  public void setForm(Enfant form) {
    this.form = form;
  }

  public Long getInscripteurId() {
    return inscripteurId;
  }

  public void setInscripteurId(Long inscripteurId) {
    this.inscripteurId = inscripteurId;
  }
}
