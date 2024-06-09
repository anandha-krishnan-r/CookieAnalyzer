package com.cookieanalyzer.execution.inputparser;

import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.data.domain.ProcessType;
import com.cookieanalyzer.exception.CookieAnalyzerException;
import com.cookieanalyzer.execution.ApplicationExecutor;
import com.cookieanalyzer.execution.InputParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link InputParser} for parsing command-line interface (CLI) input.
 */
public class CLIInputParser implements InputParser<String[]> {

    private static final Logger logger = LoggerFactory.getLogger(CLIInputParser.class);

    private static final String FILE_PARAM = "-f";
    private static final String DATE_PARAM = "-d";
    private static final String PROCESS_PARAM = "-p";

    private static final String HYPHEN = "-";

    /**
     * Parses the raw user input from the command-line arguments into a {@link UserInput} object.
     *
     * @param rawUserInput the raw user input from the command-line arguments
     * @return the parsed {@link UserInput} object
     */
    @Override
    public UserInput parseInput(String[] rawUserInput) {
        logger.debug("Parsing user input from CLI...");
        var parsedInputData = parseToInputData(rawUserInput);
        this.validateInput(parsedInputData);
        return parsedInputData;
    }

    private UserInput parseToInputData(String[] rawUserInput){

        UserInput userInput = new UserInput();
        String keyParamHolder = null;

        for(String currentParam: rawUserInput){

            if(currentParam.startsWith(HYPHEN)){
                keyParamHolder = currentParam;
            } else if (nonNull(keyParamHolder)){
                assignInputData(keyParamHolder, currentParam, userInput);
                keyParamHolder = null;
            }

        }

        return userInput;
    }

    public void assignInputData(String keyParam, String valueParam, UserInput userInput){

        switch (purifyParam(keyParam)) {
        case FILE_PARAM -> ofNullable(valueParam)
                        .ifPresent(userInput::setFilePath);

        case DATE_PARAM -> ofNullable(valueParam)
                        .map(CLIInputParser::parseLocalDate)
                        .ifPresent(userInput::setRequestedDate);

        case PROCESS_PARAM -> ofNullable(valueParam)
                        .map(ProcessType::valueOf)
                        .ifPresent(userInput::setProcessType);

        default -> logger.debug(format("Skipping invalid param %s with value %s", keyParam, valueParam));

        }
    }

    private static LocalDate parseLocalDate(String dateString){
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private void validateInput(UserInput userInput){
        List<String> missingParams = new ArrayList<>(3);

        if(isNull(userInput.getFilePath()) || userInput.getFilePath().isBlank())
            missingParams.add(FILE_PARAM);

        if(isNull(userInput.getRequestedDate()))
            missingParams.add(DATE_PARAM);

        if(isNull(userInput.getProcessType()))
            missingParams.add(PROCESS_PARAM);

        if(!missingParams.isEmpty()){
            throw new CookieAnalyzerException(format("The mandatory params %s are missing", join(" ,", missingParams)));
        }
    }

    private String purifyParam(String param){
        return param.toLowerCase().trim();
    }

}
