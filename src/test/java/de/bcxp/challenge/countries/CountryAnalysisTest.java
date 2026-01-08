package de.bcxp.challenge.countries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CountryAnalysisTest {
    @Mock
    CountryDataCsvReader countryDataCsvReader;
    CountryAnalysis countryAnalysis;

    @BeforeEach
    void setUp() {
        countryDataCsvReader = Mockito.mock(CountryDataCsvReader.class);
        countryAnalysis = new CountryAnalysis();
        countryAnalysis.dataReader = countryDataCsvReader;
    }

    @Test
    void cShouldReturnCorrectDay() throws IOException {
        List<CountryData> mockData = List.of(
                new CountryData("Name", 100, 10),
                // max density
                new CountryData("Name2", 100000, 10),
                new CountryData("Name3", 150, 10));

        when(countryDataCsvReader.readCountries(
                CountryAnalysis.DATA_SOURCE_FILE, ";"))
                .thenReturn(mockData);

        CountryData result = countryAnalysis.getCountryWithHighestPopulationDensity();

        assertEquals("Name2", result.name);
        assertEquals(10000, result.populationDensity);
    }


    @Test
    void getCountryWithHighestPopDensShouldThrowRuntimeExceptionOnIOException() throws IOException {
        List<CountryData> mockData = List.of(
                new CountryData("Name", 100, 10),
                // max density
                new CountryData("Name2", 100000, 10),
                new CountryData("Name3", 150, 10));

        when(countryDataCsvReader.readCountries(
                CountryAnalysis.DATA_SOURCE_FILE, ";"))
                .thenThrow(new IOException("File not found"));

        assertThrows(RuntimeException.class, countryAnalysis::getCountryWithHighestPopulationDensity);
    }
}