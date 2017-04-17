package weather;

public class Temperature {

	public enum Unit {
		CELSIUS,
		FAHRENHEIT;
	}
	
	private Unit unit;
	public double temperature;
	
	public Temperature(Unit unit, double temperature) {
		this.unit = unit;
		this.temperature = temperature;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		if (this.unit != null && unit != null) {
            this.temperature = convertTemp(temperature, unit);
            this.unit = unit;
        }
	}

	public double getTemperature(Unit desiredUnit) {
		return convertTemp(temperature, desiredUnit);
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	private double convertTemp(double tempVal, Unit desiredUnit) {
		if (desiredUnit != null && desiredUnit != unit) {
			if (desiredUnit == Unit.FAHRENHEIT) { 
				//Current unit must be CELSIUS
				tempVal = tempVal * 1.8 + 32;
			} else if (desiredUnit == Unit.CELSIUS) {
				//Current unit must be FAHRENHEIT
				tempVal = (tempVal - 32) * 5/9;
			}
		}
		return tempVal;
	}
}