package net.request.openweathermap;

import net.request.WeatherGetRequest;
import org.apache.http.client.utils.URIBuilder;
import weather.Location;
import weather.TempUnit;

import java.net.URI;
import java.net.URISyntaxException;

public class OWMWeatherGetRequest extends WeatherGetRequest {

    private final String LOCATION_PARAM = "q";
    private final String SUBSCRIPTION_PARAM = "APPID";
    private final String UNITS_PARAM = "units";

    public OWMWeatherGetRequest(URI uri, RequestFunction function) {
        super(uri, function);
    }

    @Override
    public void setLocation(Location location) {
        //Format location parameter key
        String locationParamKey = location.getCity() + "," + location.getCountry();

        try {
            URI uriWithKey =
                    new URIBuilder(getRequest().getURI()).addParameter(LOCATION_PARAM, locationParamKey).build();
            getRequest().setURI(uriWithKey);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUnits(TempUnit tempUnit) {
        String unitParamKey = null;

        switch (tempUnit) {
            case FAHRENHEIT:
                unitParamKey = "imperial";
                break;
            case CELSIUS:
                unitParamKey = "metric";
                break;
            default:
            case KELVIN:
                unitParamKey = "metric";
                break;
        }

        try {
            URI uriWithKey =
                    new URIBuilder(getRequest().getURI()).addParameter(UNITS_PARAM, unitParamKey).build();
            getRequest().setURI(uriWithKey);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSubscriptionKey(String subscriptionKey) {
        try {
            URI uriWithKey =
                    new URIBuilder(getRequest().getURI()).addParameter(SUBSCRIPTION_PARAM, subscriptionKey).build();
            getRequest().setURI(uriWithKey);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
