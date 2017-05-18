package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Day;

public class DayTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void DayParamError() {
		Day day = new Day(20,30,"wed");
		assertEquals("Temp Max is lower than temp min",99999999, day.getTempMax(), 0.001);
		assertEquals("Temp Max is lower than temp min",99999999, day.getTempMin(), 0.001);
	}
	
	@Test
	public void DayParamRight() {
		Day day = new Day(30,10,"wed");
		assertEquals("Max temperature error",30, day.getTempMax(), 0.001);
		assertEquals("Min temperature error",10, day.getTempMin(), 0.001);
		assertEquals("Thermal amplitude err",20, day.getThermalAmplitude(), 0.001);
	}
	
	@Test
	public void DaySettersFuntion() {
		Day day = new Day(30,20,"wed");
		day.setDay("fri");
		day.setTempMax(10);
		day.setTempMin(-10);
		assertEquals("fri", day.getDay());
		assertEquals("Max temperature error", 10, day.getTempMax(), 0.001);
		assertEquals("Min temperature error", -10, day.getTempMin(), 0.001);
		assertEquals("Thermal amplitude err in negative case", 20, day.getThermalAmplitude(), 0.001);
	}
}
