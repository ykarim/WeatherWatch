package network;

import java.net.MalformedURLException;
import java.net.URL;

public class Requests {
	
	private final static String API_KEY = "&APPID=26b5c08cd8788979f3977188af39c4b0";
	private final static String URL = "http://api.openweathermap.org/data/2.5/";
	
	public static URL requestCurrentWeatherByName(String cityName) {
		URL url = null;
		try {
			url = new URL(URL + "weather?" + "q=" + cityName + API_KEY);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	public static URL requestCurrentWeatherByZip(String zipCode, String countryCode) {
		URL url = null;
		try {
			url = new URL(URL + "weather?" + "zip=" + zipCode + "," + countryCode + API_KEY);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	public static URL requestForecastByName(String cityName) {
		URL url = null;
		try {
			url = new URL(URL + "forecast?" + "q=" + cityName + API_KEY);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

    public static URL requestForecastByZip(String zipCode, String countryCode) {
        URL url = null;
        try {
            url = new URL(URL + "forecast?" + "zip=" + zipCode + "," + countryCode + API_KEY);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;
    }
}
