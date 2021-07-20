package fr.famivac.gestionnaire.web.enfants;

import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantDTO;
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

public class LazyEnfantDataModel extends LazyDataModel<EnfantDTO> {

  private final List<EnfantDTO> datasource;

  public LazyEnfantDataModel(List<EnfantDTO> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public EnfantDTO getRowData(String rowKey) {
    for (EnfantDTO bean : datasource) {
      if (Long.valueOf(rowKey).equals(bean.getId())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(EnfantDTO bean) {
    return bean.getId().toString();
  }

  @Override
  public List<EnfantDTO> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    List<Comparator<EnfantDTO>> comparators =
        sortBy.values().stream()
            .map(o -> new LazySorter<>(EnfantDTO.class, o.getField(), o.getOrder()))
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
