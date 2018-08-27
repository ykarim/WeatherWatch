package dao;

import model.Weather;
import org.joda.time.DateTime;
import util.Constants;
import watcher.WatchDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAO {

    private static ArrayList<Weather> weatherData = new ArrayList<>();

    public static ArrayList<Weather> getWeatherData() {
        return weatherData;
    }

    public void addWeatherData(Weather weather) {
        weatherData.add(weather);
        WatchDAO.notifyWatchers(weather);
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

    //TODO: Optimize data structure and algorithm

    /**
     * Finds all weather data of requested date and retrieves low and high temperatures
     *
     * @param dayDate requested date to retrieve weather
     * @return weather object of requested date with highest temperature (most likely middle of day)
     */
    public Weather getWeatherForDay(DateTime dayDate) {
        List<Weather> daysWeatherData = new ArrayList<>();
        BigDecimal lowTemp = new BigDecimal(0);
        BigDecimal maxTemp = new BigDecimal(0);

        Weather daysWeather = null;

        for (Weather weather : weatherData) {
            if (weather.getWeatherTime().getDayOfMonth() == dayDate.getDayOfMonth()) {
                daysWeatherData.add(weather);
            }
        }

        for (Weather weather : daysWeatherData) {
            if (weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).compareTo(maxTemp) > 0) {
                maxTemp = weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT);
                daysWeather = weather;
            } else if (weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).compareTo(maxTemp) == 0) {
                //Compare timestamps to ensure better icon is used TODO: Should be done by time and finding mid of day
            }

            if (weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).compareTo(lowTemp) < 0) {
                lowTemp = weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT);
            }
        }

        return daysWeather;
    }
}
