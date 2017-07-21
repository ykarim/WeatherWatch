package weather;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemperatureTest {

	private static final double tempValue = 0.0;

	@Test
	public void getUnit_shouldReturnUnit() {
		Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempValue);
		assertEquals(Temperature.Unit.FAHRENHEIT, temp1.getUnit());
		
		Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempValue);
		assertEquals(Temperature.Unit.CELSIUS, temp2.getUnit());
	}
	
	@Test
	public void setUnit_shouldSetUnitAndConvertTemp(){
		Temperature temp1 = new Temperature(Temperature.Unit.CELSIUS, tempValue);
		temp1.setUnit(Temperature.Unit.FAHRENHEIT);
		assertEquals(Temperature.Unit.FAHRENHEIT, temp1.getUnit());
        assertEquals(tempValue * 1.8 + 32, temp1.getTemperatureValue(temp1.getUnit()), 0);

		Temperature temp2 = new Temperature(Temperature.Unit.FAHRENHEIT, tempValue);
		temp2.setUnit(Temperature.Unit.CELSIUS);
		assertEquals(Temperature.Unit.CELSIUS, temp2.getUnit());
        assertEquals((tempValue - 32) * 5 / 9, temp2.getTemperatureValue(Temperature.Unit.CELSIUS), 0);
    }

    @Test
    public void getTemperature_shouldGetTemp() {
	    final double tempVal = 50;
        Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempVal);
        assertEquals(tempVal, temp1.getTemperatureValue(Temperature.Unit.FAHRENHEIT), 0);
        assertEquals((tempVal - 32) * 5 / 9, temp1.getTemperatureValue(Temperature.Unit.CELSIUS), 0);

        Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempVal);
        assertEquals(tempVal, temp2.getTemperatureValue(Temperature.Unit.CELSIUS), 0);
        assertEquals(tempVal * 1.8 + 32, temp2.getTemperatureValue(Temperature.Unit.FAHRENHEIT), 0);
    }

    @Test
    public void setTemperature_shouldSetTemp() {
	    final double tempVal = 50;
	    Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempVal);
        temp1.setTemperatureValue(tempVal);
        assertEquals(tempVal, temp1.getTemperatureValue(Temperature.Unit.FAHRENHEIT), 0);

        Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempVal);
        temp2.setTemperatureValue(tempVal);
        assertEquals(tempVal, temp2.getTemperatureValue(Temperature.Unit.CELSIUS), 0);
    }
}
