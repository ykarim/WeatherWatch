package ui.forecastBox;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Weather;
import util.Constants;
import util.FileImport;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Locale;

public class ForecastBox extends VBox {

    @FXML
    private Label lbl_day, lbl_highTemp, lbl_lowTemp;

    @FXML
    private AnchorPane anchorPane_icon;

    @FXML
    private ImageView imgView_icon;

    private Weather weather;

    /**
     * Loads forecastBox file and fits image view to anchor pane container
     */
    ForecastBox() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        FileImport.getFileURL(
                                FileImport.importLocalFile("ui/fxml/forecastBox/forecastBox.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception); //Catching and printing produces error and distorts shape
        }

        imgView_icon.fitHeightProperty().bind(anchorPane_icon.heightProperty());
        imgView_icon.fitWidthProperty().bind(anchorPane_icon.widthProperty());
    }

    /**
     * Sets weather fields using weather data
     *
     * @param weather to be shown in pane
     */
    public ForecastBox(Weather weather) {
        this();

        lbl_day.setText(weather.getWeatherTime().dayOfWeek().getAsText(Locale.getDefault()));
        lbl_highTemp.setText(weather.getHighTemperature().getTemperatureString(Constants.PREFERRED_UNIT, 0));
        lbl_lowTemp.setText(weather.getLowTemperature().getTemperatureString(Constants.PREFERRED_UNIT, 0));

        try {
            imgView_icon.setImage(SwingFXUtils.toFXImage(ImageIO.read(weather.getImageURL()), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
