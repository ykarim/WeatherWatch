package network;

import dao.WeatherDAO;
import weather.Forecast;
import weather.Weather;

import java.util.List;

public class WeatherAccess {

    private WeatherDAO weatherDAO;

    public WeatherAccess(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    public static Weather getCurrentWeatherDataByName(String cityName) {
        return DataFormat.convertJSONToWeatherObj(
                NetworkConnect.getData(Requests.requestCurrentWeatherByName(cityName)));
    }

    public static Weather getCurrentWeatherDataByZip(String zipCode, String countryCode) {
        return DataFormat.convertJSONToWeatherObj(
                NetworkConnect.getData(Requests.requestCurrentWeatherByZip(zipCode, countryCode)));
    }

    public static List<Forecast> getForecastByName(String cityName) {
        return DataFormat.convertJSONToForecastObjs(
                NetworkConnect.getData(Requests.requestForecastByName(cityName)));
    }

    public static String getForecastByZip(String zipCode, String countryCode) {
        return null;
    }
}
