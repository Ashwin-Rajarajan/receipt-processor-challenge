package com.fetch.interview.utils;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * Any common utilities required for the application can be put into util classes and published as a client
 * so that they can be reused across the organization. As use cases grow, the Util class can be split into different
 * utils like StringUtils, NumberUtils, DateUtils, etc.
 */
public class Util {

    public static final BigDecimal QUARTER = new BigDecimal("0.25");

    public static long countAlphaNumericChars(@Nullable String string) {
        if(string == null) return 0;
        return string.chars().filter(Character::isLetterOrDigit).count();
    }

    public static boolean isRoundDollar(@NotNull BigDecimal amount) {
        return amount.stripTrailingZeros().scale() == 0;
    }

    public static boolean isMultipleOfQuarters(@NotNull BigDecimal amount) {
        return amount.remainder(QUARTER).compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isTimeHourInRange(@NotNull LocalTime time, @NotNull LocalTime begin, @NotNull LocalTime end) {
        return time.isAfter(begin) && time.isBefore(end);
    }

}
