package network;

import java.net.MalformedURLException;
import java.net.URL;

public class Requests {

    private final static String API_KEY = "&APPID=26b5c08cd8788979f3977188af39c4b0";
	private final static String URL = "http://api.openweathermap.org/data/2.5/";
    private final static String ICON_URL = "http://openweathermap.org/img/w/%s.png";

    static URL requestCurrentWeatherByName(String cityName) {
        URL url = null;
		try {
			url = new URL(URL + "weather?" + "q=" + cityName + API_KEY);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

    static URL requestCurrentWeatherByZip(String zipCode, String countryCode) {
        URL url = null;
		try {
			url = new URL(URL + "weather?" + "zip=" + zipCode + "," + countryCode + API_KEY);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

    static URL requestForecastByName(String cityName) {
        URL url = null;
		try {
			url = new URL(URL + "forecast?" + "q=" + cityName + API_KEY);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

    static URL requestForecastByZip(String zipCode, String countryCode) {
        URL url = null;
        try {
            url = new URL(URL + "forecast?" + "zip=" + zipCode + "," + countryCode + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL requestImageByIconCode(String iconCode) {
        URL url = null;
        try {
            url = new URL(String.format(ICON_URL, iconCode));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
