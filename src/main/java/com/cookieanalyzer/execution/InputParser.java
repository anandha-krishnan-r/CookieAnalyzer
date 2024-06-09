package com.cookieanalyzer.execution;

import com.cookieanalyzer.data.domain.UserInput;

/**
 * Interface for parsing user input into a structured format.
 *
 * @param <I> the type of the user input to be parsed.
 */
public  interface InputParser<I> {

    /**
     * Parses the given user input into an {@link UserInput} object.
     *
     * @param rawInput the user input to be parsed
     * @return the parsed {@link UserInput} input object
     */
    UserInput parseInput(I rawInput);

}
