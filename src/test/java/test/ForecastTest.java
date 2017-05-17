package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Day;
import model.Forecast;

public class ForecastTest {

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
	public void SettersAndGetters() {
		Forecast forecast = new Forecast();
		Day day_of_the_list;
		Day day1 = new Day(30,20,"fri");
		forecast.Adding_days(day1);
		Day day2 = new Day(35,25,"sat");
		forecast.Adding_days(day2);
		Day day3 = new Day(-10,-15,"sun");
		forecast.Adding_days(day3);
		assertEquals("Amount day error", 3, forecast.AmountDay());
		day_of_the_list = forecast.getDay(1);
		assertEquals("Error in the day of the list asked temp max", 35, day_of_the_list.getTempMax(), 0.001);
		assertEquals("Error in the day of the list asked tem min", 25, day_of_the_list.getTempMin(), 0.001);
		assertEquals("Error in the day of the list asked thermal amplitude", 10, day_of_the_list.getThermal_amplitude(), 0.001);
		assertEquals("Error in the day of the list asked name day", "sat", day_of_the_list.getDay());

	}

}
