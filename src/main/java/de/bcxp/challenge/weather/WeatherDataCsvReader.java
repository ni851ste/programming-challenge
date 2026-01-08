package de.bcxp.challenge.weather;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherDataCsvReader implements IWeatherDataReader {


    public static final int COLUMN_DAY = 0;
    public static final int COLUMN_MAX_TEMP = 1;
    public static final int COLUMN_MIN_TEMP = 2;

    public List<WeatherData> readWeather(String filename, String delimiter) throws IOException {
        List<String[]> weatherFileContents = readCsv(filename, delimiter);

        return weatherFileContents.stream()
                // Create weather object from csv data
                .map(line -> new WeatherData(
                        Integer.parseInt(line[COLUMN_DAY]),
                        Integer.parseInt(line[COLUMN_MAX_TEMP]),
                        Integer.parseInt(line[COLUMN_MIN_TEMP])))
                .collect(Collectors.toList());
    }

    private static List<String[]> readCsv(String filename, String delimiter) throws IOException {
        // Get stream to resource
        InputStream is = WeatherDataCsvReader.class.getClassLoader().getResourceAsStream(filename);
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
