package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherDataTest {

    @Test
    void constructorShouldCalculateTempSpreadCorrectly() {
        WeatherData data = new WeatherData(1, 20, 10);

        assertEquals(1, data.day);
        assertEquals(20, data.maxTemp);
        assertEquals(10, data.minTemp);
        assertEquals(10, data.tempSpread);
    }

    @Test
    void getTempSpreadShouldReturnCorrectValue() {
        WeatherData data = new WeatherData(2, 15, 5);

        assertEquals(10, data.getTempSpread());
    }
}