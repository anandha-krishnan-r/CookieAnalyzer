package com.cookieanalyzer.execution.inputparser;

import static java.lang.String.join;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.data.domain.ProcessType;
import com.cookieanalyzer.execution.ApplicationExecutor;
import com.cookieanalyzer.execution.InputParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link InputParser} for parsing command-line interface (CLI) input.
 */
public class CLIInputParser implements InputParser<String[]> {

    private static final Logger logger = LoggerFactory.getLogger(CLIInputParser.class);


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

    private UserInput parseToInputData(String[] rawInput){

        UserInput userInput = new UserInput();
        for (int i = 0; i < rawInput.length; i++) {
            var currentParam = rawInput[i];
            if(nonNull(currentParam) && currentParam.startsWith(HYPHEN)){

                switch (purifyParam(currentParam)) {
                case "-f" -> ofNullable(rawInput[++i])
                                .ifPresent(userInput::setFilePath);

                case "-d" -> ofNullable(rawInput[++i])
                                .map(CLIInputParser::parseLocalDate)
                                .ifPresent(userInput::setRequestedDate);

                case "-pt" -> ofNullable(rawInput[++i])
                                .map(ProcessType::valueOf)
                                .ifPresent(userInput::setProcessType);

                }
            }
        }

        return userInput;
    }

    private static LocalDate parseLocalDate(String dateString){
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private void validateInput(UserInput userInput){
        List<String> missingParams = new ArrayList<>(3);

        if(isNull(userInput.getFilePath()) || userInput.getFilePath().isBlank())
            missingParams.add("-f");

        if(isNull(userInput.getRequestedDate()))
            missingParams.add("-d");

        if(isNull(userInput.getProcessType()))
            missingParams.add("-pt");

        if(!missingParams.isEmpty()){
            throw new RuntimeException(String.format("The mandatory params %s are missing", join(" ,", missingParams)));
        }

    }

    private String purifyParam(String param){
        return param.toLowerCase().trim();
    }

}
