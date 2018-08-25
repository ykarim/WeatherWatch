package net.response.parser.owm;

import model.Location;
import model.Temperature;
import model.Weather;
import net.response.WeatherResponseParser;
import net.response.data.WeatherConditionsResponse;
import net.response.data.WeatherForecastResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Constants;

import java.io.IOException;
import java.util.ArrayList;

public class OWMWeatherResponseParser extends WeatherResponseParser {

    @Override
    public WeatherConditionsResponse parseConditionsResponse(HttpResponse response) throws IOException {
        if (response.getEntity() != null && response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            Weather extractedWeatherData = extractWeatherDataFromResponse(jsonObject);

            //Extract location data from response and store in weather object
            //Location data from forecast response is stored in name string and sys object
            Location conditionsLocation = new Location(jsonObject.getString("name"),
                    jsonObject.getJSONObject("sys").getString("country"));
            extractedWeatherData.setLocation(conditionsLocation);

            return new WeatherConditionsResponse(response.getStatusLine(), extractedWeatherData);
        }

        //TODO: Send error back (mb include in WeatherResponse)
        return null;
    }

    @Override
    public WeatherForecastResponse parseForecastResponse(HttpResponse response) throws IOException {
        if (response.getEntity() != null && response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray forecastArray = jsonResponse.getJSONArray("list");

            //Extract location data from response and store in weather objects
            //Location data from forecast response is stored in city object
            JSONObject cityObject = jsonResponse.getJSONObject("city");
            Location forecastLocation = new Location(cityObject.getString("name"), cityObject.getString("country"));

            ArrayList<Weather> forecasts = new ArrayList<>();
            for (int index = 0; index < forecastArray.length(); index++) {
                Weather extractedWeather = extractWeatherDataFromResponse(forecastArray.getJSONObject(index));
                extractedWeather.setLocation(forecastLocation);
                forecasts.add(extractedWeather);
            }

            return new WeatherForecastResponse(response.getStatusLine(), forecasts);
        }

        return null;
    }

    private Weather extractWeatherDataFromResponse(JSONObject weatherJSONObject) {
        JSONObject mainObj = weatherJSONObject.getJSONObject("main");
        JSONObject weatherObj = weatherJSONObject.getJSONArray("weather").getJSONObject(0);
        JSONObject sysObj = weatherJSONObject.getJSONObject("sys");

        double tempValue = mainObj.getDouble("temp");
        Temperature temperature = new Temperature(Constants.PREFERRED_UNIT, tempValue);
        String condition = weatherJSONObject.getJSONArray("weather").getJSONObject(0).getString("main");
        double humidityValue = mainObj.getDouble("humidity");
        double pressureValue = mainObj.getDouble("pressure");
        DateTime sunriseTime = new DateTime(sysObj.getLong("sunrise") * 1000L);
        DateTime sunsetTime = new DateTime(sysObj.getLong("sunset") * 1000L);
        DateTime lastUpdateTime = new DateTime(weatherJSONObject.getLong("dt") * 1000L);

        return new Weather(null, temperature, condition, humidityValue, pressureValue,
                sunriseTime, sunsetTime, lastUpdateTime);
    }
}
