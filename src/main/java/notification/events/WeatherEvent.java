package notification.events;

import model.Forecast;
import model.Weather;
import org.joda.time.DateTime;
import util.Constants;

public class WeatherEvent {

    private Forecast forecast;
    private Weather.WeatherCondition condition;
    private DateTime timeOfNotif;

    public WeatherEvent(Weather.WeatherCondition condition, Forecast forecast) {
        this.condition = condition;
        this.forecast = forecast;
        this.timeOfNotif = forecast.getTimeOfWeather().minusHours(Constants.NOTIFY_HOURS_AHEAD);
    }

    public WeatherEvent(Weather.WeatherCondition condition, Forecast forecast, DateTime timeOfNotif) {
        this(condition, forecast);
        this.timeOfNotif = timeOfNotif;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public Weather.WeatherCondition getCondition() {
        return condition;
    }

    public DateTime getTimeOfNotif() {
        return timeOfNotif;
    }
}
