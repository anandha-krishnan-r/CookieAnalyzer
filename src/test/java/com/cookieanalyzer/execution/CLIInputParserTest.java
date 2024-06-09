package com.cookieanalyzer.execution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.data.domain.ProcessType;
import com.cookieanalyzer.execution.inputparser.CLIInputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Junit")
public class CLIInputParserTest extends JunitBaseTest {


    private final CLIInputParser cliInputParser;

    public CLIInputParserTest() {
        this.cliInputParser = new CLIInputParser();
    }


    @Test
    @DisplayName("Valid CLI input")
    public void test_parseInput_valid_args(){
        String[] rawInput = {FILE_PARAM, FILE_PATH,
                        DATE_PARAM, DATE_STRING,
                        PROCESS_TYPE_PARAM, MOST_ACTIVE_COOKIE};

        var inputDataResult = cliInputParser.parseInput(rawInput);

        assertThat(inputDataResult)
                        .isNotNull()
                        .returns(FILE_PATH, UserInput::getFilePath)
                        .returns(LocalDate.of(2018, 12, 9), UserInput::getRequestedDate)
                        .returns(ProcessType.MOST_ACTIVE_COOKIE, UserInput::getProcessType);

    }

    @Test
    @DisplayName("Invalid CLI input, missing file param")
    public void test_parseInput_invalid_args_missing_file_param(){
        String[] rawInput = {FILE_PARAM,
                        DATE_PARAM, DATE_STRING,
                        PROCESS_TYPE_PARAM, MOST_ACTIVE_COOKIE};

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> cliInputParser.parseInput(rawInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("The mandatory params -f are missing");

    }

    @Test
    @DisplayName("Invalid CLI input, missing data param")
    public void test_parseInput_invalid_args_missing_date_param(){
        String[] rawInput = {FILE_PARAM, FILE_PATH,
                        DATE_PARAM,
                        PROCESS_TYPE_PARAM, MOST_ACTIVE_COOKIE};

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> cliInputParser.parseInput(rawInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("The mandatory params -d are missing");

    }

    @Test
    @DisplayName("Invalid CLI input, missing process type param")
    public void test_parseInput_invalid_args_missing_process_type_param(){
        String[] rawInput = {FILE_PARAM, FILE_PATH,
                        DATE_PARAM, DATE_STRING,
                        PROCESS_TYPE_PARAM};

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> cliInputParser.parseInput(rawInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("The mandatory params -pt are missing");

    }

    @Test
    @DisplayName("Invalid CLI input, no argument passed")
    public void test_parseInput_no_args(){
        String[] rawInput = {};

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> cliInputParser.parseInput(rawInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("The mandatory params -f ,-d ,-pt are missing");

    }



}
