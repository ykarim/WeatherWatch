package view.weatherPage;

import dao.WeatherDAO;
import javafx.scene.image.Image;
import util.Constants;
import util.WeatherImgImport;
import weather.Weather;

public class WeatherPageController {

    private WeatherDAO weatherDAO = new WeatherDAO();

    public Image getWeatherIcon() {
        if (weatherDAO.getLatestWeather() != null) {
            return WeatherImgImport.getIconImage(weatherDAO.getLatestWeather().getCondition().get(0));
        } else {
            return null;
        }
    }

    public Image getWeatherBackground() {
        if (weatherDAO.getLatestWeather() != null) {
            return WeatherImgImport.getBackgroundImage(weatherDAO.getLatestWeather().getCondition().get(0));
        } else {
            return null;
        }
    }

    public String getCurrentWeatherConditionText() {
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

    public String getCurrentWeatherTemperatureText() {
        Weather latestWeather = weatherDAO.getLatestWeather();
        if (latestWeather != null) {
            switch (Constants.PREFERRED_UNIT) {
                case CELSIUS:
                    final String degreeCelsius = "\u2103";
                    return Double.toString(latestWeather.getTemperature().getTemperatureValue()) + degreeCelsius;
                case FAHRENHEIT:
                    final String degreeFahrenheit = "\u2109";
                    return Double.toString(latestWeather.getTemperature().getTemperatureValue()) + degreeFahrenheit;
                case KELVIN:
                    final String degreeKelvin = "\u212A";
                    return Double.toString(latestWeather.getTemperature().getTemperatureValue()) + degreeKelvin;
            }
        }
        return "???";
    }
}
