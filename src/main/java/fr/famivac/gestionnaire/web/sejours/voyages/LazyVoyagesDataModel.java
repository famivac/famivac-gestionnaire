package fr.famivac.gestionnaire.web.sejours.voyages;

import fr.famivac.gestionnaire.domains.sejours.control.VoyageDTO;
import fr.famivac.gestionnaire.web.utils.LazyFilter;
import fr.famivac.gestionnaire.web.utils.LazySorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import liquibase.pro.packaged.O;
import org.apache.commons.collections4.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/** @author paoesco */
public class LazyVoyagesDataModel extends LazyDataModel<VoyageDTO> {

  private static final long serialVersionUID = 6160793949461059501L;

  private final List<VoyageDTO> datasource;

  public LazyVoyagesDataModel(List<VoyageDTO> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public VoyageDTO getRowData(String rowKey) {
    for (VoyageDTO bean : datasource) {
      if (Long.valueOf(rowKey).equals(bean.getVoyageId())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(VoyageDTO bean) {
    return bean.getVoyageId().toString();
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    var filter = new LazyFilter<>(filterBy.values());
    return (int) datasource.stream().filter(filter).count();
  }

  @Override
  public List<VoyageDTO> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    var filter = new LazyFilter<>(filterBy.values());
    List<Comparator<VoyageDTO>> comparators =
        sortBy.values().stream()
            .map(o -> new LazySorter<>(VoyageDTO.class, o.getField(), o.getOrder()))
            .collect(Collectors.toList());
    var comparator = ComparatorUtils.chainedComparator(comparators);

    return datasource.stream()
        .filter(filter)
        .sorted(comparator)
        .skip(first)
        .limit(pageSize)
        .collect(Collectors.toList());
  }
}
