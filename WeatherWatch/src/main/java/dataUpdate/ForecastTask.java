package dataUpdate;

import network.WeatherDataAccess;

import java.util.TimerTask;

public class ForecastTask extends TimerTask implements UpdateTask {

    private String cityName, zipCode, countryCode;

    public ForecastTask(String cityName) {
        this.cityName = cityName;
    }

    public ForecastTask(String zipCode, String countryCode) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }

    @Override
    public void run() {
        refreshData();
    }

    @Override
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
            WeatherDataAccess.getForecastByName(cityName);
        } else if (zipCode != null && countryCode != null) {
            WeatherDataAccess.getForecastByZip(zipCode, countryCode);
        }
    }
}
