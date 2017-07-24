package dataUpdate;

import java.util.ArrayList;
import java.util.Timer;

public class UpdateData {

    private static Timer weatherTimer = new Timer();
    private static Timer forecastTimer = new Timer();

    private static ArrayList<WeatherTask> weatherTasks = new ArrayList<>();
    private static ArrayList<ForecastTask> forecastTasks = new ArrayList<>();

    public static void addUpdater(UpdateTask updateTask, long delay, long period) {
        if (updateTask instanceof WeatherTask) {
            if (weatherTasks.size() == 0) {
                updateWeather((WeatherTask) updateTask, delay, period);
            } else {
                stopWeatherUpdates();
                weatherTimer = new Timer();
                updateWeather((WeatherTask) updateTask, delay, period);
            }
        } else if (updateTask instanceof ForecastTask) {
            if (forecastTasks.size() == 0) {
                updateForecast((ForecastTask) updateTask, delay, period);
            } else {
                stopForecastUpdates();
                forecastTimer = new Timer();
                updateForecast((ForecastTask) updateTask, delay, period);
            }
        }
    }

    private static void updateWeather(WeatherTask task, long delay, long period) {
        weatherTimer.scheduleAtFixedRate(task, delay * 60 * 1000, period * 60 * 1000);
        weatherTasks.add(task);
    }

    public static void stopWeatherUpdates() {
        weatherTimer.cancel();
        weatherTimer.purge();
        weatherTasks.clear();
    }

    private static void updateForecast(ForecastTask task, long delay, long period) {
        forecastTimer.scheduleAtFixedRate(task, delay * 60 * 1000, period * 60 * 1000);
        forecastTasks.add(task);
    }

    public static void stopForecastUpdates() {
        forecastTimer.cancel();
        forecastTimer.purge();
        forecastTasks.clear();
    }
}
