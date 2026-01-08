package de.bcxp.challenge.weather;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class WeatherAnalysis {

    public static final String DATA_SOURCE_FILE = "de/bcxp/challenge/weather.csv";

    public static WeatherData getDayWithSmallestTempSpread() {

        try {
            // Read weather data from csv File
            List<WeatherData> weatherData = WeatherData.readWeatherDataFromCsv(DATA_SOURCE_FILE, ",");

            // Get the min spread and return its day
            return weatherData.stream()
                    .min(Comparator.comparing(WeatherData::getTempSpread))
                    .orElseThrow(() -> new RuntimeException("Error while determining minimum of temperature spread."));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading weather data from file: " + DATA_SOURCE_FILE);
        }
    }
}
