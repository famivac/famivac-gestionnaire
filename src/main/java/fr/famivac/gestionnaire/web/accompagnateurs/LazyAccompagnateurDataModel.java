package fr.famivac.gestionnaire.web.accompagnateurs;

import fr.famivac.gestionnaire.domains.sejours.entity.Accompagnateur;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

public class LazyAccompagnateurDataModel extends LazyDataModel<Accompagnateur> {

  private final List<Accompagnateur> datasource;

  public LazyAccompagnateurDataModel(List<Accompagnateur> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public Accompagnateur getRowData(String rowKey) {
    for (Accompagnateur bean : datasource) {
      if (Long.valueOf(rowKey).equals(bean.getId())) {
        return bean;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(Accompagnateur bean) {
    return bean.getId().toString();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<Accompagnateur> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    return datasource.stream().skip(first).limit(pageSize).collect(Collectors.toList());
  }
}
