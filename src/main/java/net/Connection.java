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

    /**
     * Depending on API used, passes response to API's respective response parser to further process
     *
     * @param requestFunction API function called
     * @param response        response received from API
     * @return WeatherResponse containing processed data of response
     */
    static WeatherResponse processResponse(WeatherRequest.RequestFunction requestFunction, HttpResponse response) {
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

    /**
     * Sends request to API
     * @param request to send
     * @return HttpResponse received from API
     */
    static HttpResponse executeRequest(WeatherRequest request) {
        HttpResponse response = null;
        try {
            response = httpClient.execute(request.getRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
