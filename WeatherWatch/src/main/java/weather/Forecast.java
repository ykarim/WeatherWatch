package weather;

import java.util.Date;

public class Forecast {

	private Weather weather;
	private Date timeOfWeather;
	
	public Forecast(Weather weather, Date timeOfWeather) {
		this.weather = weather;
		this.timeOfWeather = new Date(timeOfWeather.getTime());
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public Date getTimeOfWeather() {
		return new Date(timeOfWeather.getTime());
	}

	public void setTimeOfWeather(Date timeOfWeather) {
		this.timeOfWeather = new Date(timeOfWeather.getTime());
	}
}
