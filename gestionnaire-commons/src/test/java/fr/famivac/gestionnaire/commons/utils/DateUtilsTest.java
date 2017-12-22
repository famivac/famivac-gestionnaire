package fr.famivac.gestionnaire.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pescobar
 */
public class DateUtilsTest {

    private Date toDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return sdf.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtilsTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
            return null;
        }
    }

    @Test
    public void testBetweenMinInclusive() {
        Date toTest = toDate("01/01/2015 01:00");
        Date min = toDate("01/01/2015 02:00");
        Date max = toDate("31/01/2015 23:59");
        boolean result = DateUtils.between(toTest, min, max);
        Assert.assertTrue(result);
    }

    @Test
    public void testBetweenMaxExclusive() {
        Date toTest = toDate("31/01/2015 01:00");
        Date min = toDate("01/01/2015 02:00");
        Date max = toDate("31/01/2015 03:59");
        boolean result = DateUtils.between(toTest, min, max);
        Assert.assertFalse(result);
    }

    @Test
    public void testBeforeTrue() {
        Date d1 = toDate("01/01/2015 02:00");
        Date d2 = toDate("31/01/2015 03:59");
        boolean result = DateUtils.before(d1, d2);
        Assert.assertTrue(result);
    }

    @Test
    public void testBeforeFalse() {
        Date d1 = toDate("01/01/2015 02:00");
        Date d2 = toDate("31/01/2014 03:59");
        boolean result = DateUtils.before(d1, d2);
        Assert.assertFalse(result);
    }

    @Test
    public void testBeforeDateEqualsFalse() {
        Date d1 = toDate("01/01/2015 02:00");
        Date d2 = toDate("01/01/2015 03:59");
        boolean result = DateUtils.before(d1, d2);
        Assert.assertFalse(result);
    }

    @Test
    public void testAfterTrue() {
        Date d1 = toDate("31/01/2015 03:59");
        Date d2 = toDate("01/01/2015 02:00");
        boolean result = DateUtils.after(d1, d2);
        Assert.assertTrue(result);
    }

    @Test
    public void testAfterFalse() {
        Date d1 = toDate("31/01/2014 03:59");
        Date d2 = toDate("01/01/2015 02:00");
        boolean result = DateUtils.after(d1, d2);
        Assert.assertFalse(result);
    }

    @Test
    public void testAfterDateEqualsTrue() {
        Date d1 = toDate("01/01/2015 03:59");
        Date d2 = toDate("01/01/2015 02:00");
        boolean result = DateUtils.after(d1, d2);
        Assert.assertTrue(result);
    }

    @Test
    public void testToLocaleDate() {
        Date toTest = toDate("31/01/2015 01:00");
        LocalDate result = DateUtils.toLocalDate(toTest);
        Assert.assertEquals("2015-01-31", result.format(DateTimeFormatter.ISO_DATE));
    }

    @Test
    public void testSumTimeToDateAdd() {
        Date toTest = toDate("31/01/2015 01:00");
        Date result = DateUtils.sumTimeToDate(toTest, 1, 30, 5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Assert.assertEquals("31/01/2015 02:30:05", sdf.format(result));
    }

    @Test
    public void testSumTimeToDateSubstract() {
        Date toTest = toDate("31/01/2015 02:00");
        Date result = DateUtils.sumTimeToDate(toTest, -1, -30, -5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Assert.assertEquals("31/01/2015 00:29:55", sdf.format(result));
    }

    @Test
    public void testSumTimeToDateNull() {
        Date result = DateUtils.sumTimeToDate(null, -1, -30, -5);
        Assert.assertNull(result);
    }

    @Test
    public void testFirstDay() {
        LocalDate localDate = LocalDate.of(2017, Month.MARCH, 15);
        Date result = DateUtils.firstDay(localDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assert.assertEquals("01/03/2017", sdf.format(result));
    }

    @Test
    public void testLastDay() {
        LocalDate localDate = LocalDate.of(2017, Month.MARCH, 15);
        Date result = DateUtils.lastDay(localDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Assert.assertEquals("31/03/2017", sdf.format(result));
    }

}