package fr.famivac.gestionnaire.web.parametres.fraispensionfamillejournalier;

import fr.famivac.gestionnaire.domains.parametres.entity.FraisPensionFamilleJournalier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/** @author paoesco */
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
  public Object getRowKey(FraisPensionFamilleJournalier bean) {
    return bean.getId();
  }

  @Override
  public List<FraisPensionFamilleJournalier> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    int max = first + pageSize > datasource.size() ? datasource.size() : first + pageSize;
    setRowCount(datasource.size());
    return datasource.subList(first, max);
  }

  public FraisPensionFamilleJournalier getRowData(int rowIndex) {
    if (rowIndex > datasource.size()) {
      throw new IllegalArgumentException();
    }
    return datasource.get(rowIndex);
  }
}
