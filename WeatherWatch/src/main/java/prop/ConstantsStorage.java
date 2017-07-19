package prop;

import util.Constants;

public class ConstantsStorage implements PropertyStorage {

    @Override
    public void storeProperties(PropertiesDAO propertiesDAO) {
        propertiesDAO.storeProperty(PropertyKey.FIRST_RUN.getKey(), String.valueOf(Constants.FIRST_RUN));
        propertiesDAO.storeProperty(PropertyKey.PREFER_CITY_NAME_OVER_ZIP.getKey(),
                String.valueOf(Constants.PREFER_CITY_NAME_OVER_ZIP));
        propertiesDAO.storeProperty(PropertyKey.PREFERRED_CITY_NAME.getKey(), Constants.PREFERRED_CITY_NAME);
        propertiesDAO.storeProperty(PropertyKey.PREFERRED_ZIP.getKey(), Constants.PREFERRED_ZIP);
        propertiesDAO.storeProperty(PropertyKey.PREFERRED_COUNTRY_CODE.getKey(), Constants.PREFERRED_COUNTRY_CODE);
        propertiesDAO.storeProperty(PropertyKey.PREFERRED_UNIT.getKey(), Constants.PREFERRED_UNIT.getUnitName());
        propertiesDAO.storeProperty(PropertyKey.CUSTOM_BKG_IMAGE_FOLDER.getKey(), Constants.CUSTOM_BKG_IMAGE_FOLDER);
        propertiesDAO.storeProperty(PropertyKey.CUSTOM_ICON_IMAGE_FOLDER.getKey(), Constants.CUSTOM_ICON_IMAGE_FOLDER);
        propertiesDAO.storeProperty(PropertyKey.CUSTOM_WEATHER_CODE_DEFINITIONS.getKey(),
                Constants.CUSTOM_WEATHER_CODE_DEFINITIONS);
    }
}
