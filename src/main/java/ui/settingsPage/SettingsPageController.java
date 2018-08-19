package ui.settingsPage;

import dataUpdate.ForecastTask;
import dataUpdate.UpdateData;
import dataUpdate.WeatherTask;
import util.Constants;
import weather.Temperature;

class SettingsPageController {

    Boolean getPreferredLocationSetting() {
        return Constants.PREFER_CITY_NAME_OVER_ZIP;
    }

    String getPreferredLocation() {
        if (getPreferredLocationSetting() != null) {
            if (getPreferredLocationSetting()) {
                return Constants.PREFERRED_CITY_NAME;
            } else {
                return Constants.PREFERRED_ZIP;
            }
        }
        return "";
    }

    Temperature.Unit getPreferredUnit() {
        return Constants.PREFERRED_UNIT;
    }

    void submitData(Boolean PREFER_CITY_NAME_OVER_ZIP, String locationValue, Temperature.Unit preferredUnit,
                    String dateTimeFormat) {
        if (PREFER_CITY_NAME_OVER_ZIP != null) {
            Constants.PREFER_CITY_NAME_OVER_ZIP = PREFER_CITY_NAME_OVER_ZIP;
        }

        if (PREFER_CITY_NAME_OVER_ZIP != null) {
            if (PREFER_CITY_NAME_OVER_ZIP) {
                Constants.PREFERRED_CITY_NAME = locationValue;
            } else {
                Constants.PREFERRED_ZIP = locationValue;
            }
        }

        if (preferredUnit != null) {
            Constants.PREFERRED_UNIT = preferredUnit;
        }

        if (dateTimeFormat != null) {
            Constants.DATE_TIME_FORMAT = dateTimeFormat;
        }

        createUpdateTasks();
    }

    private void createUpdateTasks() {
        if (Constants.PREFER_CITY_NAME_OVER_ZIP) {
            UpdateData.addUpdater(new WeatherTask(Constants.PREFERRED_CITY_NAME),
                    0, Constants.WEATHER_UPDATE_PERIOD_MINUTES);
            UpdateData.addUpdater(new ForecastTask(Constants.PREFERRED_CITY_NAME),
                    0, Constants.FORECAST_UPDATE_PERIOD_MINUTES);
        } else if (!Constants.PREFER_CITY_NAME_OVER_ZIP) {
            UpdateData.addUpdater(new WeatherTask(Constants.PREFERRED_ZIP, Constants.PREFERRED_COUNTRY_CODE),
                    0, Constants.WEATHER_UPDATE_PERIOD_MINUTES);
            UpdateData.addUpdater(new ForecastTask(Constants.PREFERRED_ZIP, Constants.PREFERRED_COUNTRY_CODE),
                    0, Constants.FORECAST_UPDATE_PERIOD_MINUTES);
        }
    }
}
