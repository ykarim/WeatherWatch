package net.response.data;

import model.Weather;
import net.response.WeatherResponse;
import org.apache.http.StatusLine;

public class WeatherConditionsResponse implements WeatherResponse {

    private StatusLine status;
    private Weather weather;

    public WeatherConditionsResponse(StatusLine status, Weather weather) {
        this.status = status;
        this.weather = weather;
    }

    @Override
    public StatusLine getStatus() {
        return status;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
