package fr.famivac.gestionnaire.web.parametres.fraispensionfamillejournalier;

import fr.famivac.gestionnaire.domains.parametres.entity.FraisPensionFamilleJournalier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

public class LazyFraisPensionFamilleJournalierDataModel
    extends LazyDataModel<FraisPensionFamilleJournalier> {

  private final List<FraisPensionFamilleJournalier> datasource;

  public LazyFraisPensionFamilleJournalierDataModel(
      List<FraisPensionFamilleJournalier> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public FraisPensionFamilleJournalier getRowData(String rowKey) {
    for (FraisPensionFamilleJournalier entity : datasource) {
      if (Long.valueOf(rowKey).equals(entity.getId())) {
        return entity;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(FraisPensionFamilleJournalier bean) {
    return bean.getId().toString();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<FraisPensionFamilleJournalier> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    return datasource.stream().skip(first).limit(pageSize).collect(Collectors.toList());
  }

  public FraisPensionFamilleJournalier getRowData(int rowIndex) {
    if (rowIndex > datasource.size()) {
      throw new IllegalArgumentException();
    }
    return datasource.get(rowIndex);
  }
}
