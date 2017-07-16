package dao;

import org.junit.Assert;
import org.junit.Test;
import weather.Forecast;
import weather.Weather;

import java.util.ArrayList;
import java.util.Date;

public class ForecastDAOTest {

    private static ForecastDAO forecastDAO = new ForecastDAO();

    @Test
    public void testGetForecasts() {
        Forecast testForecast = new Forecast(null, null);
        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(testForecast);
        forecastDAO.removeForecasts();
        forecastDAO.addForecast(testForecast);
        Assert.assertEquals(forecasts, ForecastDAO.getForecasts());
    }

    @Test
    public void testAddForecast_shouldAddOneForecast() {
        Forecast testForecast = new Forecast(new Weather(), new Date());
        forecastDAO.addForecast(testForecast);
        Assert.assertTrue(ForecastDAO.getForecasts().contains(testForecast));
    }

    @Test
    public void testAddForecasts_shouldAddMultipleForecasts() {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        Forecast testForecast1 = new Forecast(new Weather(), new Date());
        Forecast testForecast2 = new Forecast(new Weather(), new Date());
        Forecast testForecast3 = new Forecast(new Weather(), new Date());

        forecasts.add(testForecast1);
        forecasts.add(testForecast2);
        forecasts.add(testForecast3);

        forecastDAO.addForecasts(forecasts);
        Assert.assertTrue(ForecastDAO.getForecasts().contains(testForecast1));
        Assert.assertTrue(ForecastDAO.getForecasts().contains(testForecast2));
        Assert.assertTrue(ForecastDAO.getForecasts().contains(testForecast3));
    }

    @Test
    public void testGetLatestForecast() {
        Forecast testForecast1 = new Forecast(new Weather(), new Date());
        forecastDAO.addForecast(testForecast1);
        Assert.assertEquals(forecastDAO.getLatestForecast(), testForecast1);

        ArrayList<Forecast> forecasts = new ArrayList<>();
        Forecast testForecast2 = new Forecast(new Weather(), new Date());
        Forecast testForecast3 = new Forecast(new Weather(), new Date());
        Forecast testForecast4 = new Forecast(new Weather(), new Date());
        forecasts.add(testForecast2);
        forecasts.add(testForecast3);
        forecasts.add(testForecast4);

        forecastDAO.addForecasts(forecasts);
        Assert.assertEquals(forecastDAO.getLatestForecast(), testForecast4);
    }
}
