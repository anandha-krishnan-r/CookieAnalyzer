package com.cookieanalyzer.execution.datafetcher;

import static com.cookieanalyzer.execution.util.MiscUtil.isEmpty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.exception.CookieAnalyzerException;
import com.cookieanalyzer.execution.CookieDataFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link CookieDataFetcher} that reads cookie data from a CSV log file.
 */
public class CsvLogFileFetcher implements CookieDataFetcher {

    private static final Logger logger = LoggerFactory.getLogger(CsvLogFileFetcher.class);

    private static final String COMMA_SEPARATOR = ",";

    private static final List<String> VALID_HEADERS = List.of("cookie", "timestamp");


    /**
     * Reads and parses cookie data from a CSV log file specified in the {@link UserInput}.
     *
     * @param userInput the user input containing the file path and other necessary details
     * @return a list of {@link CookieData} objects parsed from the CSV file
     */
    @Override
    public List<CookieData> readData(UserInput userInput) {
        logger.debug("Fetching data from given CSV file..");

        final File file = getCsvFile(userInput.getFilePath());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            final List<String> csvFileLines = reader.lines()
                            .toList();

            if(csvFileLines.isEmpty()){
                throw new CookieAnalyzerException("Data file reading failed, given file is empty.");
            }

            final String headerRow = csvFileLines.stream()
                            .findFirst()
                            .orElseThrow(NoSuchFieldError::new);
            validateHeader(headerRow);

            return csvFileLines.stream()
                            .skip(1)
                            .map(this::parseToCookieData)
                            .toList();

        } catch (IOException e) {
            throw new CookieAnalyzerException("Unable to read the input file", e);
        }
    }

    /**
     * Gets the File for the specified file path.
     *
     * @param filePath the path to the file
     * @return the file object
     */
    private File getCsvFile(String filePath){
        if(filePath.toLowerCase().endsWith(".csv")) {
            final Path path = Paths.get(filePath);
            return new File(path.toAbsolutePath()
                            .toUri());
        } else {
            throw new CookieAnalyzerException("Data file reading failed, given file is not csv.");
        }
    }

    /**
     * Validates the header of the CSV file to ensure it contains the required columns.
     *
     * @param headerRow the header line of the CSV file
     * @throws RuntimeException if the header is missing or invalid
     */
    private void validateHeader(String headerRow){

        final Set<String> headersInFile = Stream.of(headerRow.split(COMMA_SEPARATOR))
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

        if(!headersInFile.containsAll(VALID_HEADERS)){
            throw new CookieAnalyzerException("Data file reading failed, CSV header is invalid.");
        }
    }

    /**
     * Parses a line of CSV data into a {@link CookieData} object.
     *
     * @param dataRow the line of CSV data
     * @return the parsed {@link CookieData} object
     * @throws RuntimeException if the data line is invalid
     */
    private CookieData parseToCookieData(String dataRow){
        String[] parts = dataRow.split(COMMA_SEPARATOR);

        var fileName = parts[0];
        var requestedDateString = parts[1];


        if(isEmpty(fileName) || isEmpty(requestedDateString)) {
            throw new CookieAnalyzerException("Data file reading failed, invalid data rows found.");
        }

        return new CookieData(fileName, parseDateTime(requestedDateString));
    }


    private static LocalDateTime parseDateTime(String dateTimeString){
        var offsetDateTime = OffsetDateTime.parse(dateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return offsetDateTime.toLocalDateTime();
    }

}
