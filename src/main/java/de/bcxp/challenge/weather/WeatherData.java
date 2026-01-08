package de.bcxp.challenge.weather;

import de.bcxp.challenge.util.CsvUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static de.bcxp.challenge.util.CsvUtil.readCsv;

public class WeatherData {

    public static final int COLUMN_DAY = 0;
    public static final int COLUMN_MAX_TEMP = 1;
    public static final int COLUMN_MIN_TEMP = 2;


    public final int day;
    public final int maxTemp;
    public final int minTemp;

    public final int tempSpread;

    WeatherData(int day, int maxTemp, int minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;

        this.tempSpread = maxTemp - minTemp;
    }

    public int getTempSpread() {
        return tempSpread;
    }

    public static List<WeatherData> readWeatherDataFromCsv(String filename, String delimiter) throws IOException {
        // Init util class

        List<String[]> weatherFileContents = readCsv(filename, delimiter);

        return weatherFileContents.stream()
                // Create weather object from csv data
                .map(line -> new WeatherData(
                        Integer.parseInt(line[COLUMN_DAY]),
                        Integer.parseInt(line[COLUMN_MAX_TEMP]),
                        Integer.parseInt(line[COLUMN_MIN_TEMP])))
                .collect(Collectors.toList());
    }

}
