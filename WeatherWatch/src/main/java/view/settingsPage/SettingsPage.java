package view.settingsPage;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SettingsPage {

    private GridPane gridPane;

    public SettingsPage() {
        gridPane = new GridPane();
    }

    public Pane getRootPane() {
        return gridPane;
    }
}
