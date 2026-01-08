package de.bcxp.challenge.countries;

public class CountryData {

    public final String name;
    public final int population;
    public final int area;

    public final double populationDensity;

    CountryData(String name, int population, int area) {
        this.name = name;
        this.population = population;
        this.area = area;

        this.populationDensity = (double) population / area;
    }

    public double getPopulationDensity() {
        return this.populationDensity;
    }
}
