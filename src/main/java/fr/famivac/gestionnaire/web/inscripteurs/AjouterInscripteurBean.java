package fr.famivac.gestionnaire.web.inscripteurs;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.domains.enfants.boundary.InscripteurService;
import fr.famivac.gestionnaire.domains.enfants.entity.Inscripteur;
import fr.famivac.gestionnaire.domains.enfants.entity.TypeInscripteur;
import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterInscripteurBean implements Serializable {

  private List<Commune> communes;

  private Inscripteur form;

  @Inject
  private CommuneService communeService;

  @Inject
  private InscripteurService inscripteurService;

  public void init() {
    form = new Inscripteur();
    communes = communeService.retrieve();
  }

  public String ajouter() {
    Long id = inscripteurService.create(form);
    return "/inscripteurs/details.xhtml?id=" + id + "&faces-redirect=true";
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
    return TypeInscripteur.PARTICULIER.equals(form.getType());
  }

  public Boolean getTypeServiceSocialOuAutre() {
    return TypeInscripteur.SERVICE_SOCIAL.equals(form.getType())
        || TypeInscripteur.AUTRE.equals(form.getType());
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

  public Inscripteur getForm() {
    return form;
  }

  public void setForm(Inscripteur form) {
    this.form = form;
  }
}
