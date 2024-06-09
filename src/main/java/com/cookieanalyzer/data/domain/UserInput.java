package com.cookieanalyzer.data.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class that holds the user input for processing.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private String filePath;
    private LocalDate requestedDate;
    private ProcessType processType;

}
