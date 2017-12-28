package fr.famivac.gestionnaire.sejours.control;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author paoesco
 */
public class BilanDTO {

    private Date dateDebut;
    private Date dateFin;
    private Integer totalNombreJourneesVacances;
    private BigDecimal totalFraisSejour;
    private BigDecimal totalFraisDossier;

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

    public Integer getTotalNombreJourneesVacances() {
        return totalNombreJourneesVacances;
    }

    public void setTotalNombreJourneesVacances(Integer totalNombreJourneesVacances) {
        this.totalNombreJourneesVacances = totalNombreJourneesVacances;
    }

    public BigDecimal getTotalFraisSejour() {
        return totalFraisSejour;
    }

    public void setTotalFraisSejour(BigDecimal totalFraisSejour) {
        this.totalFraisSejour = totalFraisSejour;
    }

    public BigDecimal getTotalFraisDossier() {
        return totalFraisDossier;
    }

    public void setTotalFraisDossier(BigDecimal totalFraisDossier) {
        this.totalFraisDossier = totalFraisDossier;
    }

}
