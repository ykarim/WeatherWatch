package ui.dashboard;

import dao.WeatherDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.util.AppController;
import ui.util.Bundle;
import watcher.WatchDAO;
import watcher.Watcher;

public class DashboardController implements AppController, Watcher {

    @FXML
    private VBox vBox_weatherPane;

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
        WatchDAO.addWatcher(this);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void updateData(Object updatedData) {
        refresh();
    }
}
