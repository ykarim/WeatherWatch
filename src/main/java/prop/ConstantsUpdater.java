package prop;

import util.Constants;

/**
 * Updates all constant variables
 */
public class ConstantsUpdater implements PropertyUpdater {

    @Override
    public void update(PropertiesDAO propertiesDAO) {
        Constants.FIRST_RUN = Boolean.valueOf(propertiesDAO.getProperty(PropertyKey.PREFERRED_UNIT.getKey()));
        Constants.PREFER_CITY_NAME_OVER_ZIP = Boolean.valueOf(propertiesDAO.
                getProperty(PropertyKey.PREFER_CITY_NAME_OVER_ZIP.getKey()));
        Constants.PREFERRED_CITY_NAME = propertiesDAO.getProperty(PropertyKey.PREFERRED_CITY_NAME.getKey());
        Constants.PREFERRED_ZIP = propertiesDAO.getProperty(PropertyKey.PREFERRED_ZIP.getKey());
        Constants.PREFERRED_COUNTRY_CODE = propertiesDAO.getProperty(PropertyKey.PREFERRED_COUNTRY_CODE.getKey());
        Constants.PREFERRED_CITY_NAME = propertiesDAO.getProperty(PropertyKey.PREFERRED_CITY_NAME.getKey());
    }
}
