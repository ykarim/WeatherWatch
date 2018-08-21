package net;

import net.request.WeatherRequest;
import net.response.WeatherResponse;
import net.response.parser.owm.OWMWeatherResponseParser;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Connection {

    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    private static OWMWeatherResponseParser owmWeatherResponseParser = new OWMWeatherResponseParser();

    private static WeatherResponse processResponse(WeatherRequest.RequestFunction requestFunction, HttpResponse response) {
        try {
            switch (Subscription.getCurrentSubscriptionProvider()) {
                default:
                case OPEN_WEATHER_MAP:
                    return owmWeatherResponseParser.parseResponse(requestFunction, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private HttpResponse executeRequest(WeatherRequest request) {
        HttpResponse response = null;
        try {
            response = httpClient.execute(request.getRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
