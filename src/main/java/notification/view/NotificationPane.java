package notification.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import notification.events.WeatherEvent;

public class NotificationPane extends GridPane {

    private Stage stage;
    private WeatherEvent weatherEvent;
    private NotificationPaneController controller;

    public NotificationPane(Stage stage, WeatherEvent weatherEvent) {
        this.stage = stage;
        this.weatherEvent = weatherEvent;
        this.controller = new NotificationPaneController(weatherEvent);

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(10));

        setupView();
    }

    private void setupView() {
        ImageView weatherIcon = new ImageView();
        weatherIcon.setImage(weatherEvent.getForecast().getWeather().getIcon());
        weatherIcon.fitWidthProperty().bind(stage.widthProperty().divide(3));
        weatherIcon.fitHeightProperty().bind(stage.heightProperty().multiply(0.75));
        GridPane.setHgrow(weatherIcon, Priority.ALWAYS);
        GridPane.setVgrow(weatherIcon, Priority.ALWAYS);
        GridPane.setHalignment(weatherIcon, HPos.CENTER);
        GridPane.setValignment(weatherIcon, VPos.CENTER);
        add(weatherIcon, 0, 0);

        Text txt_weather_event = new Text();
        txt_weather_event.setText(controller.getWeatherEventText());
        GridPane.setHgrow(txt_weather_event, Priority.ALWAYS);
        GridPane.setVgrow(txt_weather_event, Priority.ALWAYS);
        GridPane.setValignment(txt_weather_event, VPos.CENTER);
        add(txt_weather_event, 1, 0);
    }
}
