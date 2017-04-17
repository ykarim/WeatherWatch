package test.java.network;

import main.java.network.WeatherAccess;

public class WeatherAccessTest {

	public static void main(String[] args) {
		WeatherAccess.getCurrentWeatherDataByName("Paterson");
		WeatherAccess.getCurrentWeatherDataByZip("07501", "us");
		WeatherAccess.getForecastByName("Paterson");
	}
	
}
