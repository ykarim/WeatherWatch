package view.weatherPage;

import dataUpdate.UpdateData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import notification.TrayApp;
import util.FileLoad;
import util.WeatherImgImport;
import view.ViewConstants;
import view.settingsPage.SettingsPage;
import watcher.WatchDAO;
import watcher.Watcher;

public class WeatherPage extends Application implements Watcher {

    private static Stage stage;
    private final String EDIT_ICON_LOCATION = "gui/ic_edit/web/ic_edit_black_24dp_1x.png";
    private final String NO_IMAGE_LOCATION = "weatherIcons/no_image_available.png";
    private GridPane gridPane;
    private WeatherPageController controller = new WeatherPageController();
    private FileLoad fileLoad = new FileLoad();

    private ImageView weatherIconView;
    private Text txt_temperature;
    private Text txt_condition;
    private Button btn_edit_settings;

    public static void showStage() {
        if (stage != null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stage.show();
                    stage.toFront();
                }
            });
        }
    }

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WatchDAO.addWatcher(this);
        stage = primaryStage;
        stage.setTitle(ViewConstants.PROGRAM_TITLE);
        stage.getIcons().add(fileLoad.loadImageFile(ViewConstants.PROGRAM_ICON_LOCATION));

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(gridPane, ViewConstants.PROGRAM_WIDTH, ViewConstants.PROGRAM_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        gridPane.getStylesheets().add(this.getClass().getClassLoader().getResource("gui/css/weatherwatch.css")
                .toExternalForm());
        loadWeatherImages();
        addGuiElements();
        TrayApp.initialize();
    }

    private void loadWeatherImages() {
        boolean[] result = WeatherImgImport.initiate();
    }

    private void addGuiElements() {
        if (controller.getWeatherBackgroundClass() != null) {
            gridPane.setId(controller.getWeatherBackgroundClass());
        }

        weatherIconView = new ImageView(controller.getWeatherIcon() != null ?
                controller.getWeatherIcon() : fileLoad.loadImageFile(NO_IMAGE_LOCATION));
        weatherIconView.fitWidthProperty().bind(stage.widthProperty().divide(3));
        weatherIconView.fitHeightProperty().bind(stage.heightProperty().divide(2));
        GridPane.setHgrow(weatherIconView, Priority.ALWAYS);
        GridPane.setHalignment(weatherIconView, HPos.CENTER);
        GridPane.setVgrow(weatherIconView, Priority.ALWAYS);
        GridPane.setValignment(weatherIconView, VPos.CENTER);
        gridPane.add(weatherIconView, 0, 0);

        GridPane gridPane_conditions = new GridPane();
        gridPane_conditions.setHgap(10);
        gridPane_conditions.setVgap(10);
        GridPane.setHgrow(gridPane_conditions, Priority.ALWAYS);
        GridPane.setHalignment(gridPane_conditions, HPos.CENTER);
        GridPane.setVgrow(gridPane_conditions, Priority.ALWAYS);
        GridPane.setValignment(gridPane_conditions, VPos.TOP);
        gridPane.add(gridPane_conditions, 1, 0);

        txt_temperature = new Text();
        txt_temperature.setText(controller.getCurrentWeatherTemperatureText());
        txt_temperature.setFont(new Font(48));
        txt_temperature.setStyle("-fx-font-weight: bold");
        GridPane.setHgrow(txt_temperature, Priority.ALWAYS);
        GridPane.setHalignment(txt_temperature, HPos.LEFT);
        GridPane.setVgrow(txt_temperature, Priority.ALWAYS);
        GridPane.setValignment(txt_temperature, VPos.TOP);
        gridPane_conditions.add(txt_temperature, 0, 0);

        txt_condition = new Text();
        txt_condition.setText(controller.getCurrentWeatherConditionText());
        txt_condition.setFont(new Font(24));
        GridPane.setHgrow(txt_condition, Priority.ALWAYS);
        GridPane.setHalignment(txt_condition, HPos.LEFT);
        GridPane.setVgrow(txt_condition, Priority.ALWAYS);
        GridPane.setValignment(txt_condition, VPos.TOP);
        gridPane_conditions.add(txt_condition, 0, 1);

        btn_edit_settings = new Button();
        ImageView imgView = new ImageView(fileLoad.loadImageFile(EDIT_ICON_LOCATION));
        btn_edit_settings.setGraphic(imgView);
        GridPane.setHgrow(btn_edit_settings, Priority.ALWAYS);
        GridPane.setHalignment(btn_edit_settings, HPos.RIGHT);
        GridPane.setVgrow(btn_edit_settings, Priority.ALWAYS);
        GridPane.setValignment(btn_edit_settings, VPos.TOP);
        gridPane.add(btn_edit_settings, 2, 0);

        btn_edit_settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SettingsPage settingsPage = new SettingsPage(gridPane, stage);
                stage.getScene().setRoot(settingsPage.getRootPane());
            }
        });

        ForecastsPane gridPane_forecasts = new ForecastsPane(stage);
        GridPane.setHgrow(gridPane_forecasts, Priority.ALWAYS);
        GridPane.setHalignment(gridPane_forecasts, HPos.CENTER);
        gridPane.add(gridPane_forecasts, 0, 1, 3, 1);
    }

    @Override
    public void updateData(Object updatedData) {
        if (controller.getWeatherBackgroundClass() != null) {
            gridPane.setId(controller.getWeatherBackgroundClass());
        }

        if (controller.getWeatherIcon() != null) {
            weatherIconView.setImage(controller.getWeatherIcon());
        } else {
            weatherIconView.setImage(fileLoad.loadImageFile(NO_IMAGE_LOCATION));
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
