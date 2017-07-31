package notification.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import notification.events.WeatherEvent;
import view.ViewConstants;
import view.weatherPage.WeatherPage;

public class NotificationDisplay {

    private Stage stage;
    private NotificationPane notificationPane;

    public NotificationDisplay(WeatherEvent weatherEvent) {
        stage = new Stage();
        notificationPane = new NotificationPane(stage, weatherEvent);
    }

    public void show() {
        stage.initOwner(WeatherPage.getStage());
        stage.setScene(new Scene(notificationPane, ViewConstants.NOTIFICATION_WIDTH,
                ViewConstants.NOTIFICATION_HEIGHT));
        stage.show();
    }
}
