package view.weatherPage;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.joda.time.DateTime;
import util.Constants;
import util.FileLoad;
import weather.Forecast;
import weather.Temperature;

import java.math.RoundingMode;
import java.util.Date;

import static util.Constants.NUM_DECIMAL_PLACES;

public class ForecastDisplayPane extends GridPane {

    private final String NO_IMAGE_LOCATION = "weatherIcons/no_image_available.png";
    private Stage stage;
    private Forecast forecast;
    private int hoursAhead;
    private FileLoad fileLoad = new FileLoad();

    public ForecastDisplayPane(Stage stage, Forecast forecast) {
        this.stage = stage;
        this.forecast = forecast;

        if (forecast != null) {
            this.hoursAhead = forecast.getTimeOfWeather().getHourOfDay() - new DateTime(new Date()).getHourOfDay();
        }

        setupView();
    }

    private void setupView() {
        setHgap(10);
        setVgap(10);

        Text txt_forecast_time = new Text();
        txt_forecast_time.setText(hoursAhead + " hours ahead");
        GridPane.setHalignment(txt_forecast_time, HPos.CENTER);
        GridPane.setHgrow(txt_forecast_time, Priority.ALWAYS);

        ImageView weatherIconView;
        weatherIconView = new ImageView(
                forecast != null ? forecast.getWeather().getIcon() :
                        fileLoad.loadImageFile(NO_IMAGE_LOCATION));
        weatherIconView.fitHeightProperty().bind(stage.heightProperty().divide(6));
        weatherIconView.fitWidthProperty().bind(stage.widthProperty().divide(6));
        GridPane.setHalignment(weatherIconView, HPos.CENTER);
        GridPane.setValignment(weatherIconView, VPos.CENTER);
        GridPane.setHgrow(weatherIconView, Priority.ALWAYS);
        GridPane.setVgrow(weatherIconView, Priority.ALWAYS);

        Text txt_temp = new Text();
        if (forecast != null) {
            switch (Constants.PREFERRED_UNIT) {
                case CELSIUS:
                    final String degreeCelsius = "\u2103";
                    txt_temp.setText(forecast.getWeather().getTemperature().getTemperatureValue(Temperature.Unit.CELSIUS)
                            .setScale(NUM_DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString()
                            + degreeCelsius);
                case FAHRENHEIT:
                    final String degreeFahrenheit = "\u2109";
                    txt_temp.setText(forecast.getWeather().getTemperature().getTemperatureValue(Temperature.Unit.FAHRENHEIT)
                            .setScale(NUM_DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString()
                            + degreeFahrenheit);
                case KELVIN:
                    final String degreeKelvin = "\u212A";
                    txt_temp.setText(forecast.getWeather().getTemperature().getTemperatureValue(Temperature.Unit.KELVIN)
                            .setScale(NUM_DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString()
                            + degreeKelvin);
            }
        } else {
            txt_temp.setText("???");
        }
        GridPane.setHalignment(txt_temp, HPos.CENTER);
        GridPane.setValignment(txt_temp, VPos.CENTER);
        GridPane.setHgrow(txt_temp, Priority.ALWAYS);
        GridPane.setVgrow(txt_temp, Priority.ALWAYS);


        add(txt_forecast_time, 0, 0);
        add(weatherIconView, 0, 1);
        add(txt_temp, 0, 2);
    }
}
