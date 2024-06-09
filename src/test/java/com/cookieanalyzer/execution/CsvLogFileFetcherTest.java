package com.cookieanalyzer.execution;

import static com.cookieanalyzer.data.domain.ProcessType.MOST_ACTIVE_COOKIE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.execution.datafetcher.CsvLogFileFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsvLogFileFetcherTest {

    private CsvLogFileFetcher csvLogFileFetcher;

    private final String BASE_PATH = "src/test/resources/";

    @BeforeEach
    public void setUp() {
        csvLogFileFetcher = new CsvLogFileFetcher();
    }

    @Test
    public void test_readData_valid_csv_file(){
        UserInput userInput = new UserInput(BASE_PATH + "cookie-log-1.csv", LocalDate.of(2018, 12, 9), MOST_ACTIVE_COOKIE);

        final List<CookieData> cookieData = csvLogFileFetcher.readData(userInput);

        assertThat(cookieData).isNotEmpty().hasSize(8);
        assertThat(cookieData).first().isNotNull();
    }

    @Test
    public void test_readData_empty_csv_file(){
        UserInput userInput = new UserInput(BASE_PATH + "cookie-log-empty.csv", LocalDate.of(2018, 12, 9), MOST_ACTIVE_COOKIE);

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> csvLogFileFetcher.readData(userInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("Data file reading failed, given file is empty.");
    }

    @Test
    public void test_readData_non_csv_file(){
        UserInput userInput = new UserInput(BASE_PATH + "cookie-log.json", LocalDate.of(2018, 12, 9), MOST_ACTIVE_COOKIE);

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> csvLogFileFetcher.readData(userInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("Data file reading failed, given file is not csv.");
    }

    @Test
    public void test_readData_invalid_file_wrong_header(){
        UserInput userInput = new UserInput(BASE_PATH + "cookie-log-invalid-header.csv", LocalDate.of(2018, 12, 9), MOST_ACTIVE_COOKIE);

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> csvLogFileFetcher.readData(userInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("Data file reading failed, CSV header is invalid.");

    }

    @Test
    public void test_readData_invalid_file_missing_cookie_data(){
        UserInput userInput = new UserInput(BASE_PATH + "cookie-log-invalid-data-row.csv", LocalDate.of(2018, 12, 9), MOST_ACTIVE_COOKIE);

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> csvLogFileFetcher.readData(userInput));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("Data file reading failed, invalid data rows found.");

    }



}
