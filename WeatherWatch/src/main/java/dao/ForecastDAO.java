package dao;

import weather.Forecast;

import java.util.ArrayList;
import java.util.List;

public class ForecastDAO {

    private static ArrayList<Forecast> forecasts = new ArrayList<>();

    public static ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }

    public void addForecasts(List<Forecast> forecasts) {
        ForecastDAO.forecasts.addAll(forecasts);
    }

    public void removeForecasts() {
        forecasts.clear();
    }

    public void getForecast(String cityName) {
        addForecasts(WeatherDataAccess.getForecastByName(cityName));
    }

    public void getForecast(String zipCode, String countryCode) {
        addForecasts(WeatherDataAccess.getForecastByZip(zipCode, countryCode));
    }

    public Forecast getLatestForecast() {
        return (ForecastDAO.getForecasts().get(forecasts.size() - 1));
    }
}
