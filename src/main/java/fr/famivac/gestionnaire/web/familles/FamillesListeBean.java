package fr.famivac.gestionnaire.web.familles;

import fr.famivac.gestionnaire.domains.familles.boundary.FamilleService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class FamillesListeBean implements Serializable {

  private final FamilleService familleService;
  private LazyFamilleDataModel lazyModel;
  private List<String> periodesAccueil;

  private Boolean archivees;

  @Inject
  public FamillesListeBean(FamilleService familleService) {
    this.familleService = familleService;
  }

  @PostConstruct
  public void init() {
    archivees = false;
    periodesAccueil = new ArrayList<>();
    lazyModel = new LazyFamilleDataModel(familleService.search("", "", periodesAccueil, archivees));
  }

  public void rechercher() {
    lazyModel = new LazyFamilleDataModel(familleService.search("", "", periodesAccueil, archivees));
  }

  public void supprimer(Long id) {
    familleService.delete(id);
    init();
  }

  public void archiver(Long id) {
    familleService.archiver(id);
    init();
  }

  public void desarchiver(Long id) {
    familleService.desarchiver(id);
    init();
  }

  public LazyFamilleDataModel getLazyModel() {
    return lazyModel;
  }

  public List<String> getPeriodesAccueil() {
    return periodesAccueil;
  }

  public void setPeriodesAccueil(List<String> periodesAccueil) {
    this.periodesAccueil = periodesAccueil;
  }

  public Boolean getArchivees() {
    return archivees;
  }

  public void setArchivees(Boolean archivees) {
    this.archivees = archivees;
  }
}
