package ui.dashboard;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.util.AppController;
import ui.util.AppScene;
import util.FileImport;

import java.io.IOException;

public class DashboardScene implements AppScene {

    private String sceneLocation = "ui/fxml/dashboard/dashboard.fxml";
    private Parent root;
    private DashboardController dashboardController;
    private boolean isStarted;

    @Override
    public Parent start() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FileImport.getFileURL(FileImport.importLocalFile(sceneLocation)));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dashboardController = loader.getController();
        dashboardController.initialize(null);

        return root;
    }

    @Override
    public Parent getParent() {
        return root;
    }

    @Override
    public AppController getController() {
        return dashboardController;
    }

    @Override
    public Type getType() {
        return Type.DASHBOARD;
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
