package network;

import weather.Weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherCodes {

    private static Map<Integer, Weather.WeatherCondition> codes = new HashMap<>();

    public WeatherCodes() {
        codes.put(2, Weather.WeatherCondition.THUNDERSTORM);
        codes.put(3, Weather.WeatherCondition.DRIZZLE);
        codes.put(5, Weather.WeatherCondition.RAIN);
        codes.put(6, Weather.WeatherCondition.SNOW);
        codes.put(7, Weather.WeatherCondition.ATMOSPHERE);
        codes.put(8, Weather.WeatherCondition.CLEAR);
        codes.put(9, Weather.WeatherCondition.EXTREME);
    }

    public static Map<Integer, Weather.WeatherCondition> getCodes() {
        return codes;
    }

    public Weather.WeatherCondition getConditionFromCode(int code) {
        return codes.get(Integer.parseInt(Integer.toString(code).substring(0, 1)));
    }

    public Integer getCodeFromCondition(Weather.WeatherCondition condition) {
        for (Map.Entry<Integer, Weather.WeatherCondition> entry : codes.entrySet()) {
            if (entry.getValue() == condition) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
