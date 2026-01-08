package de.bcxp.challenge.countries;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryDataCsvReaderTest {
    ICountryDataReader countryDataReader = new CountryDataCsvReader();


    @Test
    void readCountriesShouldParseRowsCorrectly() throws IOException {

        List<CountryData> result =
                countryDataReader.readCountries("de/bcxp/challenge/countries_10_entries.csv", ";");

        assertEquals(10, result.size());

        CountryData data = result.get(1);

        assertEquals("Name2", data.name);
        assertEquals(200, data.population);
        assertEquals(20, data.area);
        assertEquals(10, data.populationDensity);
    }


    @Test
    void readCountryDataFromCsvShouldThrowIOExceptionForMissingFile() {
        assertThrows(IOException.class, () ->
                countryDataReader.readCountries(
                        "this/is/a/wrong/path", ";")
        );
    }
}