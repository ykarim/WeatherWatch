package net.request;

import org.apache.http.client.methods.HttpGet;
import weather.Location;
import weather.TempUnit;

import java.net.URI;

public abstract class WeatherGetRequest implements WeatherRequest {

    private HttpGet httpGet;
    private RequestFunction function;

    public WeatherGetRequest(URI uri, RequestFunction function) {
        httpGet = new HttpGet(uri);
        this.function = function;
    }

    @Override
    public HttpGet getRequest() {
        return httpGet;
    }

    @Override
    public RequestFunction getFunction() {
        return function;
    }

    @Override
    public abstract void setLocation(Location location);

    @Override
    public abstract void setUnits(TempUnit tempUnit);

    @Override
    public abstract void setSubscriptionKey(String subscriptionKey);
}
