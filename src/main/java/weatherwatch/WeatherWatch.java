package weatherwatch;

import javafx.application.Application;
import notification.NotificationService;
import view.weatherPage.WeatherPage;

public class WeatherWatch {

    public static void main(String[] args) {
        Application.launch(WeatherPage.class);
        NotificationService.startService();
    }
}
