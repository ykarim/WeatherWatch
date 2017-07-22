package view.weatherPage;

import dao.WeatherDAO;
import javafx.scene.image.Image;
import util.Constants;
import util.WeatherImgImport;
import weather.Temperature;
import weather.Weather;

import java.math.RoundingMode;

class WeatherPageController {

    private final int NUM_DECIMAL_PLACES = Constants.NUM_DECIMAL_PLACES;
    private WeatherDAO weatherDAO = new WeatherDAO();

    Image getWeatherIcon() {
        if (weatherDAO.getLatestWeather() != null) {
            return weatherDAO.getLatestWeather().getIcon();
        } else {
            return null;
        }
    }

    Image getWeatherBackground() {
        if (weatherDAO.getLatestWeather() != null) {
            return WeatherImgImport.getBackgroundImage(weatherDAO.getLatestWeather().getCondition().get(0));
        } else {
            return null;
        }
    }

    String getCurrentWeatherConditionText() {
        Weather latestWeather = weatherDAO.getLatestWeather();
        if (latestWeather != null) {
            StringBuilder builder = new StringBuilder();
            for (Weather.WeatherCondition condition : latestWeather.getCondition()) {
                builder.append(condition.getName());
                if (latestWeather.getCondition().size() > 1) {
                    builder.append(", ");
                }
            }
            return builder.toString();
        }
        return "???";
    }

    String getCurrentWeatherTemperatureText() {
        Weather latestWeather = weatherDAO.getLatestWeather();
        if (latestWeather != null) {
            switch (Constants.PREFERRED_UNIT) {
                case CELSIUS:
                    final String degreeCelsius = "\u2103";
                    return latestWeather.getTemperature().getTemperatureValue(Temperature.Unit.CELSIUS)
                            .setScale(NUM_DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString()
                            + degreeCelsius;
                case FAHRENHEIT:
                    final String degreeFahrenheit = "\u2109";
                    return latestWeather.getTemperature().getTemperatureValue(Temperature.Unit.FAHRENHEIT)
                            .setScale(NUM_DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString()
                            + degreeFahrenheit;
                case KELVIN:
                    final String degreeKelvin = "\u212A";
                    return latestWeather.getTemperature().getTemperatureValue(Temperature.Unit.KELVIN)
                            .setScale(NUM_DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString()
                            + degreeKelvin;
            }
        }
        return "???";
    }
}
