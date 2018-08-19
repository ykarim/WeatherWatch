package ui.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.util.AppController;
import ui.util.Bundle;

public class DashboardController implements AppController {

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

    public DashboardController() {

    }

    @Override
    public void initialize(Bundle dataBundle) {

    }

    @Override
    public void refresh() {

    }
}
