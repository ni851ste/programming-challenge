package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherAnalysisTest {

    @Test
    void getDayWithSmallestTempSpreadShouldReturnCorrectDay() {
        List<WeatherData> mockData = List.of(
                new WeatherData(1, 20, 10),
                // min spread
                new WeatherData(2, 1, 0),
                new WeatherData(3, 25, 10));

        try (MockedStatic<WeatherData> mocked = Mockito.mockStatic(WeatherData.class)) {
            mocked.when(() -> WeatherData.readWeatherDataFromCsv(WeatherAnalysis.DATA_SOURCE_FILE, ",")).thenReturn(mockData);

            WeatherData result = WeatherAnalysis.getDayWithSmallestTempSpread();

            assertEquals(2, result.day);
            assertEquals(1, result.tempSpread);
        }
    }

    @Test
    void getDayWithSmallestTempSpreadShouldThrowRuntimeExceptionOnIOException() {
        try (MockedStatic<WeatherData> mocked = Mockito.mockStatic(WeatherData.class)) {
            mocked.when(() -> WeatherData.readWeatherDataFromCsv(WeatherAnalysis.DATA_SOURCE_FILE, ","))
                    .thenThrow(new IOException("File not found"));

            assertThrows(RuntimeException.class, WeatherAnalysis::getDayWithSmallestTempSpread);
        }
    }
}
