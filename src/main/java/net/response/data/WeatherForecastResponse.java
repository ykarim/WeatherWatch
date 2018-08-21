package net.response.data;

import net.response.WeatherResponse;
import org.apache.http.StatusLine;
import weather.Weather;

import java.util.ArrayList;

public class WeatherForecastResponse implements WeatherResponse {

    private StatusLine statusLine;
    private ArrayList<Weather> forecasts;

    public WeatherForecastResponse(StatusLine statusLine, ArrayList<Weather> forecasts) {
        this.statusLine = statusLine;
        this.forecasts = forecasts;
    }

    public ArrayList<Weather> getForecasts() {
        return forecasts;
    }

    public void setForecasts(ArrayList<Weather> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public StatusLine getStatus() {
        return null;
    }
}
