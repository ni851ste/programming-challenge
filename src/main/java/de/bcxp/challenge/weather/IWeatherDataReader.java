package de.bcxp.challenge.weather;

import java.io.IOException;
import java.util.List;

public interface IWeatherDataReader {
    List<WeatherData> readWeather(String filename, String delimiter) throws IOException;
}
