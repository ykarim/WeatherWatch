package net.response.parser.owm;

import net.response.WeatherResponseParser;
import net.response.data.WeatherConditionsResponse;
import net.response.data.WeatherForecastResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Constants;
import weather.Temperature;
import weather.Weather;

import java.io.IOException;
import java.util.ArrayList;

public class OWMWeatherResponseParser extends WeatherResponseParser {

    @Override
    public WeatherConditionsResponse parseConditionsResponse(HttpResponse response) throws IOException {
        if (response.getEntity() != null && response.getStatusLine().getStatusCode() == 200) {
            return new WeatherConditionsResponse(response.getStatusLine(),
                    extractWeatherDataFromResponse(new JSONObject(EntityUtils.toString(response.getEntity()))));
        }

        //TODO: Send error back (mb include in WeatherResponse)
        return null;
    }

    @Override
    public WeatherForecastResponse parseForecastResponse(HttpResponse response) throws IOException {
        if (response.getEntity() != null && response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray forecastArray = jsonResponse.getJSONArray("list");

            ArrayList<Weather> forecasts = new ArrayList<>();
            for (int index = 0; index < forecastArray.length(); index++) {
                forecasts.add(extractWeatherDataFromResponse(forecastArray.getJSONObject(index)));
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
