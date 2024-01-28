package fr.famivac.gestionnaire.web.accompagnateurs;

import fr.famivac.gestionnaire.domains.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
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
@FacesConverter("accompagnateurConverter")
public class AccompagnateurConverter implements Converter<Accompagnateur> {

  @Inject
  private AccompagnateurService service;

  @Override
  public Accompagnateur getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    Accompagnateur bean = service.get(Long.valueOf(value));
    return bean;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Accompagnateur value) {
    if (value == null || ((Accompagnateur) value).getId() == null) {
      return null;
    }
    return ((Accompagnateur) value).getId().toString();
  }

}
