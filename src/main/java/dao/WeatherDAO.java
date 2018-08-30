package dao;

import model.Location;
import model.Temperature;
import model.Weather;
import org.joda.time.DateTime;
import org.joda.time.Hours;
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

    public Weather getLatestWeather(Location location) {
        if (weatherData.size() != 0) {
            return weatherData.get(weatherData.size() - 1);
        } else {
            return null;
        }
    }

    public List<Weather> getWeatherForLocation(Location location) {
        List<Weather> locationWeather = new ArrayList<>();
        for (Weather weather : weatherData) {
            if (weather.getLocation().getCity().equalsIgnoreCase(location.getCity())
                    && weather.getLocation().getCountry().equalsIgnoreCase(location.getCountry())) {
                locationWeather.add(weather); //might not work since when resp. received location is set from response
            }
        }

        return locationWeather;
    }

    public List<Weather> getWeatherForDate(Location location, DateTime dateTime) {
        List<Weather> locationWeather = getWeatherForLocation(location);
        List<Weather> dateWeather = new ArrayList<>();
        for (Weather weather : locationWeather) {
            if (weather.getWeatherTime().dayOfYear().equals(dateTime.dayOfYear())) {
                dateWeather.add(weather);
            }
        }

        return dateWeather;
    }

    //TODO: Optimize data structure and algorithm

    /**
     * Finds all weather data of requested date and retrieves calculated low and high temperatures
     * TODO: Using icon from highest temp weather obj chooses night weather at times
     *
     * @param dayDate requested date to retrieve weather
     * @return weather object of requested date with highest temperature representing day's average weather
     */
    public Weather getWeatherForDay(Location location, DateTime dayDate) {
        List<Weather> daysWeatherData = getWeatherForDate(location, dayDate);

        BigDecimal highTemp = new BigDecimal(0);
        BigDecimal lowTemp = new BigDecimal(Integer.MAX_VALUE);

        Weather daysWeather = null;

        for (Weather weather : daysWeatherData) {
            if (weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).compareTo(highTemp) > 0) {
                highTemp = weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT);
                daysWeather = weather;
            }

            if (weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).compareTo(lowTemp) < 0) {
                lowTemp = weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT);
            }
        }

        daysWeather.setHighTemperature(new Temperature(daysWeather.getHighTemperature().getUnit(), highTemp));
        daysWeather.setLowTemperature(new Temperature(daysWeather.getLowTemperature().getUnit(), lowTemp));

        return daysWeather;
    }

    /**
     * Searches through weather data to find weather object closest to requested time by comparing hour difference
     * If hour difference is less than or equal to 3 hours, retrieved weather data is automatically returned
     * TODO: Improve algorithm complexity and time
     *
     * @param date requested date to find closest weather data for
     * @return weather object with weather time closest to requested date
     */
    public Weather findWeatherByTime(Location location, DateTime date) {
        List<Weather> locationWeather = getWeatherForLocation(location);
        if (locationWeather.size() > 0) {
            Weather closestWeather = locationWeather.get(0);
            int highestHourDiff = Math.abs(Hours.hoursBetween(closestWeather.getWeatherTime(), date).getHours());
            for (int index = 1; index < locationWeather.size(); index++) {
                int currentHourDiff = Math.abs(Hours.hoursBetween(locationWeather.get(index).getWeatherTime(), date).getHours());
                if (currentHourDiff < highestHourDiff) {
                    highestHourDiff = currentHourDiff;
                    closestWeather = locationWeather.get(index);

                    if (currentHourDiff <= 3) {
                        break;
                    }
                }
            }

            return closestWeather;
        } else {
            return null;
        }
    }
}
