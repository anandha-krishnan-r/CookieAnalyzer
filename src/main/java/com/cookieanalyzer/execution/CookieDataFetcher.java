package com.cookieanalyzer.execution;

import java.util.List;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;

/**
 * Interface for fetching cookie data from a given data source for processing.
 */
public interface CookieDataFetcher {


    /**
     * Reads and parses cookie data from the provided data source in input {@link UserInput}.
     *
     * @param userInput the input data containing data source from which cookie data will be read.
     * @return a list of {@link CookieData} objects parsed from the data source.
     */
    List<CookieData> readData(UserInput userInput);

}
