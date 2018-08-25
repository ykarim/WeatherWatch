package ui.dashboard;

import com.jfoenix.controls.JFXButton;
import dao.WeatherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Weather;
import net.APIAccess;
import ui.settings.SettingsScene;
import ui.util.AppController;
import ui.util.Bundle;
import ui.util.SceneManager;
import util.Constants;

public class DashboardController implements AppController {

    @FXML
    private VBox vBox_weatherPane;

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXButton btn_settings;

    @FXML
    private Label lbl_timeText;

    @FXML
    private Label lbl_currentTemp;

    @FXML
    private Label lbl_currentCond;

    @FXML
    private Label lbl_location;

    private WeatherDAO weatherDAO = new WeatherDAO();

    public DashboardController() {

    }

    @Override
    public void initialize(Bundle dataBundle) {
        if (weatherDAO.getLatestWeather() != null) {
            Weather currentWeather = weatherDAO.getLatestWeather();

            lbl_currentTemp.setText(
                    currentWeather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).toPlainString());
            lbl_currentCond.setText(currentWeather.getCondition());
            lbl_location.setText(currentWeather.getLocation().toString());
            lbl_timeText.setText("Last Updated: " + currentWeather.getWeatherTime().toString());
        }
    }

    /**
     *
     */
    @Override
    public void refresh() {
        //Pull new weather data
        Weather weather = APIAccess.requestWeatherConditions(Constants.PREFERRED_LOCATION);

        lbl_currentTemp.setText(weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).toPlainString());
        lbl_currentCond.setText(weather.getCondition());
        lbl_location.setText(weather.getLocation().toString());
        lbl_timeText.setText("Last Updated: " + weather.getWeatherTime().toString());
    }

    @FXML
    private void handleRefreshButton(ActionEvent event) {
        refresh();
    }

    @FXML
    private void handleSettingsButton(ActionEvent event) {
        SceneManager.addScene(new SettingsScene());
    }
}
