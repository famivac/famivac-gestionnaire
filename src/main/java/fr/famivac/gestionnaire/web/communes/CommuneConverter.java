package fr.famivac.gestionnaire.web.communes;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.domains.parametres.CommuneService;
import java.util.Objects;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter("communeConverter")
public class CommuneConverter implements Converter<Commune> {

  private final CommuneService communeService;

  @Inject
  public CommuneConverter(CommuneService communeService) {
    this.communeService = communeService;
  }

  @Override
  public Commune getAsObject(FacesContext context, UIComponent component, String value) {
    return communeService.retrieve(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Commune value) {
    return Objects.isNull(value) ? null : value.getCode();
  }
}
