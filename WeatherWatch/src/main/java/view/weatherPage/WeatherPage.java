package view.weatherPage;

import dataUpdate.UpdateData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.FileLoad;
import util.WeatherImgImport;
import view.ViewConstants;
import view.settingsPage.SettingsPage;
import watcher.WatchDAO;
import watcher.Watcher;

public class WeatherPage extends Application implements Watcher {

    private final String EDIT_ICON_LOCATION = "gui/ic_edit/web/ic_edit_black_24dp_1x.png";
    private Stage stage;
    private GridPane gridPane;
    private WeatherPageController controller = new WeatherPageController();
    private FileLoad fileLoad = new FileLoad();

    private ImageView weatherIconView;
    private Text txt_temperature;
    private Text txt_condition;
    private Button btn_edit_settings;

    @Override
    public void start(Stage primaryStage) throws Exception {
        WatchDAO.addWatcher(this);
        this.stage = primaryStage;
        primaryStage.setTitle(ViewConstants.PROGRAM_TITLE);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(gridPane, ViewConstants.PROGRAM_WIDTH, ViewConstants.PROGRAM_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        gridPane.getStylesheets().add(this.getClass().getClassLoader().getResource("gui/css/weatherwatch.css")
                .toExternalForm());
        loadWeatherImages();
        addGuiElements();
    }

    private void loadWeatherImages() {
        boolean[] result = WeatherImgImport.initiate();
    }

    private void addGuiElements() {
        if (controller.getWeatherBackground() != null) {
            BackgroundImage image = new BackgroundImage(controller.getWeatherBackground(), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            gridPane.setBackground(new Background(image));
        }

        weatherIconView = new ImageView();
        if (controller.getWeatherIcon() != null) {
            weatherIconView = new ImageView(controller.getWeatherIcon());
        }
        weatherIconView.setFitHeight(150);
        weatherIconView.setFitWidth(150);
        gridPane.add(weatherIconView, 0, 0);

        txt_temperature = new Text();
        txt_temperature.setText(controller.getCurrentWeatherTemperatureText());
        gridPane.add(txt_temperature, 1, 0);

        txt_condition = new Text();
        txt_condition.setText(controller.getCurrentWeatherConditionText());
        gridPane.add(txt_condition, 1, 1);

        btn_edit_settings = new Button();
        ImageView imgView = new ImageView(fileLoad.loadImageFile(EDIT_ICON_LOCATION));
        btn_edit_settings.setGraphic(imgView);
        gridPane.add(btn_edit_settings, 2, 0);

        btn_edit_settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SettingsPage settingsPage = new SettingsPage(gridPane, stage);
                stage.getScene().setRoot(settingsPage.getRootPane());
            }
        });
    }

    @Override
    public void updateData() {
        if (controller.getWeatherBackground() != null) {
            BackgroundImage image = new BackgroundImage(controller.getWeatherBackground(), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            gridPane.setBackground(new Background(image));
        }

        if (controller.getWeatherIcon() != null) {
            weatherIconView.setImage(controller.getWeatherIcon());
        } else {
            //Set image to ??? image
        }

        txt_temperature.setText(controller.getCurrentWeatherTemperatureText());

        txt_condition.setText(controller.getCurrentWeatherConditionText());
    }

    @Override
    public void stop() throws Exception {
        UpdateData.stopWeatherUpdates();
        UpdateData.stopForecastUpdates();
        super.stop();
    }

}
