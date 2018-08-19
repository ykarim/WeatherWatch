package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.dashboard.DashboardScene;
import ui.util.SceneManager;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        DashboardScene dashboardScene = new DashboardScene();
        SceneManager.initProgram(stage, dashboardScene);
    }
}
