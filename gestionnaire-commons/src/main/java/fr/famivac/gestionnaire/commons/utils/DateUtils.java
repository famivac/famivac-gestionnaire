package fr.famivac.gestionnaire.commons.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Classe utilitaire de manipulation des dates.
 *
 * @author paoesco
 */
public final class DateUtils {

    private static final long ONE_HOUR_IN_MS = 3600000;
    private static final long ONE_MIN_IN_MS = 60000;
    private static final long ONE_SEC_IN_MS = 1000;

    private DateUtils() {
    }

    /**
     * Indique si une date est comprise entre deux dates.
     * <ul>
     * <li>la date de max est exclusive.<li>
     * <li>les heures minutes secondes ne sont pas prises en compte (retirées
     * avant le test).<li>
     * </ul>
     *
     * @param date
     * @param minInclusive
     * @param maxExclusive
     * @return
     */
    public static boolean between(Date date, Date minInclusive, Date maxExclusive) {
        Date working = removeTime(date);
        Date workingMin = removeTime(minInclusive);
        Date workingMax = removeTime(maxExclusive);
        return workingMin.getTime() <= working.getTime() && working.getTime() < workingMax.getTime();
    }

    /**
     * Teste si la date 1 est antérieure à la date 2. Les
     * heures/minutes/secondes ne sont pas prises en compte. Dans le cas du même
     * jour, on retourne false.
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean before(Date d1, Date d2) {
        Date workingD1 = removeTime(d1);
        Date workingD2 = removeTime(d2);
        return workingD1.compareTo(workingD2) < 0;
    }

    /**
     * Teste si la date 1 est postérieure à la date 2. Les
     * heures/minutes/secondes ne sont pas prises en compte. Dans le cas du même
     * jour, on retourne true.
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean after(Date d1, Date d2) {
        return (!before(d1, d2));
//        Date workingD1 = removeTime(d1);
//        Date workingD2 = removeTime(d2);
//        return workingD1.compareTo(workingD2) >= 0;
    }

    /**
     * Transformation d'une java.util.Date en java.time.LocalDate.
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Date toDate(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date sumTimeToDate(Date date, int hours, int mins, int secs) {
        if (date == null) {
            return null;
        }
        long hoursToAddInMs = hours * ONE_HOUR_IN_MS;
        long minsToAddInMs = mins * ONE_MIN_IN_MS;
        long secsToAddInMs = secs * ONE_SEC_IN_MS;
        return new Date(date.getTime() + hoursToAddInMs + minsToAddInMs + secsToAddInMs);
    }

    /**
     * Retire les heures/minutes/secondes/millisecondes d'une date.
     *
     * @param date
     * @return
     */
    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date firstDay(LocalDate date) {
        return toDate(date.with(firstDayOfMonth()));
    }

    public static Date lastDay(LocalDate date) {
        return toDate(date.with(lastDayOfMonth()));
    }

}
