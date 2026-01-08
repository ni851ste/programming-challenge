package de.bcxp.challenge.countries;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CountryDataCsvReader implements ICountryDataReader {


    public static final int COLUMN_NAME = 0;
    public static final int COLUMN_POPULATION = 3;
    public static final int COLUMN_AREA = 4;

    NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);

    public List<CountryData> readCountries(String filename, String delimiter) throws IOException {
        List<String[]> countryFileContents = readCsv(filename, delimiter);

        return countryFileContents.stream()
                // Create country object from csv data
                .map(line -> {
                    // make parsing more resilient to allow for different formatting in source data
                    try {
                        return new CountryData(
                                line[COLUMN_NAME],
                                // german format to support XXX.XXX.XXX,XX format
                                format.parse(line[COLUMN_POPULATION]).intValue(),
                                Integer.parseInt(line[COLUMN_AREA]));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private static List<String[]> readCsv(String filename, String delimiter) throws IOException {
        // Get stream to resource
        InputStream is = CountryDataCsvReader.class.getClassLoader().getResourceAsStream(filename);
        if (is == null) throw new IOException("File not found");

        List<String[]> fileContents = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                // Split each line by delimiter and collect split elements back into a list to create a matrix
                .map(line -> line.split(delimiter))
                // Skip header line
                .skip(1)
                .collect(Collectors.toList());

        return fileContents;
    }
}
