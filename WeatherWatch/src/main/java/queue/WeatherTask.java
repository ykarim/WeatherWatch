package queue;

import network.WeatherDataAccess;

import java.util.TimerTask;

public class WeatherTask extends TimerTask implements UpdateTask {

    private String cityName, zipCode, countryCode;

    public WeatherTask(String cityName) {
        this.cityName = cityName;
    }

    public WeatherTask(String zipCode, String countryCode) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }

    @Override
    public void run() {
        refreshData();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void setZipCode(String zipCode, String countryCode) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }

    @Override
    public void refreshData() {
        if (cityName != null) {
            WeatherDataAccess.getWeatherByName(cityName);
        } else if (zipCode != null && countryCode != null) {
            WeatherDataAccess.getWeatherByZip(zipCode, countryCode);
        }
    }
}
