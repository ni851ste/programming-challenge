package de.bcxp.challenge.weather;

public class WeatherData {

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
}
