package ui.weatherPage;

import dao.ForecastDAO;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import watcher.WatchDAO;
import watcher.Watcher;
import weather.Forecast;

import java.util.List;

public class ForecastsPane extends GridPane implements Watcher {

    private Stage stage;

    private ForecastDAO forecastDAO = new ForecastDAO();

    private List<Forecast> forecastList;

    private Forecast forecastOne;
    private Forecast forecastTwo;
    private Forecast forecastThree;

    private ForecastDisplayPane forecastOnePane;
    private ForecastDisplayPane forecastTwoPane;
    private ForecastDisplayPane forecastThreePane;

    public ForecastsPane(Stage stage) {
        this.stage = stage;
        WatchDAO.addWatcher(this);
        getForecasts();
        setupView();
    }

    private void getForecasts() {
        //TODO: Change to let user select number of forecasts
        forecastList = ForecastDAO.getForecasts();

        if (forecastList.size() > 3) {
            forecastOne = forecastList.get(0);
            forecastTwo = forecastList.get(1);
            forecastThree = forecastList.get(2);
        }
    }

    private void setupView() {
        setHgap(10);
        setVgap(10);

        forecastOnePane = new ForecastDisplayPane(stage, forecastOne);
        GridPane.setHalignment(forecastOnePane, HPos.CENTER);
        GridPane.setHgrow(forecastOnePane, Priority.ALWAYS);
        GridPane.setVgrow(forecastOnePane, Priority.ALWAYS);

        forecastTwoPane = new ForecastDisplayPane(stage, forecastTwo);
        GridPane.setHalignment(forecastTwoPane, HPos.CENTER);
        GridPane.setHgrow(forecastTwoPane, Priority.ALWAYS);
        GridPane.setVgrow(forecastTwoPane, Priority.ALWAYS);

        forecastThreePane = new ForecastDisplayPane(stage, forecastThree);
        GridPane.setHalignment(forecastThreePane, HPos.CENTER);
        GridPane.setHgrow(forecastThreePane, Priority.ALWAYS);
        GridPane.setVgrow(forecastThreePane, Priority.ALWAYS);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getChildren().clear();
                add(forecastOnePane, 0, 0);
                add(forecastTwoPane, 1, 0);
                add(forecastThreePane, 2, 0);
            }
        });
    }

    @Override
    public void updateData(Object updatedData) {
        forecastList = ForecastDAO.getForecasts();

        if (forecastList.size() > 3) {
            forecastOne = forecastList.get(0);
            forecastTwo = forecastList.get(1);
            forecastThree = forecastList.get(2);
        }

        setupView();
    }
}
