package de.bcxp.challenge.countries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryDataTest {

    @Test
    void constructorShouldCalculatePopDensityCorrectly() {
        CountryData data = new CountryData("Name", 100, 10);

        assertEquals("Name", data.name);
        assertEquals(100, data.population);
        assertEquals(10, data.area);
        assertEquals(10, data.populationDensity);
    }

    @Test
    void getPopulationDensityShouldReturnCorrectValue() {
        CountryData data = new CountryData("Name2", 100, 10);

        assertEquals(10, data.getPopulationDensity());
    }
}