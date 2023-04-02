package fr.famivac.gestionnaire.web.parametres.fraisvoyage;

import fr.famivac.gestionnaire.domains.parametres.entity.FraisVoyage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/** @author paoesco */
public class LazyFraisVoyageDataModel extends LazyDataModel<FraisVoyage> {

  private final List<FraisVoyage> datasource;

  public LazyFraisVoyageDataModel(List<FraisVoyage> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public FraisVoyage getRowData(String rowKey) {
    for (FraisVoyage entity : datasource) {
      if (Long.valueOf(rowKey).equals(entity.getId())) {
        return entity;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(FraisVoyage bean) {
    return bean.getId().toString();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<FraisVoyage> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    return datasource.stream().skip(first).limit(pageSize).collect(Collectors.toList());
  }

  public FraisVoyage getRowData(int rowIndex) {
    if (rowIndex > datasource.size()) {
      throw new IllegalArgumentException();
    }
    return datasource.get(rowIndex);
  }
}
