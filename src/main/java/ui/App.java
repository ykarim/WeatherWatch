package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import net.Subscription;
import ui.dashboard.DashboardScene;
import ui.settings.SettingsScene;
import ui.util.SceneManager;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        DashboardScene dashboardScene = new DashboardScene();
        SceneManager.initProgram(stage, dashboardScene);

        //Check Subscription status upon first init
        if (Subscription.getSubscriptionKey().isEmpty()) {
            SceneManager.addScene(new SettingsScene());
        }
    }
}
