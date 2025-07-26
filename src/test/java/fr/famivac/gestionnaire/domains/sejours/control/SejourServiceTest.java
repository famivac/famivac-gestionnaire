package fr.famivac.gestionnaire.domains.sejours.control;

import fr.famivac.gestionnaire.domains.sejours.entity.PeriodeJournee;
import fr.famivac.gestionnaire.domains.sejours.entity.Sejour;
import fr.famivac.gestionnaire.domains.sejours.entity.SejourRepository;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author paoesco
 */
@ExtendWith(MockitoExtension.class)
public class SejourServiceTest {
    
    @Mock
    private SejourRepository repository;
    
    @InjectMocks
    private SejourService service;
    
    @Test
    @Disabled
    public void testGetBilanSurLaPeriode() {
        // GIVEN
        Date dateDebut = toDate("01/01/2018");
        Date dateFin = toDate("31/01/2018");
        initSejours();

        // WHEN
        BilanDTO bilan = service.getBilanSurLaPeriode(dateDebut, dateFin);

        // THEN
        Assertions.assertEquals(dateDebut, bilan.getDateDebut());
        Assertions.assertEquals(dateFin, bilan.getDateFin());
        Assertions.assertEquals(62, bilan.getTotalNombreJourneesVacances().intValue());
        Assertions.assertEquals(50, bilan.getTotalFraisDossier().intValue());
        Assertions.assertEquals(2170, bilan.getTotalForfait().intValue());
        Assertions.assertEquals(90, bilan.getTotalFraisVoyage().intValue());
        Assertions.assertEquals(93, bilan.getTotalFraisPensionFamille().intValue());
    }

    private void initSejours() {
        List<Sejour> sejours = new ArrayList<>();
        Sejour sejour1 = new Sejour(
                toDate("01/01/2018"),
                PeriodeJournee.MATIN,
                toDate("31/01/2018"),
                PeriodeJournee.APRES_MIDI)
                .withFraisDossier(BigDecimal.valueOf(10))
                .withFraisSejourJournalier(BigDecimal.valueOf(20))
                .withFraisVoyage(BigDecimal.valueOf(30))
                .withFraisPensionFamilleJournalier(BigDecimal.valueOf(1));
        sejours.add(sejour1);
        Sejour sejour2 = new Sejour(
                toDate("01/01/2018"),
                PeriodeJournee.MATIN,
                toDate("31/01/2018"),
                PeriodeJournee.APRES_MIDI)
                .withFraisDossier(BigDecimal.valueOf(40))
                .withFraisSejourJournalier(BigDecimal.valueOf(50))
                .withFraisVoyage(BigDecimal.valueOf(60))
                .withFraisPensionFamilleJournalier(BigDecimal.valueOf(2));
        sejours.add(sejour2);
        Mockito.when(repository.getSejoursTerminesDansLaPeriode(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(sejours);
    }
    
    private Date toDate(String source) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(source);
        } catch (ParseException ex) {
            Assertions.fail(ex.getMessage());
            return null;
        }
    }
    
}
