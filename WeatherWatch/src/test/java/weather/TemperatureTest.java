package weather;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureTest {

	private final double tempValue = 0.0;

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
        assertEquals(tempValue * 1.8 + 32, temp1.getTemperature(temp1.getUnit()), 0);

		Temperature temp2 = new Temperature(Temperature.Unit.FAHRENHEIT, tempValue);
		temp2.setUnit(Temperature.Unit.CELSIUS);
		assertEquals(Temperature.Unit.CELSIUS, temp2.getUnit());
		assertEquals((tempValue - 32) * 5/9, temp2.getTemperature(Temperature.Unit.CELSIUS), 0);
	}

    @Test
    public void getTemperature_shouldGetTemp() {
	    final double tempVal = 50;
        Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempVal);
        assertEquals(tempVal, temp1.getTemperature(Temperature.Unit.FAHRENHEIT), 0);
        assertEquals((tempVal - 32) * 5/9, temp1.getTemperature(Temperature.Unit.CELSIUS), 0);

        Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempVal);
        assertEquals(tempVal, temp2.getTemperature(Temperature.Unit.CELSIUS),0);
        assertEquals(tempVal * 1.8 + 32, temp2.getTemperature(Temperature.Unit.FAHRENHEIT), 0);
    }

    @Test
    public void setTemperature_shouldSetTemp() {
	    final double tempVal = 50;
	    Temperature temp1 = new Temperature(Temperature.Unit.FAHRENHEIT, tempVal);
	    temp1.setTemperature(tempVal);
	    assertEquals(tempVal, temp1.getTemperature(Temperature.Unit.FAHRENHEIT), 0);

        Temperature temp2 = new Temperature(Temperature.Unit.CELSIUS, tempVal);
        temp2.setTemperature(tempVal);
        assertEquals(tempVal, temp2.getTemperature(Temperature.Unit.CELSIUS), 0);
    }
}
