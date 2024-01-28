package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.domains.familles.boundary.FamilleResult;
import fr.famivac.gestionnaire.domains.familles.boundary.FamilleService;
import fr.famivac.gestionnaire.domains.familles.entity.Famille;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter("familleConverter")
public class FamilleConverter implements Converter<FamilleResult> {

  private final FamilleService service;

  @Inject
  public FamilleConverter(FamilleService service) {
    this.service = service;
  }

  @Override
  public FamilleResult getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    Famille bean = service.get(Long.valueOf(value));
    return new FamilleResult(bean);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, FamilleResult value) {
    if (value == null) {
      return null;
    }
    return value.getId().toString();
  }
}
