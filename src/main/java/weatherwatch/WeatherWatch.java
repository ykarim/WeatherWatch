package weatherwatch;

import javafx.application.Application;
import notification.NotificationService;
import ui.weatherPage.WeatherPage;

public class WeatherWatch {

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                Application.launch(WeatherPage.class);
            }
        }.start();
        new NotificationService().startService();
    }
}
