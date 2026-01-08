package de.bcxp.challenge.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static de.bcxp.challenge.util.CsvUtil.readCsv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvUtilTest {


    @Test
    void readingACsvFileShouldSucceed() throws IOException {
        List<String[]> contents = readCsv("de/bcxp/challenge/weather_10_entries.csv", ",");

        // 10 rows parsed
        assertEquals(contents.size(), 10);

        // 15 values per row
        assertEquals(contents.get(0).length, 14);
    }


    @Test
    void readingANonExistingFileShouldFail() throws IOException {
        assertThrows(IOException.class,
                () -> readCsv("this/is/a/wrong/path", ","));
    }

}