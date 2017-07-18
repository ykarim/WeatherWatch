package dao;

import weather.Weather;

import java.util.ArrayList;

public class WeatherDAO {

    private static ArrayList<Weather> weatherData = new ArrayList<>();

    public static ArrayList<Weather> getWeatherData() {
        return weatherData;
    }

    public void addWeatherData(Weather weather) {
        weatherData.add(weather);
    }

    public Weather getLastUpdatedWeather() {
        return weatherData.get(weatherData.size() - 1);
    }
}
