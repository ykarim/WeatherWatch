package net.request;

import net.Subscription;
import net.request.openweathermap.OWMWeatherGetRequest;
import util.Constants;
import weather.Location;

import java.net.URI;

public class WeatherRequestBuilder {

    private static String OWM_CONDITIONS_REQUEST = "https://api.openweathermap.org/data/2.5/weather";
    private static String OWM_FORECAST_REQUEST = "https://api.openweathermap.org/data/2.5/forecast";

    /**
     * Creates WeatherGetRequest to query current weather conditions based upon API in use given location
     *
     * @param location to be used in weather data request
     * @return API-specific WeatherGetRequest containing location, unit, and subscription data to be sent for current
     * conditions request
     */
    public static WeatherGetRequest createConditionsRequest(Location location) {
        WeatherGetRequest conditionsRequest =
                new OWMWeatherGetRequest(URI.create(OWM_CONDITIONS_REQUEST), WeatherRequest.RequestFunction.CONDITIONS);
        conditionsRequest.setLocation(location);
        conditionsRequest.setUnits(Constants.PREFERRED_UNIT);
        conditionsRequest.setSubscriptionKey(Subscription.getSubscriptionKey());

        return conditionsRequest;
    }

    /**
     * Creates WeatherGetRequest to query weather forecast based upon API in use given location
     * @param location to be used in weather data request
     * @return API-specific WeatherGetRequest containing location, unit, and subscription data to be sent for current
     *  forecast request
     */
    public static WeatherGetRequest createForecastRequest(Location location) {
        WeatherGetRequest forecastRequest =
                new OWMWeatherGetRequest(URI.create(OWM_FORECAST_REQUEST), WeatherRequest.RequestFunction.FORECAST);
        forecastRequest.setLocation(location);
        forecastRequest.setUnits(Constants.PREFERRED_UNIT);
        forecastRequest.setSubscriptionKey(Subscription.getSubscriptionKey());

        return forecastRequest;
    }
}
