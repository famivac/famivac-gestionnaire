package fr.famivac.gestionnaire.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableRenderer;

public class PatchedDataTableRenderer extends DataTableRenderer {
  @Override
  public void decode(FacesContext context, UIComponent component) {
    ((DataTable) component).setSortByAsMap(null); // PF #6987: Clear out unsaved transient state.
    super.decode(context, component);
  }
}
