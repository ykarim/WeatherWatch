package queue;

import dao.WeatherDAO;
import network.DataFormat;
import network.NetworkConnect;

import java.net.URL;
import java.util.TimerTask;

public class WeatherTask extends TimerTask implements UpdateTask {

    private URL weatherURL;
    private WeatherDAO weatherDAO;

    public WeatherTask(URL weatherURL) {
        this.weatherURL = weatherURL;
        weatherDAO = new WeatherDAO();
    }

    @Override
    public void run() {
        refreshData();
    }

    @Override
    public void setRequest(URL url) {
        weatherURL = url;
    }

    @Override
    public void refreshData() {
        if (weatherURL != null) {
            weatherDAO.addWeatherData(DataFormat.convertJSONToWeatherObj(NetworkConnect.getData(weatherURL)));
        }
    }
}
