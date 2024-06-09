package com.cookieanalyzer.execution;

import java.util.List;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.data.domain.ProcessType;

/**
 * Interface for analyze cookie data to generate requested result.
 *
 * @param <T> the type of the result produced by the processing
 */
public interface CookieDataAnalyzer<T> {

    /**
     * Analyze the given list of {@link CookieData} for given process type {@link ProcessType} in input.
     *
     * @param cookieDataList the list of cookie data to be processed
     * @param userInput user input data required for processing
     * @return the result of processing the cookie data
     */
    T analyzeData(List<CookieData> cookieDataList, UserInput userInput);

}
