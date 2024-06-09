package com.cookieanalyzer.data.domain;

import java.time.LocalDateTime;

/**
 * Wrapper class that holds the cookie data for processing.
 *
 * @param cookie the name representing the cookie.
 * @param accessedTime the timestamp at which the cookie was accessed.
 */
public record CookieData(String cookie, LocalDateTime accessedTime) {

}
