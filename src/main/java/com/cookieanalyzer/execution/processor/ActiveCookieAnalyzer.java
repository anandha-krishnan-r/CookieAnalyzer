package com.cookieanalyzer.execution.processor;

import static com.cookieanalyzer.data.domain.ProcessType.MOST_ACTIVE_COOKIE;
import static com.cookieanalyzer.execution.processor.ActiveCookieAnalyzeFunctions.getMostActiveCookieForReqDate;
import static java.lang.String.format;

import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.data.domain.ProcessType;
import com.cookieanalyzer.execution.CookieDataAnalyzer;
import com.cookieanalyzer.execution.datafetcher.CsvLogFileFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link CookieDataAnalyzer} for analyzing active cookies.
 */
public class ActiveCookieAnalyzer implements CookieDataAnalyzer<Set<Object>> {
    private static final Logger logger = LoggerFactory.getLogger(ActiveCookieAnalyzer.class);


    /**
     * Analyzes the cookie data based on the specified process type.
     *
     * @param cookieDataList the list of cookie data to be analyzed
     * @param userInput      the user input containing details about the analysis
     * @return a set of objects representing the analysis result
     * @throws UnsupportedOperationException if the specified process type is not supported
     */
    @Override
    public Set<Object> analyzeData(List<CookieData> cookieDataList, UserInput userInput) {
        logger.debug("Analysing the cookie data.. ");

        var processFunctionMap = getFunctionMap();

        if(processFunctionMap.containsKey(userInput.getProcessType())){

            var processFunction = processFunctionMap.get(userInput.getProcessType());
            return processFunction.apply(cookieDataList, userInput);
        } else {
            throw new UnsupportedOperationException(format("The given process %s is not supported",
                            userInput.getProcessType()));
        }
    }

    /**
     * Returns a mapping of process types to corresponding analysis functions.
     * <p> any new analyse function needs to be mapped here for processing.
     *
     * @return an {@link EnumMap} mapping process types to analysis functions
     */
    private EnumMap<ProcessType, BiFunction<List<CookieData>, UserInput, Set<Object>>> getFunctionMap(){
        var functionMap = new EnumMap<ProcessType, BiFunction<List<CookieData>, UserInput, Set<Object>>>(ProcessType.class);
        functionMap.put(MOST_ACTIVE_COOKIE, getMostActiveCookieForReqDate);
        return functionMap;
    }
}
