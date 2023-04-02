package fr.famivac.gestionnaire.web.communes;

import fr.famivac.gestionnaire.commons.entity.Commune;
import fr.famivac.gestionnaire.commons.utils.AlphanumComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/** @author paoesco */
public class LazyCommuneDataModel extends LazyDataModel<Commune> {

  private final List<Commune> datasource;

  private final Comparator alphanumComparator;

  public LazyCommuneDataModel(List<Commune> datasource) {
    alphanumComparator = new AlphanumComparator();
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public Commune getRowData(String rowKey) {
    for (Commune bean : datasource) {
      if (rowKey.equals(bean.getCode())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(Commune bean) {
    return bean.getCode();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<Commune> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    return datasource.stream()
        .sorted((Commune o1, Commune o2) -> alphanumComparator.compare(o1.getCode(), o2.getCode()))
        .skip(first)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public boolean contains(Commune bean) {
    return datasource.contains(bean);
  }

  public Commune getRowData(int rowIndex) {
    if (rowIndex > datasource.size()) {
      throw new IllegalArgumentException();
    }
    return datasource.get(rowIndex);
  }
}
