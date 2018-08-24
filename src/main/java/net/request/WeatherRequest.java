package net.request;

import model.Location;
import model.TempUnit;
import org.apache.http.client.methods.HttpUriRequest;

public interface WeatherRequest {

    HttpUriRequest getRequest();

    RequestFunction getFunction();

    /**
     * Sets location to be used for weather API call
     * Implementation dependent on API in use
     *
     * @param location to query weather data for
     */
    void setLocation(Location location);

    /**
     * Sets temperature to be used for weather API call
     * Implementation dependent on API in use
     * @param tempUnit for API response unit
     */
    void setUnits(TempUnit tempUnit);

    /**
     * Sets API key
     * Implementation dependent on API in use
     * @param subscriptionKey for API
     */
    void setSubscriptionKey(String subscriptionKey);

    /**
     * Stores currently supported API function categories
     * Both OpenWeatherMap and Wunderground API's support current condition and forecast functions
     * Used to send received response to API-specific parser and analyze received data
     */
    enum RequestFunction {
        CONDITIONS, FORECAST
    }
}
