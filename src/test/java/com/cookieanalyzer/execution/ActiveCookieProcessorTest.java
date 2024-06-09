package com.cookieanalyzer.execution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import com.cookieanalyzer.data.domain.CookieData;
import com.cookieanalyzer.data.domain.UserInput;
import com.cookieanalyzer.data.domain.ProcessType;
import com.cookieanalyzer.execution.processor.ActiveCookieAnalyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Junit")
public class ActiveCookieProcessorTest extends JunitBaseTest {
    private ActiveCookieAnalyzer activeCookieProcessor;

    public ActiveCookieProcessorTest(){
        activeCookieProcessor = new ActiveCookieAnalyzer();
    }


    @Test
    @DisplayName("")
    public void test_processData_only_one_active_cookie(){
        final List<CookieData> cookieDataList = getMockCookieData();
        var inputData = new UserInput(FILE_PATH, DAY_1_TIMESTAMP_1.toLocalDate(), ProcessType.MOST_ACTIVE_COOKIE);

        final Set<Object> mostActiveCookies = activeCookieProcessor.analyzeData(cookieDataList, inputData);

        assertThat(mostActiveCookies)
                        .hasSize(1)
                        .contains(COOKIE_1);
    }

    @Test
    @DisplayName("")
    public void test_processData_multiple_active_cookie(){
        final List<CookieData> cookieDataList = getMockCookieData();
        var inputData = new UserInput(FILE_PATH, DAY_2_TIMESTAMP_1.toLocalDate(), ProcessType.MOST_ACTIVE_COOKIE);

        final Set<Object> mostActiveCookies = activeCookieProcessor.analyzeData(cookieDataList, inputData);

        assertThat(mostActiveCookies)
                        .hasSize(2)
                        .contains(COOKIE_3, COOKIE_4);
    }

    @Test
    @DisplayName("")
    public void test_processData_no_cookies_accessed(){
        final List<CookieData> cookieDataList = Collections.emptyList();
        var inputData = new UserInput(FILE_PATH, DAY_2_TIMESTAMP_1.toLocalDate(), ProcessType.MOST_ACTIVE_COOKIE);

        RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> activeCookieProcessor.analyzeData(cookieDataList, inputData));

        assertThat(exception)
                        .extracting(Exception::getMessage)
                        .isEqualTo("No Cookie data found");
    }

    @Test
    @DisplayName("")
    public void test_processData_every_cookies_accessed_exactly_once(){
        final List<CookieData> cookieDataList = getMockCookieData_singleOccurrence();
        var inputData = new UserInput(FILE_PATH, DAY_1_TIMESTAMP_1.toLocalDate(), ProcessType.MOST_ACTIVE_COOKIE);

        final Set<Object> mostActiveCookies = activeCookieProcessor.analyzeData(cookieDataList, inputData);

        assertThat(mostActiveCookies)
                        .hasSize(4)
                        .contains(COOKIE_1, COOKIE_2, COOKIE_3, COOKIE_4);
    }

    private List<CookieData> getMockCookieData() {
        List<CookieData> cookieDataList = new ArrayList<>();
        cookieDataList.add(new CookieData(COOKIE_1, DAY_1_TIMESTAMP_1));
        cookieDataList.add(new CookieData(COOKIE_2, DAY_1_TIMESTAMP_2));
        cookieDataList.add(new CookieData(COOKIE_1, DAY_1_TIMESTAMP_2));

        cookieDataList.add(new CookieData(COOKIE_3, DAY_2_TIMESTAMP_1));
        cookieDataList.add(new CookieData(COOKIE_3, DAY_2_TIMESTAMP_2));
        cookieDataList.add(new CookieData(COOKIE_4, DAY_2_TIMESTAMP_1));
        cookieDataList.add(new CookieData(COOKIE_4, DAY_2_TIMESTAMP_2));


        return cookieDataList;
    }

    private List<CookieData> getMockCookieData_singleOccurrence() {
        List<CookieData> cookieDataList = new ArrayList<>();
        cookieDataList.add(new CookieData(COOKIE_1, DAY_1_TIMESTAMP_1));
        cookieDataList.add(new CookieData(COOKIE_2, DAY_1_TIMESTAMP_2));
        cookieDataList.add(new CookieData(COOKIE_3, DAY_1_TIMESTAMP_1));
        cookieDataList.add(new CookieData(COOKIE_4, DAY_1_TIMESTAMP_2));

        return cookieDataList;
    }

}
