package com.cookieanalyzer.execution;

import static java.util.Calendar.DECEMBER;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JunitBaseTest {
    public static final String FILE_PARAM = "-f";
    public static final String DATE_PARAM = "-d";
    public static final String PROCESS_TYPE_PARAM = "-pt";
    public static final String FILE_PATH = "cookie-log-1.csv";
    public static final String DATE_STRING = "2018-12-09";
    public static final String MOST_ACTIVE_COOKIE = "MOST_ACTIVE_COOKIE";
    
    public static final String COOKIE_1 = "AtY0laUfhglK3lC7";
    public static final String COOKIE_2 = "SAZuXPGUrfbcn5UA";
    public static final String COOKIE_3 = "4sMM2LxV07bPJzwf";
    public static final String COOKIE_4 = "9sMM2LxV07bPJzwf";
    
    public static final LocalDateTime DAY_1_TIMESTAMP_1 = LocalDate.of(2018, DECEMBER, 19)
                    .atTime(10, 9, 12);
    public static final LocalDateTime DAY_1_TIMESTAMP_2 = LocalDate.of(2018, DECEMBER, 19)
                    .atTime(11, 0, 12);
    public static final LocalDateTime DAY_2_TIMESTAMP_1 = LocalDate.of(2018, DECEMBER, 20)
                    .atTime(10, 9, 12);
    public static final LocalDateTime DAY_2_TIMESTAMP_2 = LocalDate.of(2018, DECEMBER, 20)
                    .atTime(19, 9, 12);


}
