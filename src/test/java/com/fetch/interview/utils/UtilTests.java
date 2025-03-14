package com.fetch.interview.utils;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTests {

    @Test
    public void testCountAlphaNumericChars() {
        assertEquals(6, Util.countAlphaNumericChars("Target"));
        assertEquals(14, Util.countAlphaNumericChars("M&M Corner Market"));
        assertEquals(0, Util.countAlphaNumericChars(""));
        assertEquals(0, Util.countAlphaNumericChars(null));
    }

    @Test
    public void testIsRoundDollar() {
        assertTrue(Util.isRoundDollar(new BigDecimal("9.00")));
        assertTrue(Util.isRoundDollar(new BigDecimal("35")));
        assertTrue(Util.isRoundDollar(new BigDecimal("0")));

        assertFalse(Util.isRoundDollar(new BigDecimal("35.35")));
    }

    @Test
    public void testIsMultipleOfQuarters() {
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("9.00")));
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("9.25")));
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("9.5")));
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("9.50")));
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("9.75")));
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("35")));
        assertTrue(Util.isMultipleOfQuarters(new BigDecimal("0")));

        assertFalse(Util.isMultipleOfQuarters(new BigDecimal("35.35")));
        assertFalse(Util.isMultipleOfQuarters(new BigDecimal("35.4")));
    }

    @Test
    public void testIsTimeHourInRange() {
        LocalTime begin = LocalTime.parse("14:00");
        LocalTime end = LocalTime.parse("16:00");
        assertTrue(Util.isTimeHourInRange(LocalTime.parse("14:01"), begin, end));
        assertTrue(Util.isTimeHourInRange(LocalTime.parse("14:30"), begin, end));
        assertTrue(Util.isTimeHourInRange(LocalTime.parse("15:59"), begin, end));


        assertFalse(Util.isTimeHourInRange(LocalTime.parse("14:00"), begin, end));
        assertFalse(Util.isTimeHourInRange(LocalTime.parse("16:00"), begin, end));
        assertFalse(Util.isTimeHourInRange(LocalTime.parse("13:59"), begin, end));
        assertFalse(Util.isTimeHourInRange(LocalTime.parse("16:01"), begin, end));
        assertFalse(Util.isTimeHourInRange(LocalTime.parse("11:00"), begin, end));
    }
}
