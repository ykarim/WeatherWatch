import javafx.application.Application;
import ui.App;

public class WeatherWatch {

    public static void main(String[] args) {
        //Launch UI
        App weatherWatchApp = new App();
        Application.launch(weatherWatchApp.getClass(), args);
    }
}
