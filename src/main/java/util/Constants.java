package util;

import weather.TempUnit;
import weather.Weather;

import java.util.ArrayList;

public class Constants {

    public static boolean FIRST_RUN = false;

    public static Boolean PREFER_CITY_NAME_OVER_ZIP = true;
    public static String PREFERRED_CITY_NAME;
    public static String PREFERRED_ZIP;
    public static String PREFERRED_COUNTRY_CODE = "us";

    public static TempUnit PREFERRED_UNIT = TempUnit.FAHRENHEIT;

    public static String CUSTOM_BKG_IMAGE_FOLDER;
    public static String CUSTOM_ICON_IMAGE_FOLDER;

    public static String CUSTOM_WEATHER_CODE_DEFINITIONS;

    public static int NUM_DECIMAL_PLACES = 2;

    public static int WEATHER_UPDATE_PERIOD_MINUTES = 10;
    public static int FORECAST_UPDATE_PERIOD_MINUTES = 10;

    public static String DATE_TIME_FORMAT = "hh:mm a";

    public static int NOTIFY_HOURS_AHEAD = 1;
    public static ArrayList<Weather.WeatherCondition> CONDITIONS_TO_TRACK = new ArrayList<>();

}
