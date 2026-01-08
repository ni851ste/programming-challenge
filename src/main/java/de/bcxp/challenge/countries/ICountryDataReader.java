package de.bcxp.challenge.countries;

import java.io.IOException;
import java.util.List;

public interface ICountryDataReader {
    List<CountryData> readCountries(String filename, String delimiter) throws IOException;
}
