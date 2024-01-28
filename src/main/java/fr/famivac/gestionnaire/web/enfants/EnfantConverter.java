package fr.famivac.gestionnaire.web.enfants;

import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantDTO;
import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantService;
import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;
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
@FacesConverter("enfantConverter")
public class EnfantConverter implements Converter<EnfantDTO> {

  @Inject
  private EnfantService service;

  @Override
  public EnfantDTO getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    Enfant bean = service.search(Long.valueOf(value));
    return new EnfantDTO(bean);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, EnfantDTO value) {
    if (value == null) {
      return null;
    }
    return String.valueOf(((EnfantDTO) value).getId());
  }
}
