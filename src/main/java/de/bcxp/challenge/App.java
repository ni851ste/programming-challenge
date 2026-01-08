package de.bcxp.challenge;

import de.bcxp.challenge.countries.CountryAnalysis;
import de.bcxp.challenge.countries.CountryData;
import de.bcxp.challenge.weather.WeatherAnalysis;
import de.bcxp.challenge.weather.WeatherData;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        // Your preparation code …
        WeatherAnalysis weatherAnalysis = new WeatherAnalysis();
        CountryAnalysis countryAnalysis = new CountryAnalysis();

        // Your day analysis function call …
        WeatherData dayWithSmallestTempSpread = weatherAnalysis.getDayWithSmallestTempSpread();
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread.day);

        CountryData countryWithHighestPopulationDensity = countryAnalysis.getCountryWithHighestPopulationDensity();
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity.name);
    }
}
