package com.cookieanalyzer.execution.util;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A utility class containing generic helper methods.
 */
public class MiscUtil {

    private MiscUtil(){}


    /**
     * Checks if the specified string value is empty.
     *
     * @param value the string value to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isEmpty(String value){
        return isNull(value) || value.isBlank();
    }

}
