package ui.settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.util.AppController;
import ui.util.AppScene;
import util.FileImport;

import java.io.IOException;

public class SettingsScene implements AppScene {

    private String sceneLocation = "ui/fxml/settings/settings.fxml";
    private Parent root;
    private SettingsController settingsController;
    private boolean isStarted;

    @Override
    public Parent start() {
        FXMLLoader loader = new FXMLLoader(FileImport.getFileURL(FileImport.importLocalFile(sceneLocation)));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        settingsController = loader.getController();
        settingsController.initialize(null);

        return root;
    }

    @Override
    public Parent getParent() {
        return root;
    }

    @Override
    public AppController getController() {
        return settingsController;
    }

    @Override
    public Type getType() {
        return Type.SETTINGS;
    }

    @Override
    public boolean getStarted() {
        return isStarted;
    }

    @Override
    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }
}
