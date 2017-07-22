package weather;

import java.math.BigDecimal;

public class Temperature {

    private BigDecimal temperatureValue;
    private Unit unit;

    public Temperature(Unit unit, double temperatureValue) {
        this.unit = unit;
        this.temperatureValue = new BigDecimal(temperatureValue);
    }

    public Temperature(Unit unit, BigDecimal temperatureValue) {
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

    public BigDecimal getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(BigDecimal temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public BigDecimal getTemperatureValue(Unit desiredUnit) {
        return convertTemp(temperatureValue, desiredUnit);
    }

    private BigDecimal convertTemp(BigDecimal tempVal, Unit desiredUnit) {
        if (desiredUnit != null && desiredUnit != unit) {
            if (unit == Unit.CELSIUS && desiredUnit == Unit.FAHRENHEIT) {
                tempVal = tempVal.multiply(new BigDecimal(1.8))
                        .add(new BigDecimal(32));
            } else if (unit == Unit.FAHRENHEIT && desiredUnit == Unit.CELSIUS) {
                tempVal = tempVal.subtract(new BigDecimal(32))
                        .multiply(new BigDecimal((double) 5 / 9));
            } else if (unit == Unit.FAHRENHEIT && desiredUnit == Unit.KELVIN) {
                tempVal = tempVal.subtract(new BigDecimal(32))
                        .divide(new BigDecimal(1.8))
                        .add(new BigDecimal(273.15));
            } else if (unit == Unit.KELVIN && desiredUnit == Unit.FAHRENHEIT) {
                tempVal = tempVal.subtract(new BigDecimal(273.15))
                        .multiply(new BigDecimal(1.8))
                        .add(new BigDecimal(32));
            } else if (unit == Unit.CELSIUS && desiredUnit == Unit.KELVIN) {
                tempVal = tempVal.add(new BigDecimal(273.15));
            } else if (unit == Unit.KELVIN && desiredUnit == Unit.CELSIUS) {
                tempVal = tempVal.subtract(new BigDecimal(273.15));
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