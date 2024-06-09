package com.cookieanalyzer.exception;

/**
 * Custom runtime exception class for the Cookie Analyzer project.
 * This checked exception is thrown to indicate specific errors that occur during the processing
 * of cookie data within the application.
 */
public class CookieAnalyzerException extends RuntimeException {

    public CookieAnalyzerException(String message) {
        super(message);
    }

    public CookieAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }

}
