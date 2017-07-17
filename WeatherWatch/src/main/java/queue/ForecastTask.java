package queue;

import dao.ForecastDAO;
import network.DataFormat;
import network.NetworkConnect;

import java.net.URL;
import java.util.TimerTask;

public class ForecastTask extends TimerTask implements UpdateTask {

    private URL forecastURL;
    private ForecastDAO forecastDAO;

    public ForecastTask(URL forecastURL) {
        this.forecastURL = forecastURL;
        forecastDAO = new ForecastDAO();
    }

    @Override
    public void run() {
        refreshData();
    }

    @Override
    public void setRequest(URL url) {
        forecastURL = url;
    }

    @Override
    public void refreshData() {
        if (forecastURL != null) {
            forecastDAO.addForecasts(DataFormat.convertJSONToForecastObjs(NetworkConnect.getData(forecastURL)));
        }
    }
}
