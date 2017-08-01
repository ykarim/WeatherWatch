package weatherwatch;

import javafx.application.Application;
import notification.NotificationService;
import view.weatherPage.WeatherPage;
import weather.Weather;

public class WeatherWatch {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                Application.launch(WeatherPage.class);
            }
        };
        thread.start();
        new NotificationService().startService(Weather.WeatherCondition.CLEAR);
    }
}
