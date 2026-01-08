package de.bcxp.challenge.weather;

import de.bcxp.challenge.util.CsvUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
    void readWeatherDataFromCsvShouldParseSingleRowCorrectly() throws IOException {
        List<String[]> mockData = List.of(
                new String[] {"1", "20", "10"},
                new String[] {"2", "1", "0"});

        try (MockedStatic<CsvUtil> mocked = Mockito.mockStatic(CsvUtil.class)) {
            mocked.when(() -> CsvUtil.readCsv(WeatherAnalysis.DATA_SOURCE_FILE, ",")).thenReturn(mockData);

            List<WeatherData> result =
                    WeatherData.readWeatherDataFromCsv(WeatherAnalysis.DATA_SOURCE_FILE,",");

            assertEquals(2, result.size());

            WeatherData data = result.get(0);
            assertEquals(1, data.day);
            assertEquals(20, data.maxTemp);
            assertEquals(10, data.minTemp);
            assertEquals(10, data.tempSpread);
        }
    }

    @Test
    void readWeatherDataFromCsvShouldThrowIOExceptionForMissingFile() {
        assertThrows(IOException.class, () ->
                WeatherData.readWeatherDataFromCsv(
                        "this/is/a/wrong/path", ",")
        );
    }
}