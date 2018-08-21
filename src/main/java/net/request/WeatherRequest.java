package net.request;

import org.apache.http.client.methods.HttpUriRequest;
import weather.Location;
import weather.TempUnit;

public interface WeatherRequest {

    HttpUriRequest getRequest();

    RequestFunction getFunction();

    void setLocation(Location location);

    void setUnits(TempUnit tempUnit);

    void setSubscriptionKey(String subscriptionKey);

    enum RequestFunction {
        CONDITIONS, FORECAST
    }
}
