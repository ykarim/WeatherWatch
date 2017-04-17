package test.java.weather;

import static org.junit.Assert.*;
import main.java.weather.Temperature;

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
	public void setUnit_shouldSetUnit(){
		Temperature temp1 = null;
		temp1.setUnit(Temperature.Unit.FAHRENHEIT);
		assertEquals(Temperature.Unit.FAHRENHEIT, temp1.getUnit());
	}
}
