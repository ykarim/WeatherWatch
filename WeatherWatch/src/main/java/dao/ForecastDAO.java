package dao;

import watcher.WatchDAO;
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
        WatchDAO.notifyWatchers();
    }

    public void addForecasts(List<Forecast> forecasts) {
        ForecastDAO.forecasts.addAll(forecasts);
        WatchDAO.notifyWatchers();
    }

    public void removeForecasts() {
        forecasts.clear();
    }

    public Forecast getLatestForecast() {
        if (forecasts.size() != 0) {
            return (ForecastDAO.getForecasts().get(forecasts.size() - 1));
        } else {
            return null;
        }
    }

    public List<Forecast> getLastNForecasts(int count) {
        ArrayList<Forecast> forecastsToRetrieve = new ArrayList<>();

        if (forecasts.size() > count) {
            for (int index = 1; index <= count; index++) {
                forecastsToRetrieve.add(forecasts.get(forecasts.size() - index));
            }
        }

        return forecastsToRetrieve;
    }

    public Forecast getForecastForNthHour(int hour) {
        for (Forecast forecast : forecasts) {
            if (forecast.getTimeOfWeather().getHourOfDay() == hour) {
                return forecast;
            }
        }
        return null;
    }
}
