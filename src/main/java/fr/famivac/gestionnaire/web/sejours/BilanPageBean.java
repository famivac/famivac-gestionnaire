package fr.famivac.gestionnaire.web.sejours;

import fr.famivac.gestionnaire.commons.utils.DateUtils;
import fr.famivac.gestionnaire.domains.sejours.control.BilanDTO;
import fr.famivac.gestionnaire.domains.sejours.control.SejourService;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class BilanPageBean implements Serializable {

  @Inject
  private SejourService sejourService;

  private BilanDTO bilan;

  private Date dateDebut;

  private Date dateFin;

  public void init() {
    LocalDate now = LocalDate.now();
    dateDebut = DateUtils.firstDay(now);
    dateFin = DateUtils.lastDay(now);
    bilan = rechercher(dateDebut, dateFin);
  }

  public void refresh() {
    bilan = rechercher(dateDebut, dateFin);
  }

  private BilanDTO rechercher(Date dateDebut, Date dateFin) {
    return sejourService.getBilanSurLaPeriode(dateDebut, dateFin);
  }

  public BilanDTO getBilan() {
    return bilan;
  }

  public Date getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }

}
