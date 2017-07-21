package weather;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    private List<WeatherCondition> condition;
    private Temperature temperature;

    public Weather() {
        condition = new ArrayList<>();
        temperature = new Temperature(Temperature.Unit.CELSIUS, 0);
	}

    public Weather(List<WeatherCondition> condition, Temperature temperature) {
        this.condition = condition;
		this.temperature = temperature;
	}

    public List<WeatherCondition> getCondition() {
        return condition;
	}

    public void setCondition(List<WeatherCondition> condition) {
        this.condition = condition;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

    public enum WeatherCondition {
        THUNDERSTORM("Thunderstorm"),
        DRIZZLE("Drizzle"),
        RAIN("Rain"),
        SNOW("Snow"),
        ATMOSPHERE("Atmosphere"),
        CLEAR("Clear"),
        EXTREME("Extreme");

        private String name;

        WeatherCondition(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
