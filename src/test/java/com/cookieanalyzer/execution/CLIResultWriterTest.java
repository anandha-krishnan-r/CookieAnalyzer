package com.cookieanalyzer.execution;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Set;
import com.cookieanalyzer.execution.resultwriter.CLIResultWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Junit")
public class CLIResultWriterTest extends JunitBaseTest {

    private CLIResultWriter cliResultWriter;

    private PrintStream mockPrintStream;


    @BeforeEach
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
        cliResultWriter = new CLIResultWriter(mockPrintStream);
    }


    @Test
    public void test_writeResult_input_isNotNull(){
        cliResultWriter.writeResult(Set.of(COOKIE_1, COOKIE_2));

        verify(mockPrintStream, times(2)).println(anyString());
    }

    @Test
    public void test_writeResult_input_isEmpty(){
        cliResultWriter.writeResult(Collections.emptySet());

        verify(mockPrintStream, times(0)).println(anyString());
    }

}
