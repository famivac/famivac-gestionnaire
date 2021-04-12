package fr.famivac.gestionnaire.web.inscripteurs;

import fr.famivac.gestionnaire.domains.enfants.control.RetrieveInscripteursResponseDTO;
import fr.famivac.gestionnaire.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyInscripteurDataModel extends LazyDataModel<RetrieveInscripteursResponseDTO> {

  private final List<RetrieveInscripteursResponseDTO> datasource;

  public LazyInscripteurDataModel(List<RetrieveInscripteursResponseDTO> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public RetrieveInscripteursResponseDTO getRowData(String rowKey) {
    for (RetrieveInscripteursResponseDTO bean : datasource) {
      if (Long.valueOf(rowKey).equals(bean.getId())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public Object getRowKey(RetrieveInscripteursResponseDTO bean) {
    return bean.getId();
  }

  @Override
  public List<RetrieveInscripteursResponseDTO> load(
      int first,
      int pageSize,
      String sortField,
      SortOrder sortOrder,
      Map<String, FilterMeta> filterBy) {
    setRowCount(datasource.size());
    if (sortField != null) {
      Collections.sort(
          datasource,
          new LazySorter<>(RetrieveInscripteursResponseDTO.class, sortField, sortOrder));
    }
    int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
    return datasource.subList(first, max);
  }
}
