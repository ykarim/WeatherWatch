package ui.dashboard;

import com.jfoenix.controls.JFXButton;
import dao.WeatherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.Weather;
import net.APIAccess;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ui.forecastBox.ForecastBox;
import ui.settings.SettingsScene;
import ui.util.AppController;
import ui.util.Bundle;
import ui.util.SceneManager;
import util.Constants;
import util.FileImport;

import java.math.BigDecimal;

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

    @FXML
    private GridPane gridPane_forecasts;

    private WeatherDAO weatherDAO = new WeatherDAO();

    public DashboardController() {

    }

    @Override
    public void initialize(Bundle dataBundle) {
        if (weatherDAO.getLatestWeather() != null) {
            Weather currentWeather = weatherDAO.getLatestWeather();
            setWeatherConditionFields(currentWeather);
        }
    }

    /**
     *
     */
    @Override
    public void refresh() {
        //Pull new weather data
        APIAccess.requestWeatherConditions(Constants.PREFERRED_LOCATION);
        setWeatherConditionFields(weatherDAO.getLatestWeather());

        APIAccess.requestWeatherForecast(Constants.PREFERRED_LOCATION);
        setWeatherForecastFields(weatherDAO.getWeatherForDay(new DateTime()),
                weatherDAO.getWeatherForDay(new DateTime().plusDays(1)),
                weatherDAO.getWeatherForDay(new DateTime().plusDays(2)));
    }

    @FXML
    private void handleRefreshButton(ActionEvent event) {
        refresh();
    }

    @FXML
    private void handleSettingsButton(ActionEvent event) {
        SceneManager.addScene(new SettingsScene());
    }

    private void setWeatherConditionFields(Weather weather) {
        vBox_weatherPane.setBackground(new Background(getBackgroundImage(weather.getCondition())));
        lbl_currentTemp.setText(weather.getTemperature().getTemperatureValue(Constants.PREFERRED_UNIT).setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString());
        lbl_currentCond.setText(weather.getCondition());
        lbl_location.setText(weather.getLocation().toString());

        //Create date pattern and set time label to last received weather data time
        DateTimeFormatter timeFormat = DateTimeFormat.forPattern("hh:mm a");
        lbl_timeText.setText("Last Updated: " + weather.getWeatherTime().toString(timeFormat));
    }

    /**
     * Fills forecastPane by creating ForecastBox for current, next day, and day after next day's weather
     *
     * @param today's            weather data
     * @param tomorrow's         weather data
     * @param dayAfterTomorrow's weather data
     */
    private void setWeatherForecastFields(Weather today, Weather tomorrow, Weather dayAfterTomorrow) {
        gridPane_forecasts.getChildren().clear();

        gridPane_forecasts.add(new ForecastBox(today), 0, 0);
        gridPane_forecasts.add(new ForecastBox(tomorrow), 1, 0);
        gridPane_forecasts.add(new ForecastBox(dayAfterTomorrow), 2, 0);
    }

    /**
     * Creates BackgroundImage object by retrieving condition and provider dependent image
     * TODO: Create image manager to extract based upon provider and defaults instead of manual file input
     *
     * @param condition current weather condition used to determine background
     */
    private BackgroundImage getBackgroundImage(String condition) {
        return new BackgroundImage(
                FileImport.importImage(FileImport.importLocalFile("backgrounds/owm/" + condition.toLowerCase() + ".jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
    }
}
