package de.bcxp.challenge.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class WeatherAnalysisTest {

    @Mock
    WeatherDataCsvReader weatherDataReader;
    WeatherAnalysis weatherAnalysis;

    @BeforeEach
    void setUp() {
        weatherDataReader = Mockito.mock(WeatherDataCsvReader.class);
        weatherAnalysis = new WeatherAnalysis();
        weatherAnalysis.dataReader = weatherDataReader;
    }

    @Test
    void getDayWithSmallestTempSpreadShouldReturnCorrectDay() throws IOException {
        List<WeatherData> mockData = List.of(
                new WeatherData(1, 20, 10),
                // min spread
                new WeatherData(2, 1, 0),
                new WeatherData(3, 25, 10));

        when(weatherDataReader.readWeather(
                WeatherAnalysis.DATA_SOURCE_FILE, ","))
                .thenReturn(mockData);

        WeatherData result = weatherAnalysis.getDayWithSmallestTempSpread();

        assertEquals(2, result.day);
        assertEquals(1, result.tempSpread);
    }


    @Test
    void getDayWithSmallestTempSpreadShouldThrowRuntimeExceptionOnIOException() throws IOException {
        List<WeatherData> mockData = List.of(
                new WeatherData(1, 20, 10),
                // min spread
                new WeatherData(2, 1, 0),
                new WeatherData(3, 25, 10));

        when(weatherDataReader.readWeather(WeatherAnalysis.DATA_SOURCE_FILE, ","))
                .thenThrow(new IOException("File not found"));

        assertThrows(RuntimeException.class, weatherAnalysis::getDayWithSmallestTempSpread);
    }
}

