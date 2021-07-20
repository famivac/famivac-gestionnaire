package fr.famivac.gestionnaire.web.inscripteurs;

import fr.famivac.gestionnaire.domains.enfants.boundary.RetrieveInscripteursResponseDTO;
import fr.famivac.gestionnaire.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

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
  public String getRowKey(RetrieveInscripteursResponseDTO bean) {
    return bean.getId().toString();
  }

  @Override
  public List<RetrieveInscripteursResponseDTO> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    List<Comparator<RetrieveInscripteursResponseDTO>> comparators =
        sortBy.values().stream()
            .map(
                o ->
                    new LazySorter<>(
                        RetrieveInscripteursResponseDTO.class, o.getField(), o.getOrder()))
            .collect(Collectors.toList());
    var comparator = ComparatorUtils.chainedComparator(comparators);

    setRowCount(datasource.size());
    return datasource.stream()
        .sorted(comparator)
        .skip(first)
        .limit(pageSize)
        .collect(Collectors.toList());
  }
}
