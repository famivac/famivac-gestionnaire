package fr.famivac.gestionnaire.web.utils;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author paoesco
 */
@FacesConverter(forClass = String.class)
public class StringTrimConverter implements Serializable, javax.faces.convert.Converter<String> {

	private static final long serialVersionUID = 4990397280435716896L;

	@Override
    public String getAsObject(FacesContext context, UIComponent cmp, String value) {
        if (value != null && cmp instanceof HtmlInputText) {
            // trim the entered value in a HtmlInputText before doing validation/updating the model
            return value.trim();
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent cmp, String value) {
        if (value != null) {
            // return the value as is for presentation
            return value.toString();
        }
        return null;
    }

}
