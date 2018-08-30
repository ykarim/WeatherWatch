package ui.settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import model.Location;
import model.TempUnit;
import net.Subscription;
import ui.util.AppController;
import ui.util.Bundle;
import ui.util.SceneManager;
import util.Constants;

public class SettingsController implements AppController {

    @FXML
    private JFXTextField txt_city, txt_country, txt_key;

    private ToggleGroup toggle_unit, toggle_provider;

    @FXML
    private JFXRadioButton radio_imperial, radio_metric, radio_kelvin;

    @FXML
    private JFXRadioButton radio_owm;

    @FXML
    private JFXButton btn_submit;

    /**
     * Initializes scene by prepopulating all fields
     *
     * @param bundle
     */
    @Override
    public void initialize(Bundle bundle) {
        toggle_unit = new ToggleGroup();
        radio_imperial.setToggleGroup(toggle_unit);
        radio_metric.setToggleGroup(toggle_unit);
        radio_kelvin.setToggleGroup(toggle_unit);

        toggle_provider = new ToggleGroup();
        radio_owm.setToggleGroup(toggle_provider);

        //Initialize field values
        if (Constants.PREFERRED_LOCATION != null) {
            txt_city.setText(Constants.PREFERRED_LOCATION.getCity());
            txt_country.setText(Constants.PREFERRED_LOCATION.getCountry());
        }

        switch (Constants.PREFERRED_UNIT) {
            case FAHRENHEIT:
                radio_imperial.setSelected(true);
                break;
            case CELSIUS:
                radio_metric.setSelected(true);
                break;
            case KELVIN:
                radio_kelvin.setSelected(true);
                break;
        }

        switch (Subscription.getCurrentSubscriptionProvider()) {
            case NONE:
                break;
            case OPEN_WEATHER_MAP:
                radio_owm.setSelected(true);
                break;
        }

        txt_key.setText(Subscription.getSubscriptionKey());

        //Add validation as focus listener for text fields
        txt_city.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (!newValue) {
                        txt_city.validate();
                    }
                });

        txt_country.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (!newValue) {
                        txt_country.validate();
                    }
                });

        txt_key.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (!newValue) {
                        txt_key.validate();
                    }
                });
    }

    @Override
    public void refresh() {
        //Do nothing
    }

    /**
     * Returns to previous dashboard scene without making any changes
     * Refresh not needed as no data was manipulated
     * TODO: Check if user made any changes and show confirmation dialog
     *
     * @param event
     */
    @FXML
    private void handleReturnAction(ActionEvent event) {
        SceneManager.returnToPreviousScene();
    }

    /**
     * Validates input and upon receiving proper input, saves all data and returns to refreshed dashboard scene
     *
     * @param event
     */
    @FXML
    private void handleSubmitButton(ActionEvent event) {
        if (validateInput()) {
            //Save data
            if (Constants.PREFERRED_LOCATION == null) {
                Constants.PREFERRED_LOCATION = new Location(txt_city.getText(), txt_country.getText());
            } else {
                Constants.PREFERRED_LOCATION.setCity(txt_city.getText());
                Constants.PREFERRED_LOCATION.setCountry(txt_country.getText());
            }

            if (radio_imperial.isSelected()) {
                Constants.PREFERRED_UNIT = TempUnit.FAHRENHEIT;
            } else if (radio_metric.isSelected()) {
                Constants.PREFERRED_UNIT = TempUnit.CELSIUS;
            } else if (radio_kelvin.isSelected()) {
                Constants.PREFERRED_UNIT = TempUnit.KELVIN;
            }

            if (radio_owm.isSelected()) {
                Subscription.setCurrentSubscriptionProvider(Subscription.Provider.OPEN_WEATHER_MAP);
            }

            Subscription.setSubscriptionKey(txt_key.getText());

            //Return to weather dashboard and refresh with new information
            SceneManager.returnToPreviousScene();
            SceneManager.refreshCurrentScene();
        }
    }

    /**
     * Triggers validation on all text fields and returns boolean representing proper form entry
     *
     * @return true if all fields have proper input, false if otherwise
     */
    private boolean validateInput() {
        boolean validInput = true;

        //Validation required as fields may not have received focus yet
        txt_city.validate();
        txt_country.validate();
        txt_key.validate();

        if (txt_country.getText().isEmpty() || txt_city.getText().isEmpty() || txt_key.getText().isEmpty() ||
                toggle_unit.getSelectedToggle() == null || toggle_provider.getSelectedToggle() == null) {
            validInput = false;
        }

        return validInput;
    }
}
