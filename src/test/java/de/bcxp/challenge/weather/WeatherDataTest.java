package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    @TempDir
    Path tempDir;

    @Test
    void constructorShouldCalculateTempSpreadCorrectly() {
        WeatherData data = new WeatherData(1, 20, 10);

        assertEquals(1, data.day);
        assertEquals(20, data.maxTemp);
        assertEquals(10, data.minTemp);
        assertEquals(10, data.tempSpread);
    }

    @Test
    void getTempSpreadShouldReturnCorrectValue() {
        WeatherData data = new WeatherData(2, 15, 5);

        assertEquals(10, data.getTempSpread());
    }

    @Test
    void readWeatherDataFromCsv_shouldParseSingleRowCorrectly() throws IOException {


        List<WeatherData> result =
                WeatherData.readWeatherDataFromCsv("de/bcxp/challenge/weather_1_entries.csv",",");

        assertEquals(1, result.size());

        WeatherData data = result.get(0);
        assertEquals(1, data.day);
        assertEquals(20, data.maxTemp);
        assertEquals(10, data.minTemp);
        assertEquals(10, data.tempSpread);
    }

    @Test
    void readWeatherDataFromCsv_shouldThrowIOExceptionForMissingFile() {
        assertThrows(IOException.class, () ->
                WeatherData.readWeatherDataFromCsv(
                        "this/is/a/wrong/path", ",")
        );
    }
}