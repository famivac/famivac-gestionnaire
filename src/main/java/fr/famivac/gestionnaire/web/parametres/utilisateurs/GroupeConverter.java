package fr.famivac.gestionnaire.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.domains.utilisateurs.control.GroupeService;
import fr.famivac.gestionnaire.domains.utilisateurs.entity.Groupe;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ApplicationScoped
@FacesConverter("groupeConverter")
public class GroupeConverter implements Converter<Groupe> {

  @Inject
  private GroupeService groupeService;

  @Override
  public Groupe getAsObject(FacesContext context, UIComponent component, String value) {
    return groupeService.retrieve(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Groupe value) {
    if (value == null) {
      return null;
    }
    return ((Groupe) value).getNom();
  }

}
