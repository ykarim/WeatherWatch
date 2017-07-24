package view.settingsPage;

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

    void submitData(Boolean PREFER_CITY_NAME_OVER_ZIP, String locationValue, Temperature.Unit preferredUnit) {
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
    }
}
