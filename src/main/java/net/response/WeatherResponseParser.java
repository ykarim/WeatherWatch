package net.response;

import net.request.WeatherRequest;
import net.response.data.WeatherConditionsResponse;
import net.response.data.WeatherForecastResponse;
import org.apache.http.HttpResponse;

import java.io.IOException;

public abstract class WeatherResponseParser {

    /**
     * Parse API response depending on API function called
     *
     * @param requestFunction API function called in request
     * @param response        Response received from API
     * @return WeatherResponse containing function respective data
     * @throws IOException if error occurs while reading JSON string
     */
    public WeatherResponse parseResponse(WeatherRequest.RequestFunction requestFunction, HttpResponse response) throws IOException {
        switch (requestFunction) {

            case CONDITIONS:
                return parseConditionsResponse(response);
            case FORECAST:
                return parseForecastResponse(response);
        }

        return null;
    }

    /**
     * Parse API-respective response from current conditions request and store data into WeatherConditionsResponse
     * object
     *
     * @param response Response received from server after conditions function call
     * @return WeatherConditionsResponse containing parsed data from sent current conditions request
     * @throws IOException if error occurs while reading JSON string
     */
    public abstract WeatherConditionsResponse parseConditionsResponse(HttpResponse response) throws IOException;

    /**
     * Parse API-respective response from forecast request and store data into WeatherForecastResponse object
     *
     * @param response Response received from server after forecast function call
     * @return WeatherForecastResponse containing parsed data from sent forecast request
     * @throws IOException if error occurs while reading JSON string
     */
    public abstract WeatherForecastResponse parseForecastResponse(HttpResponse response) throws IOException;
}
