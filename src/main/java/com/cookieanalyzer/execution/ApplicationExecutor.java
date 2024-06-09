package com.cookieanalyzer.execution;

import java.util.List;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class that orchestrates the execution of various stages of cookie data analysis.
 *
 * @param <I> the type of the user input to be parsed for processing.
 * @param <O> the type of the processed result
 */
public abstract class ApplicationExecutor<I, O> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExecutor.class);

    private final InputParser<I> inputParser;

    private final CookieDataFetcher cookieDataFetcher;

    private final CookieDataAnalyzer<O> cookieDataAnalyzer;

    private final ResultWriter<O> resultWriter;


    public ApplicationExecutor(InputParser<I> inputParser,
                    CookieDataFetcher cookieDataFetcher,
                    CookieDataAnalyzer<O> cookieDataAnalyzer,
                    ResultWriter<O> resultWriter){
        this.inputParser = inputParser;
        this.cookieDataFetcher = cookieDataFetcher;
        this.cookieDataAnalyzer = cookieDataAnalyzer;
        this.resultWriter = resultWriter;
    }

    /**
     * Executes the workflow for processing the cookie data.
     * <p>
     *     The workflow contains following steps:
     *     1.Parse raw user request into a structured format. {@link InputParser}
     *     2.Fetch cookie data from data source like csv file for processing. {@link CookieDataFetcher}
     *     3.Analyze cookie data to generate user requested result. {@link CookieDataAnalyzer}
     *     4.Write the result to the specified output stream such as console. {@link ResultWriter}
     *
     * @param rawInput the raw user input to be processed
     */
    public void execute(I rawInput){
        try {
            logger.info("Starting execution");

            final UserInput userInput = inputParser.parseInput(rawInput);
            final List<CookieData> cookieData = cookieDataFetcher.readData(userInput);
            final O processedResult = cookieDataAnalyzer.analyzeData(cookieData, userInput);
            resultWriter.writeResult(processedResult);

            logger.info("Execution completed successfully!");
        }
        catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }

}
