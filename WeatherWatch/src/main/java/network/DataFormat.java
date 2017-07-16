package network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import weather.Forecast;
import weather.Temperature;
import weather.Weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<Forecast> convertJSONToForecastObjs(String jsonString) {
        List<Forecast> forecasts = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        for (int index = 0; index < json.getAsJsonArray("list").size(); index++) {
            JsonObject forecastsArray = json.getAsJsonArray("list").get(index).getAsJsonObject();
            List<Weather.WeatherCondition> conditions = new ArrayList<>();
            for (int conditionsIndex = 0; conditionsIndex < forecastsArray.getAsJsonArray("weather").size(); conditionsIndex++) {
                JsonObject weatherConditionArray = forecastsArray.getAsJsonArray("weather").get(conditionsIndex).getAsJsonObject();
                conditions.add(weatherCodes.getConditionFromCode(Integer.parseInt(weatherConditionArray.get("id").toString())));
            }
            forecasts.add(new Forecast(
                    new Weather(conditions,
                            new Temperature(Temperature.Unit.KELVIN,
                                    Double.parseDouble(forecastsArray.get("main").getAsJsonObject().get("temp").toString()))),
                    new Date(forecastsArray.get("dt").getAsLong())));
        }

        return forecasts;
    }
}
