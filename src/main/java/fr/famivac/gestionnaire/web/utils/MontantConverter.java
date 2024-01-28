package fr.famivac.gestionnaire.web.utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.math.BigDecimal;

/**
 * @author paoesco
 */
@FacesConverter
public class MontantConverter implements Converter<BigDecimal> {

  @Override
  public BigDecimal getAsObject(FacesContext context, UIComponent component, String value) {
    return new BigDecimal(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, BigDecimal value) {
    BigDecimal montant = value;
    return montant.toPlainString();
  }

}
