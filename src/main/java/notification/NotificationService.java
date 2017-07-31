package notification;

import javafx.application.Platform;
import notification.events.WeatherEvent;
import notification.view.NotificationDisplay;
import org.joda.time.DateTime;
import util.Constants;
import watcher.Watcher;
import weather.Forecast;
import weather.Weather;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationService implements Watcher {

    private static ScheduledExecutorService scheduledExecutor;

    public static void startService() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    public static void startService(Weather.WeatherCondition... weatherConditions) {
        startService();
        if (weatherConditions != null) {
            for (Weather.WeatherCondition condition : weatherConditions) {
                addConditionToWatch(condition);
            }
        }
    }

    public static void addConditionToWatch(Weather.WeatherCondition weatherCondition) {
        Constants.CONDITIONS_TO_TRACK.add(weatherCondition);
    }

    private static void addNotification(final WeatherEvent weatherEvent) {
        scheduledExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        new NotificationDisplay(weatherEvent).show();
                    }
                });
            }
        }, weatherEvent.getTimeOfNotif().getMillis() - new DateTime().getMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void updateData(Object updatedData) {
        if (scheduledExecutor != null) {
            if (updatedData instanceof Forecast) {
                Forecast forecast = (Forecast) updatedData;
                for (Weather.WeatherCondition weatherCondition : forecast.getWeather().getCondition()) {
                    if (Constants.CONDITIONS_TO_TRACK.contains(weatherCondition)) {
                        addNotification(new WeatherEvent(weatherCondition, forecast));
                    }
                }
            }
        }
    }
}
