package de.bcxp.challenge.countries;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class CountryAnalysis {

    public static final String DATA_SOURCE_FILE = "de/bcxp/challenge/countries.csv";

    ICountryDataReader dataReader = new CountryDataCsvReader();


    public CountryData getCountryWithHighestPopulationDensity() {

        try {
            // Read country data from csv File
            List<CountryData> countryData = dataReader.readCountries(DATA_SOURCE_FILE, ";");

            // Get the min spread and return its day
            return countryData.stream()
                    .max(Comparator.comparing(CountryData::getPopulationDensity))
                    .orElseThrow(() -> new RuntimeException("Error while determining maximum population density."));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading country data from file: " + DATA_SOURCE_FILE);
        }
    }
}
