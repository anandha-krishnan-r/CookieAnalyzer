package com.cookieanalyzer.app;

import java.util.Set;
import com.cookieanalyzer.execution.CookieDataFetcher;
import com.cookieanalyzer.execution.CookieDataAnalyzer;
import com.cookieanalyzer.execution.InputParser;
import com.cookieanalyzer.execution.ResultWriter;
import com.cookieanalyzer.execution.inputparser.CLIInputParser;
import com.cookieanalyzer.execution.processor.ActiveCookieAnalyzer;
import com.cookieanalyzer.execution.datafetcher.CsvLogFileFetcher;
import com.cookieanalyzer.execution.resultwriter.CLIResultWriter;

/**
 * Entry point for the cookie data analyzer application.
 */
public class Application {

    /**
     * Main method that sets up the components and executes the application.
     *
     * @param userInput the raw user input arguments passed to the application
     */
    public static void main(String[] userInput){

        final InputParser<String[]> inputParser = new CLIInputParser();
        final CookieDataFetcher cookieDataFetcher = new CsvLogFileFetcher();
        final CookieDataAnalyzer<Set<Object>> cookieDataAnalyzer = new ActiveCookieAnalyzer();
        final ResultWriter<Set<Object>> resultWriter = new CLIResultWriter(System.out);

        ApplicationCLIExecutor executor = new ApplicationCLIExecutor(inputParser, cookieDataFetcher, cookieDataAnalyzer, resultWriter);

        executor.execute(userInput);
    }

}
