package main.java.weather;

public class Temperature {

	public enum Unit {
		CELSIUS,
		FAHRENHEIT;
	}
	
	private Unit unit;
	private double temperature;
	
	public Temperature(Unit unit, double temperature) {
		this.unit = unit;
		this.temperature = temperature;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		if (this.unit != null && unit != null) {
			this.unit = unit;
		}
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public void convertTemp(Unit desiredUnit) {
		if (desiredUnit != null && desiredUnit != unit) {
			if (desiredUnit == Unit.FAHRENHEIT) { 
				//Current unit must be CELSIUS
				temperature = temperature * 1.8 + 32;
			} else if (desiredUnit == Unit.CELSIUS) {
				//Current unit must be FAHRENHEIT
				temperature = (temperature - 32) * (5/9); 
			}
		}
	}
}
