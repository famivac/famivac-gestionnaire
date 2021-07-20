package fr.famivac.gestionnaire.web.sejours;

import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantDTO;
import fr.famivac.gestionnaire.domains.enfants.boundary.EnfantService;
import fr.famivac.gestionnaire.domains.familles.boundary.FamilleResult;
import fr.famivac.gestionnaire.domains.familles.boundary.FamilleService;
import fr.famivac.gestionnaire.domains.parametres.control.ForfaitJournalierService;
import fr.famivac.gestionnaire.domains.parametres.control.FraisDossierService;
import fr.famivac.gestionnaire.domains.parametres.control.FraisPensionFamilleJournalierService;
import fr.famivac.gestionnaire.domains.parametres.control.FraisVoyageService;
import fr.famivac.gestionnaire.domains.sejours.control.SejourService;
import fr.famivac.gestionnaire.domains.sejours.entity.PeriodeJournee;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/** @author paoesco */
@Named
@ViewScoped
public class AjouterSejourBean implements Serializable {

  private static final long serialVersionUID = 3521762625440433062L;

  @Inject private FamilleService familleService;

  @Inject private EnfantService enfantService;

  @Inject private SejourService sejourService;

  @Inject private ForfaitJournalierService fraisDeSejourJournalierService;

  @Inject private FraisDossierService fraisDossierService;

  @Inject private FraisVoyageService fraisVoyageService;

  @Inject private FraisPensionFamilleJournalierService fraisPensionFamilleJournalierService;

  private AjouterSejourForm form;

  public void init() {
    form = new AjouterSejourForm();
  }

  public String ajouter() {
    BigDecimal montantFraisSejourJournalier =
        fraisDeSejourJournalierService
            .getCurrentMontant(form.getDateDebut())
            .orElse(BigDecimal.ZERO);
    BigDecimal montantFraisDossier =
        fraisDossierService.getCurrentMontant(form.getDateDebut()).orElse(BigDecimal.ZERO);
    BigDecimal montantFraisVoyage =
        fraisVoyageService.getCurrentMontant(form.getDateDebut()).orElse(BigDecimal.ZERO);
    BigDecimal montantFraisPensionFamilleJournalier =
        fraisPensionFamilleJournalierService
            .getCurrentMontant(form.getDateDebut())
            .orElse(BigDecimal.ZERO);
    Long sejourId =
        sejourService.create(
            form.getFamille().getId(),
            form.getFamille().getNomReferent(),
            form.getFamille().getPrenomReferent(),
            form.getFamille().getTelephoneReferent(),
            form.getEnfant().getId(),
            form.getEnfant().getNomEnfant(),
            form.getEnfant().getPrenomEnfant(),
            form.getDateDebut(),
            PeriodeJournee.valueOf(form.getPeriodeJourneeDebut()),
            form.getDateFin(),
            PeriodeJournee.valueOf(form.getPeriodeJourneeFin()),
            montantFraisSejourJournalier,
            montantFraisDossier,
            montantFraisVoyage,
            montantFraisPensionFamilleJournalier);
    return "/fr/famivac/gestionnaire/domains/sejours/details.xhtml?id="
        + sejourId
        + "&faces-redirect=true";
  }

  public List<FamilleResult> completeFamille(String query) {
    if (query == null || query.isEmpty()) {
      return familleService.search("%", "%", null, false);
    }
    return familleService.search(query, "%", null, false);
  }

  public List<EnfantDTO> completeEnfant(String query) {
    if (query == null || query.isEmpty()) {
      return enfantService.retrieve("%", "%");
    }
    return enfantService.retrieve(query, "%");
  }

  public AjouterSejourForm getForm() {
    return form;
  }

  public void setForm(AjouterSejourForm form) {
    this.form = form;
  }
}
