package weather;

import org.joda.time.DateTime;

import java.util.Date;

public class Forecast {

	private Weather weather;
    private DateTime timeOfWeather;

	public Forecast(Weather weather, Date timeOfWeather) {
		this.weather = weather;
        this.timeOfWeather = new DateTime(timeOfWeather);
    }

    public Forecast(Weather weather, DateTime timeOfWeather) {
        this.weather = weather;
        this.timeOfWeather = timeOfWeather;
    }

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

    public DateTime getTimeOfWeather() {
        return timeOfWeather;
    }

	public void setTimeOfWeather(Date timeOfWeather) {
        this.timeOfWeather = new DateTime(timeOfWeather);
    }

    public void setTimeOfWeather(DateTime timeOfWeather) {
        this.timeOfWeather = timeOfWeather;
    }
}
