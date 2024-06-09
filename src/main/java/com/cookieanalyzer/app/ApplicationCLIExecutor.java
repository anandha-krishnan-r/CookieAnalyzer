package com.cookieanalyzer.app;

import java.util.Set;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.execution.ApplicationExecutor;
import com.cookieanalyzer.execution.CookieDataFetcher;
import com.cookieanalyzer.execution.CookieDataAnalyzer;
import com.cookieanalyzer.execution.InputParser;
import com.cookieanalyzer.execution.ResultWriter;

/**
 * Executes the cookie data analysis workflow for command-line interface (CLI).
 */
public class ApplicationCLIExecutor extends ApplicationExecutor<String[], Set<Object>> {

    /**
     * Constructs an {@code ApplicationCLIExecutor} with the specified components.
     *
     * @param inputParser the parser to convert raw CLI input into {@link UserInput}
     * @param cookieDataFetcher the fetcher to read {@link CookieData} from {@link UserInput}
     * @param cookieDataAnalyzer the analyzer to process the list of {@link CookieData}
     * @param resultWriter the writer to write the processed result to the output
     */
    public ApplicationCLIExecutor(InputParser<String[]> inputParser,
                    CookieDataFetcher cookieDataFetcher,
                    CookieDataAnalyzer<Set<Object>> cookieDataAnalyzer,
                    ResultWriter<Set<Object>> resultWriter) {
        super(inputParser, cookieDataFetcher, cookieDataAnalyzer, resultWriter);
    }
}
