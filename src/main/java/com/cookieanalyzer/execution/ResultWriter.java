package com.cookieanalyzer.execution;

/**
 * Interface for writing the result to the specified output stream.
 *
 * @param <R> the type of the result to be written
 */
public interface ResultWriter<R> {

    /**
     * Writes the given result to the output stream.
     *
     * @param result the result to be written
     */
    void writeResult(R result);

}
