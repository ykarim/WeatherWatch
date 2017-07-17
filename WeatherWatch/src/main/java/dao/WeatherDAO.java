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

    public void getWeatherData(String cityName) {
        weatherData.add(WeatherDataAccess.getCurrentWeatherDataByName(cityName));
    }

    public void getWeatherData(String zipCode, String countryCode) {
        weatherData.add(WeatherDataAccess.getCurrentWeatherDataByZip(zipCode, countryCode));
    }

    public Weather getLastUpdatedWeather() {
        return weatherData.get(weatherData.size() - 1);
    }
}
