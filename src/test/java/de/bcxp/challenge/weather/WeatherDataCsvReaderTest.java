package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherDataCsvReaderTest {

    IWeatherDataReader weatherDataReader = new WeatherDataCsvReader();


    @Test
    void readWeatherShouldParseRowsCorrectly() throws IOException {
        List<String[]> mockData = List.of(
                new String[]{"1", "20", "10"},
                new String[]{"2", "1", "0"});


        List<WeatherData> result =
                weatherDataReader.readWeather("de/bcxp/challenge/weather_10_entries.csv", ",");

        assertEquals(10, result.size());

        WeatherData data = result.get(0);
        assertEquals(1, data.day);
        assertEquals(20, data.maxTemp);
        assertEquals(10, data.minTemp);
        assertEquals(10, data.tempSpread);
    }


    @Test
    void readWeatherDataFromCsvShouldThrowIOExceptionForMissingFile() {
        assertThrows(IOException.class, () ->
                weatherDataReader.readWeather(
                        "this/is/a/wrong/path", ",")
        );
    }
}