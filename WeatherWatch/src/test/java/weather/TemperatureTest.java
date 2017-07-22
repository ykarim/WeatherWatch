package weather;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class TemperatureTest {

    private static final BigDecimal tempValue = new BigDecimal(0.0);

	@Test
	public void getUnit_shouldReturnUnit() {
		Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempValue);
		assertEquals(Temperature.Unit.FAHRENHEIT, temp1.getUnit());
		
		Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempValue);
		assertEquals(Temperature.Unit.CELSIUS, temp2.getUnit());

        Temperature temp3 = new Temperature(Temperature.Unit.KELVIN, tempValue);
        assertEquals(Temperature.Unit.KELVIN, temp3.getUnit());
    }
	
	@Test
	public void setUnit_shouldSetUnitAndConvertTemp(){
		Temperature temp1 = new Temperature(Temperature.Unit.CELSIUS, tempValue);
		temp1.setUnit(Temperature.Unit.FAHRENHEIT);
		assertEquals(Temperature.Unit.FAHRENHEIT, temp1.getUnit());
        assertEquals(tempValue.multiply(new BigDecimal(1.8)).add(new BigDecimal(32)),
                temp1.getTemperatureValue(temp1.getUnit()));
        temp1.setUnit(Temperature.Unit.KELVIN);
        assertEquals(Temperature.Unit.KELVIN, temp1.getUnit());
        assertEquals(tempValue.add(new BigDecimal(273.15)), temp1.getTemperatureValue(Temperature.Unit.KELVIN));

		Temperature temp2 = new Temperature(Temperature.Unit.FAHRENHEIT, tempValue);
		temp2.setUnit(Temperature.Unit.CELSIUS);
		assertEquals(Temperature.Unit.CELSIUS, temp2.getUnit());
        assertEquals((tempValue.subtract(new BigDecimal(32)).multiply(new BigDecimal((double) 5 / 9))),
                temp2.getTemperatureValue(Temperature.Unit.CELSIUS));
        temp2.setUnit(Temperature.Unit.KELVIN);
        assertEquals(Temperature.Unit.KELVIN, temp2.getUnit());
        assertEquals(tempValue.subtract(new BigDecimal(32))
                .multiply(new BigDecimal((double) 5 / 9))
                .add(new BigDecimal(273.15)), temp2.getTemperatureValue(Temperature.Unit.KELVIN));

        Temperature temp3 = new Temperature(Temperature.Unit.KELVIN, tempValue);
        temp3.setUnit(Temperature.Unit.FAHRENHEIT);
        assertEquals(Temperature.Unit.FAHRENHEIT, temp3.getUnit());
        assertEquals(tempValue.subtract(new BigDecimal(273.15))
                .multiply(new BigDecimal(1.8))
                .add(new BigDecimal(32)), temp3.getTemperatureValue(Temperature.Unit.FAHRENHEIT));
        temp3.setUnit(Temperature.Unit.CELSIUS);
        assertEquals(Temperature.Unit.CELSIUS, temp3.getUnit());
        assertEquals(tempValue.subtract(new BigDecimal(273.15)).setScale(2, RoundingMode.HALF_UP),
                temp3.getTemperatureValue(Temperature.Unit.CELSIUS).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void getTemperature_shouldGetTemp() {
        final BigDecimal tempVal = new BigDecimal(50);
        Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempVal);
        assertEquals(tempVal, temp1.getTemperatureValue(Temperature.Unit.FAHRENHEIT));

        Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempVal);
        assertEquals(tempVal, temp2.getTemperatureValue(Temperature.Unit.CELSIUS));

        Temperature temp3 = new Temperature(Temperature.Unit.KELVIN, tempVal);
        assertEquals(tempVal, temp3.getTemperatureValue(Temperature.Unit.KELVIN));
    }

    @Test
    public void setTemperature_shouldSetTemp() {
        final BigDecimal tempVal = new BigDecimal(50);
        Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempValue);
        temp1.setTemperatureValue(tempVal);
        assertEquals(tempVal, temp1.getTemperatureValue(Temperature.Unit.FAHRENHEIT));

        Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempValue);
        temp2.setTemperatureValue(tempVal);
        assertEquals(tempVal, temp2.getTemperatureValue(Temperature.Unit.CELSIUS));

        Temperature temp3 = new Temperature(Temperature.Unit.KELVIN, tempValue);
        temp3.setTemperatureValue(tempVal);
        assertEquals(tempVal, temp3.getTemperatureValue(Temperature.Unit.KELVIN));
    }
}
