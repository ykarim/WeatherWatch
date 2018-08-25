package net;

import dao.WeatherDAO;
import model.Location;
import model.Weather;
import net.request.WeatherRequest;
import net.request.WeatherRequestBuilder;
import net.response.data.WeatherConditionsResponse;
import net.response.data.WeatherForecastResponse;
import org.apache.http.HttpResponse;

import java.util.List;

public class APIAccess {

    private static WeatherDAO weatherDAO = new WeatherDAO();

    public static Weather requestWeatherConditions(Location location) {
        //Send request to API
        HttpResponse conditionsHttpResponse =
                Connection.executeRequest(WeatherRequestBuilder.createConditionsRequest(location));

        //Parse received HttpResponse into conditions response
        WeatherConditionsResponse conditionsResponse =
                (WeatherConditionsResponse) Connection.processResponse(WeatherRequest.RequestFunction.CONDITIONS,
                        conditionsHttpResponse);

        //Extract weather data from conditions response and add to DAO
        weatherDAO.addWeatherData(conditionsResponse.getWeather());
        return conditionsResponse.getWeather();
    }

    public static List<Weather> requestWeatherForecast(Location location) {
        //Send request to API
        HttpResponse forecastHttpResponse =
                Connection.executeRequest(WeatherRequestBuilder.createForecastRequest(location));

        //Parse received HttpResponse into forecast response
        WeatherForecastResponse forecastResponse =
                (WeatherForecastResponse) Connection.processResponse(WeatherRequest.RequestFunction.FORECAST,
                        forecastHttpResponse);

        //Extract weather data from forecast response and add to DAO
        for (Weather forecastWeather : forecastResponse.getForecasts()) {
            weatherDAO.addWeatherData(forecastWeather);
        }

        return forecastResponse.getForecasts();
    }
}
