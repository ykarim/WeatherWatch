package network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import weather.Temperature;
import weather.Weather;

/**
 * Formats obtained weather data into Weather obj.
 */
public class DataFormat {

    private static WeatherCodes weatherCodes = new WeatherCodes();

    public static Weather convertJSONToWeatherObj(String jsonString) {
        Weather weatherObj = new Weather();

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        weatherObj.setTemperature(new Temperature(Temperature.Unit.KELVIN,
                Double.parseDouble(json.getAsJsonObject("main").get("temp").toString())));

        for (int index = 0; index < json.getAsJsonArray("weather").size(); index++) {
            JsonObject object = json.getAsJsonArray("weather").get(index).getAsJsonObject();
            weatherObj.getCondition().add(
                    weatherCodes.getConditionFromCode(Integer.parseInt(object.get("id").toString())));
        }
        return weatherObj;
    }
}
