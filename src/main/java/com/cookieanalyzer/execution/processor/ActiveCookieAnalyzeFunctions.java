package com.cookieanalyzer.execution.processor;

import static java.util.Objects.nonNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;

/**
 *  A class containing functions to analyze the cookie data.
 */
public class ActiveCookieAnalyzeFunctions {


    /**
     * Function to retrieve the most active cookies for a requested date.
     */
    public static final BiFunction<List<CookieData>, UserInput, Set<Object>> getMostActiveCookieForReqDate
                    = (cookieDataList, inputData) -> {

        final Map<String, Long> cookieCountsOnDay = countCookies(cookieDataList, inputData.getRequestedDate());

        final Long maxCookieCount = cookieCountsOnDay.values()
                        .stream()
                        .max(Comparator.naturalOrder())
                        .orElseThrow(() -> new RuntimeException("No Cookie data found"));

        return cookieCountsOnDay.entrySet().stream()
                        .filter(cookieCountData -> maxCookieCount.equals(cookieCountData.getValue()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet());
    };

    /**
     * Counts the occurrences of each cookie for a specified date.
     *
     * @param cookieDataList the list of cookie data
     * @param requestedDate  the requested date
     * @return a map of cookie names to their counts
     */
    public static Map<String, Long> countCookies(
                    final List<CookieData> cookieDataList,
                    final LocalDate requestedDate){
        return cookieDataList.stream()
                        .filter(cookieData -> nonNull(cookieData.accessedTime()))
                        .filter(cookieData -> requestedDate.isEqual(cookieData.accessedTime().toLocalDate()))
                        .collect(Collectors.groupingBy(CookieData::cookie, Collectors.counting()));
    }

}
