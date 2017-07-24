package dao;

import watcher.WatchDAO;
import weather.Weather;

import java.util.ArrayList;

public class WeatherDAO {

    private static ArrayList<Weather> weatherData = new ArrayList<>();

    public static ArrayList<Weather> getWeatherData() {
        return weatherData;
    }

    public void addWeatherData(Weather weather) {
        weatherData.add(weather);
        WatchDAO.notifyWatchers();
    }

    public void removeWeatherData() {
        weatherData.clear();
    }

    public Weather getLatestWeather() {
        if (weatherData.size() != 0) {
            return weatherData.get(weatherData.size() - 1);
        } else {
            return null;
        }
    }
}
