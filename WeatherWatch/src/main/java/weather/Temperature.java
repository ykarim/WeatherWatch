package weather;

public class Temperature {

    private double temperatureValue;
    private Unit unit;

    public Temperature(Unit unit, double temperatureValue) {
        this.unit = unit;
        this.temperatureValue = temperatureValue;
    }

    public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		if (this.unit != null && unit != null) {
            this.temperatureValue = convertTemp(temperatureValue, unit);
            this.unit = unit;
        }
	}

    public double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public double getTemperatureValue(Unit desiredUnit) {
        return convertTemp(temperatureValue, desiredUnit);
    }

	private double convertTemp(double tempVal, Unit desiredUnit) {
		if (desiredUnit != null && desiredUnit != unit) {
            if (unit == Unit.CELSIUS && desiredUnit == Unit.FAHRENHEIT) {
                tempVal = tempVal * 1.8 + 32;
            } else if (unit == Unit.FAHRENHEIT && desiredUnit == Unit.CELSIUS) {
                tempVal = (tempVal - 32) * 5/9;
            } else if (unit == Unit.FAHRENHEIT && desiredUnit == Unit.KELVIN) {
                tempVal = ((tempVal - 32) / 1.8) + 273.15;
            } else if (unit == Unit.KELVIN && desiredUnit == Unit.FAHRENHEIT) {
                tempVal = 1.8 * (tempVal - 273.15) + 32;
            } else if (unit == Unit.CELSIUS && desiredUnit == Unit.KELVIN) {
                tempVal = tempVal + 273.15;
            } else if (unit == Unit.KELVIN && desiredUnit == Unit.CELSIUS) {
                tempVal = tempVal - 273.15;
            }
		}
		return tempVal;
	}

    public enum Unit {
        CELSIUS("CELSIUS"),
        FAHRENHEIT("FAHRENHEIT"),
        KELVIN("KELVIN");

        private String unitName;

        Unit(String unitName) {
            this.unitName = unitName;
        }

        public String getUnitName() {
            return unitName;
        }
    }
}