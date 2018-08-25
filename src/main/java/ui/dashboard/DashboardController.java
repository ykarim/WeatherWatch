package ui.dashboard;

import com.jfoenix.controls.JFXButton;
import dao.WeatherDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.settings.SettingsScene;
import ui.util.AppController;
import ui.util.Bundle;
import ui.util.SceneManager;

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

    }

    /**
     *
     */
    @Override
    public void refresh() {
        //Pull new weather data


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
