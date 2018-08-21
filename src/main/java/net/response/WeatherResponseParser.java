package net.response;

import net.request.WeatherRequest;
import org.apache.http.HttpResponse;

import java.io.IOException;

public abstract class WeatherResponseParser {

    public WeatherResponse parseResponse(WeatherRequest.RequestFunction requestFunction, HttpResponse response) throws IOException {
        switch (requestFunction) {

            case CONDITIONS:
                return parseConditionsResponse(response);
            case FORECAST:
                return parseForecastResponse(response);
        }

        return null;
    }

    public abstract WeatherResponse parseConditionsResponse(HttpResponse response) throws IOException;

    public abstract WeatherResponse parseForecastResponse(HttpResponse response) throws IOException;
}
