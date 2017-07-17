package queue;

import dao.WeatherDAO;

import java.util.TimerTask;

public class WeatherTask extends TimerTask implements UpdateTask {

    private String cityName, zipCode, countryCode;
    private WeatherDAO weatherDAO;

    public WeatherTask(String cityName) {
        this.cityName = cityName;
        weatherDAO = new WeatherDAO();
    }

    public WeatherTask(String zipCode, String countryCode) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
        weatherDAO = new WeatherDAO();
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
            weatherDAO.getWeatherData(cityName);
        } else if (zipCode != null && countryCode != null) {
            weatherDAO.getWeatherData(zipCode, countryCode);
        }
    }
}
