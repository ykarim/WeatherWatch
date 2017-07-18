package network;

import dao.ForecastDAO;
import dao.WeatherDAO;

public class WeatherDataAccess {

    private static WeatherDAO weatherDAO = new WeatherDAO();
    private static ForecastDAO forecastDAO = new ForecastDAO();

    public static void getWeatherByName(String cityName) {
        weatherDAO.addWeatherData(DataFormat.convertJSONToWeatherObj(
                NetworkConnect.getData(Requests.requestCurrentWeatherByName(cityName))));
    }

    public static void getWeatherByZip(String zipCode, String countryCode) {
        weatherDAO.addWeatherData(DataFormat.convertJSONToWeatherObj(
                NetworkConnect.getData(Requests.requestCurrentWeatherByZip(zipCode, countryCode))));
    }

    public static void getForecastByName(String cityName) {
        forecastDAO.addForecasts(DataFormat.convertJSONToForecastObjs(
                NetworkConnect.getData(Requests.requestForecastByName(cityName))));
    }

    public static void getForecastByZip(String zipCode, String countryCode) {
        forecastDAO.addForecasts(DataFormat.convertJSONToForecastObjs(
                NetworkConnect.getData(Requests.requestForecastByZip(zipCode, countryCode))));
    }
}
