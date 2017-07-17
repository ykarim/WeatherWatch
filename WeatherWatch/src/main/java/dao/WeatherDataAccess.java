package dao;

import network.DataFormat;
import network.NetworkConnect;
import network.Requests;
import weather.Forecast;
import weather.Weather;

import java.util.List;

class WeatherDataAccess {

    static Weather getCurrentWeatherDataByName(String cityName) {
        return DataFormat.convertJSONToWeatherObj(
                NetworkConnect.getData(Requests.requestCurrentWeatherByName(cityName)));
    }

    static Weather getCurrentWeatherDataByZip(String zipCode, String countryCode) {
        return DataFormat.convertJSONToWeatherObj(
                NetworkConnect.getData(Requests.requestCurrentWeatherByZip(zipCode, countryCode)));
    }

    static List<Forecast> getForecastByName(String cityName) {
        return DataFormat.convertJSONToForecastObjs(
                NetworkConnect.getData(Requests.requestForecastByName(cityName)));
    }

    static List<Forecast> getForecastByZip(String zipCode, String countryCode) {
        return DataFormat.convertJSONToForecastObjs(
                NetworkConnect.getData(Requests.requestForecastByZip(zipCode, countryCode)));
    }
}
