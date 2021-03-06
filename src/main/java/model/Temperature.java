package model;

import java.math.BigDecimal;

public class Temperature {

    private BigDecimal temperatureValue;
    private TempUnit unit;

    public Temperature(TempUnit unit, double temperatureValue) {
        this.unit = unit;
        this.temperatureValue = new BigDecimal(temperatureValue);
    }

    public Temperature(TempUnit unit, BigDecimal temperatureValue) {
        this.unit = unit;
        this.temperatureValue = temperatureValue;
    }

    public TempUnit getUnit() {
        return unit;
    }

    public void setUnit(TempUnit unit) {
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

    //TODO: redundant if temp set using const.pref_unit and used again to get value?
    public BigDecimal getTemperatureValue(TempUnit desiredUnit) {
        return convertTemp(temperatureValue, desiredUnit);
    }

    public String getTemperatureString(TempUnit unit, int scale) {
        String tempValString = getTemperatureValue(unit).setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();

        //Append degree sign depending on unit requested
        final String degreeSign = "\u00B0";
        switch (unit) {
            case FAHRENHEIT:
            case CELSIUS:
                tempValString += degreeSign;
                break;
            case KELVIN:
                break;
        }

        return tempValString;
    }

    private BigDecimal convertTemp(BigDecimal tempVal, TempUnit desiredUnit) {
        if (desiredUnit != null && desiredUnit != unit) {
            if (unit == TempUnit.CELSIUS && desiredUnit == TempUnit.FAHRENHEIT) {
                tempVal = tempVal.multiply(new BigDecimal(1.8))
                        .add(new BigDecimal(32));
            } else if (unit == TempUnit.FAHRENHEIT && desiredUnit == TempUnit.CELSIUS) {
                tempVal = tempVal.subtract(new BigDecimal(32))
                        .multiply(new BigDecimal((double) 5 / 9));
            } else if (unit == TempUnit.FAHRENHEIT && desiredUnit == TempUnit.KELVIN) {
                tempVal = tempVal.subtract(new BigDecimal(32))
                        .divide(new BigDecimal(1.8))
                        .add(new BigDecimal(273.15));
            } else if (unit == TempUnit.KELVIN && desiredUnit == TempUnit.FAHRENHEIT) {
                tempVal = tempVal.subtract(new BigDecimal(273.15))
                        .multiply(new BigDecimal(1.8))
                        .add(new BigDecimal(32));
            } else if (unit == TempUnit.CELSIUS && desiredUnit == TempUnit.KELVIN) {
                tempVal = tempVal.add(new BigDecimal(273.15));
            } else if (unit == TempUnit.KELVIN && desiredUnit == TempUnit.CELSIUS) {
                tempVal = tempVal.subtract(new BigDecimal(273.15));
            }
        }
        return tempVal;
    }

    @Override
    public String toString() {
        return getTemperatureString(unit, 0);
    }
}