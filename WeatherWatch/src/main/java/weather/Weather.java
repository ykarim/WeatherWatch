package weather;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    private List<WeatherCondition> condition;
    private Temperature temperature;
    private Image icon;

    public Weather() {
        condition = new ArrayList<>();
        temperature = new Temperature(Temperature.Unit.CELSIUS, 0);
	}

    public Weather(List<WeatherCondition> condition, Temperature temperature) {
        this.condition = condition;
        this.temperature = temperature;
    }

    public Weather(List<WeatherCondition> condition, Temperature temperature, Image icon) {
        this.condition = condition;
        this.temperature = temperature;
        this.icon = icon;
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

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
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
