package queue;

import dao.ForecastDAO;

import java.util.TimerTask;

public class ForecastTask extends TimerTask implements UpdateTask {

    private String cityName, zipCode, countryCode;
    private ForecastDAO forecastDAO;

    public ForecastTask(String cityName) {
        this.cityName = cityName;
        forecastDAO = new ForecastDAO();
    }

    public ForecastTask(String zipCode, String countryCode) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
        forecastDAO = new ForecastDAO();
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
            forecastDAO.getForecast(cityName);
        } else if (zipCode != null && countryCode != null) {
            forecastDAO.getForecast(zipCode, countryCode);
        }
    }
}
