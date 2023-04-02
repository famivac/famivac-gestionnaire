package fr.famivac.gestionnaire.web.sejours;

import fr.famivac.gestionnaire.domains.sejours.control.SejourDTO;
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

public class LazySejourDataModel extends LazyDataModel<SejourDTO> {

  private final List<SejourDTO> datasource;

  public LazySejourDataModel(List<SejourDTO> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public SejourDTO getRowData(String rowKey) {
    for (SejourDTO bean : datasource) {
      if (Long.valueOf(rowKey).equals(bean.getId())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(SejourDTO bean) {
    return bean.getId().toString();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<SejourDTO> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    List<Comparator<SejourDTO>> comparators =
        sortBy.values().stream()
            .map(o -> new LazySorter<>(SejourDTO.class, o.getField(), o.getOrder()))
            .collect(Collectors.toList());
    var comparator = ComparatorUtils.chainedComparator(comparators);

    return datasource.stream()
        .sorted(comparator)
        .skip(first)
        .limit(pageSize)
        .collect(Collectors.toList());
  }
}
