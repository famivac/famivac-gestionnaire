package fr.famivac.gestionnaire.web.parametres.fraisdossier;

import fr.famivac.gestionnaire.domains.parametres.entity.FraisDossier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

public class LazyFraisDossierDataModel extends LazyDataModel<FraisDossier> {

  private final List<FraisDossier> datasource;

  public LazyFraisDossierDataModel(List<FraisDossier> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public FraisDossier getRowData(String rowKey) {
    for (FraisDossier entity : datasource) {
      if (Long.valueOf(rowKey).equals(entity.getId())) {
        return entity;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(FraisDossier bean) {
    return bean.getId().toString();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<FraisDossier> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    return datasource.stream().skip(first).limit(pageSize).collect(Collectors.toList());
  }

  public FraisDossier getRowData(int rowIndex) {
    if (rowIndex > datasource.size()) {
      throw new IllegalArgumentException();
    }
    return datasource.get(rowIndex);
  }
}
