package fr.famivac.gestionnaire.web.parametres.utilisateurs;

import fr.famivac.gestionnaire.domains.utilisateurs.control.RetrieveUtilisateursDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/** @author paoesco */
public class LazyUtilisateurDataModel extends LazyDataModel<RetrieveUtilisateursDTO> {

  private final List<RetrieveUtilisateursDTO> datasource;

  public LazyUtilisateurDataModel(List<RetrieveUtilisateursDTO> datasource) {
    this.datasource = new ArrayList<>(datasource);
  }

  @Override
  public RetrieveUtilisateursDTO getRowData(String rowKey) {
    for (RetrieveUtilisateursDTO dto : datasource) {
      if (rowKey.equals(dto.getLogin())) {
        return dto;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(RetrieveUtilisateursDTO bean) {
    return bean.getLogin();
  }

  @Override
  public int count(Map<String, FilterMeta> map) {
    return datasource.size();
  }

  @Override
  public List<RetrieveUtilisateursDTO> load(
      int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    return datasource.stream().skip(first).limit(pageSize).collect(Collectors.toList());
  }

  public RetrieveUtilisateursDTO getRowData(int rowIndex) {
    if (rowIndex > datasource.size()) {
      throw new IllegalArgumentException();
    }
    return datasource.get(rowIndex);
  }
}
