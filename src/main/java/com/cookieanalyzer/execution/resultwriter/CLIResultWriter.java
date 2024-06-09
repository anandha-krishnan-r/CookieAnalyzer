package com.cookieanalyzer.execution.resultwriter;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import com.cookieanalyzer.execution.ResultWriter;

/**
 * Implementation of {@link ResultWriter} for writing results to the command-line interface (CLI).
 */
public class CLIResultWriter implements ResultWriter<Set<Object>> {

    private final PrintStream printStream;

    public CLIResultWriter(PrintStream printStream){
        this.printStream = printStream;
    }

    /**
     * Writes the result to the command-line interface (CLI) using the provided {@link PrintStream}.
     *
     * @param result the result to be written
     */
    @Override
    public void writeResult(Set<Object> result) {
        System.out.println("Result: ");
        Optional.ofNullable(result)
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(String::valueOf)
                        .forEach(printStream::println);
    }
}
