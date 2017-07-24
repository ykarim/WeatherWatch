package util;

import weather.Temperature;

public class Constants {

    public static boolean FIRST_RUN = false;

    public static Boolean PREFER_CITY_NAME_OVER_ZIP = true;
    public static String PREFERRED_CITY_NAME;
    public static String PREFERRED_ZIP;
    public static String PREFERRED_COUNTRY_CODE = "us";


    public static Temperature.Unit PREFERRED_UNIT = Temperature.Unit.FAHRENHEIT;

    public static String CUSTOM_BKG_IMAGE_FOLDER;
    public static String CUSTOM_ICON_IMAGE_FOLDER;

    public static String CUSTOM_WEATHER_CODE_DEFINITIONS;

    public static int NUM_DECIMAL_PLACES = 2;
}
