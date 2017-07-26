package view.settingsPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.FileLoad;
import weather.Temperature;


public class SettingsPage {

    private final String BACK_ICON_LOCATION = "gui/ic_arrow_back/web/ic_arrow_back_black_24dp_1x.png";
    private Pane parentPane;
    private Stage stage;
    private GridPane gridPane;
    private FileLoad fileLoad = new FileLoad();
    private SettingsPageController controller = new SettingsPageController();
    private Button btn_back;
    private Text lbl_title;
    private Label lbl_blank;
    private GridPane gridPane_location_radio;
    private Label lbl_location_setting;
    private ToggleGroup toggle_location_preference;
    private RadioButton radio_city_name;
    private RadioButton radio_city_zip;
    private GridPane gridPane_location;
    private Label lbl_location;
    private TextField txt_location;
    private GridPane gridPane_unit_preference;
    private Label lbl_preferred_unit;
    private ToggleGroup toggle_preferred_unit;
    private RadioButton radio_fahrenheit;
    private RadioButton radio_celsius;
    private RadioButton radio_kelvin;
    private Button btn_submit;

    public SettingsPage(Pane parentPane, Stage stage) {
        this.parentPane = parentPane;
        this.stage = stage;

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        addGuiElements();
        addListeners();
    }

    public Pane getRootPane() {
        return gridPane;
    }

    private void addGuiElements() {
        btn_back = new Button();
        ImageView backIcon = new ImageView(fileLoad.loadImageFile(BACK_ICON_LOCATION));
        btn_back.setGraphic(backIcon);
        GridPane.setValignment(btn_back, VPos.TOP);
        GridPane.setHgrow(btn_back, Priority.ALWAYS);
        gridPane.add(btn_back, 0, 0);

        lbl_title = new Text();
        lbl_title.setText("Settings");
        lbl_title.setFont(new Font(36));
        GridPane.setHgrow(lbl_title, Priority.ALWAYS);
        GridPane.setHalignment(lbl_title, HPos.CENTER);
        GridPane.setMargin(lbl_title, new Insets(0, 10, 30, 10));
        gridPane.add(lbl_title, 1, 0);

        lbl_blank = new Label();
        GridPane.setHgrow(lbl_blank, Priority.ALWAYS);
        gridPane.add(lbl_blank, 2, 0);

        gridPane_location_radio = new GridPane();
        gridPane_location_radio.setHgap(10);
        gridPane_location_radio.setVgap(10);

        lbl_location_setting = new Label("Location Setting :");
        GridPane.setHgrow(lbl_location_setting, Priority.ALWAYS);
        gridPane_location_radio.add(lbl_location_setting, 0, 0);

        toggle_location_preference = new ToggleGroup();

        radio_city_name = new RadioButton("City Name");
        radio_city_name.setToggleGroup(toggle_location_preference);
        GridPane.setHgrow(radio_city_name, Priority.ALWAYS);
        GridPane.setHalignment(radio_city_name, HPos.CENTER);
        gridPane_location_radio.add(radio_city_name, 1, 0);

        radio_city_zip = new RadioButton("Zip Code");
        radio_city_zip.setToggleGroup(toggle_location_preference);
        GridPane.setHgrow(radio_city_zip, Priority.ALWAYS);
        GridPane.setHalignment(radio_city_zip, HPos.CENTER);
        gridPane_location_radio.add(radio_city_zip, 2, 0);

        if (controller.getPreferredLocationSetting() != null) {
            if (controller.getPreferredLocationSetting()) {
                toggle_location_preference.selectToggle(radio_city_name);
            } else {
                toggle_location_preference.selectToggle(radio_city_zip);
            }
        }

        GridPane.setHalignment(gridPane_location_radio, HPos.CENTER);
        GridPane.setHgrow(gridPane_location_radio, Priority.ALWAYS);
        gridPane.add(gridPane_location_radio, 1, 1);

        gridPane_location = new GridPane();
        gridPane_location.setVgap(10);
        gridPane_location.setHgap(10);

        lbl_location = new Label("Location :");
        GridPane.setHgrow(lbl_location, Priority.ALWAYS);
        gridPane_location.add(lbl_location, 0, 0);

        txt_location = new TextField(controller.getPreferredLocation());
        GridPane.setHgrow(txt_location, Priority.ALWAYS);
        gridPane_location.add(txt_location, 1, 0);

        GridPane.setHgrow(gridPane_location, Priority.ALWAYS);
        gridPane.add(gridPane_location, 1, 2);

        gridPane_unit_preference = new GridPane();
        GridPane.setHgrow(gridPane_unit_preference, Priority.ALWAYS);
        gridPane.add(gridPane_unit_preference, 1, 3);

        lbl_preferred_unit = new Label("Preferred Unit :");
        GridPane.setHgrow(lbl_preferred_unit, Priority.ALWAYS);
        gridPane_unit_preference.add(lbl_preferred_unit, 0, 0);

        toggle_preferred_unit = new ToggleGroup();

        radio_fahrenheit = new RadioButton("Fahrenheit");
        radio_fahrenheit.setToggleGroup(toggle_preferred_unit);
        GridPane.setHgrow(radio_fahrenheit, Priority.ALWAYS);
        GridPane.setHalignment(radio_fahrenheit, HPos.CENTER);
        gridPane_unit_preference.add(radio_fahrenheit, 1, 0);

        radio_celsius = new RadioButton("Celsius");
        radio_celsius.setToggleGroup(toggle_preferred_unit);
        GridPane.setHgrow(radio_celsius, Priority.ALWAYS);
        GridPane.setHalignment(radio_celsius, HPos.CENTER);
        gridPane_unit_preference.add(radio_celsius, 2, 0);

        radio_kelvin = new RadioButton("Kelvin");
        radio_kelvin.setToggleGroup(toggle_preferred_unit);
        GridPane.setHgrow(radio_kelvin, Priority.ALWAYS);
        GridPane.setHalignment(radio_kelvin, HPos.CENTER);
        gridPane_unit_preference.add(radio_kelvin, 3, 0);

        if (controller.getPreferredUnit() == Temperature.Unit.FAHRENHEIT) {
            toggle_preferred_unit.selectToggle(radio_fahrenheit);
        } else if (controller.getPreferredUnit() == Temperature.Unit.CELSIUS) {
            toggle_preferred_unit.selectToggle(radio_celsius);
        } else if (controller.getPreferredUnit() == Temperature.Unit.KELVIN) {
            toggle_preferred_unit.selectToggle(radio_kelvin);
        }

        btn_submit = new Button("Submit");
        GridPane.setHalignment(btn_submit, HPos.RIGHT);
        gridPane.add(btn_submit, 2, 4);
    }

    private void addListeners() {
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.getScene().setRoot(parentPane);
            }
        });

        txt_location.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    submitData();
                }
            }
        });

        btn_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                submitData();
            }
        });

        btn_submit.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    submitData();
                }
            }
        });
    }

    private boolean validateInput() {
        boolean correctInput = true;

        //Check location preference radio buttons
        Label require_location_pref = new Label("This field is required");
        String require_loc_pref_id = "require-loc-pref";
        require_location_pref.setId(require_loc_pref_id);
        if (toggle_location_preference.getSelectedToggle() == null) {
            require_location_pref.setStyle("-fx-font-weight: bold");
            require_location_pref.setTextFill(Color.RED);
            GridPane.setHalignment(require_location_pref, HPos.CENTER);
            GridPane.setHgrow(require_location_pref, Priority.ALWAYS);
            gridPane_location_radio.add(require_location_pref, 0, 1, 3, 1);
            correctInput = false;
        } else {
            gridPane_location_radio.getChildren().remove(
                    gridPane_location_radio.lookup("#" + require_loc_pref_id));
        }

        //Check location text field
        if (txt_location.getText() == null || txt_location.getText().trim().isEmpty()) {
            txt_location.setBorder(new Border(
                    new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                            new CornerRadii(5), BorderWidths.DEFAULT)));
            correctInput = false;
        }

        //Check unit preference radio buttons
        Label required_unit_pref = new Label("This field is required");
        String required_unit_pref_id = "required-unit-pref";
        required_unit_pref.setId(required_unit_pref_id);
        if (toggle_preferred_unit.getSelectedToggle() == null) {
            required_unit_pref.setStyle("-fx-font-weight: bold");
            required_unit_pref.setTextFill(Color.RED);
            GridPane.setHalignment(required_unit_pref, HPos.CENTER);
            GridPane.setHgrow(required_unit_pref, Priority.ALWAYS);
            gridPane_unit_preference.add(required_unit_pref, 0, 1,
                    4, 1);
            correctInput = false;
        } else {
            gridPane_unit_preference.getChildren().remove(
                    gridPane_unit_preference.lookup("#" + required_unit_pref_id));
        }

        return correctInput;
    }

    private void submitData() {
        Boolean locationPreference = null;
        if (toggle_location_preference.getSelectedToggle() == radio_city_name) {
            locationPreference = true;
        } else if (toggle_location_preference.getSelectedToggle() == radio_city_zip) {
            locationPreference = false;
        }

        Temperature.Unit preferredUnit = null;
        if (toggle_preferred_unit.getSelectedToggle() == radio_fahrenheit) {
            preferredUnit = Temperature.Unit.FAHRENHEIT;
        } else if (toggle_preferred_unit.getSelectedToggle() == radio_celsius) {
            preferredUnit = Temperature.Unit.CELSIUS;
        } else if (toggle_preferred_unit.getSelectedToggle() == radio_kelvin) {
            preferredUnit = Temperature.Unit.KELVIN;
        }

        if (validateInput()) {
            controller.submitData(locationPreference, txt_location.getText().trim(), preferredUnit);
            stage.getScene().setRoot(parentPane);
        }
    }
}
