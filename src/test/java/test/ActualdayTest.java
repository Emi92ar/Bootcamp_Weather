package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logic.Actualday;

public class ActualdayTest {

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
	public void Actualday() {
		Actualday actual = new Actualday("mon 11:00hs", "35");
		assertEquals("Error in the day", "mon 11:00hs", actual.getDate());
		assertEquals("Error in the temperature of the day", "35", actual.getTemperature());
	}
	
	@Test
	public void SettersAndGetterActualday() {
		Actualday actual = new Actualday("mon 11:00hs", "35");
		actual.setDate("fri 15:00hs");
		actual.setTemperature("15");
		assertEquals("Error in the day", "fri 15:00hs", actual.getDate());
		assertEquals("Error in the temperature of the day", "15", actual.getTemperature());
	}

	@Test
	public void Location() {
		Actualday.Location loc = new Actualday.Location("cordoba");
		assertEquals("Error in the city name", "cordoba", loc.getCity());
	}
	
	@Test
	public void LocationSettersAndGetters() {
		Actualday.Location loc = new Actualday.Location("cordoba");
		loc.setCity("Neuquen");
		loc.setCountry("Argentina");
		loc.setLatitude("-30");
		loc.setLongitude("55");
		loc.setRegion("Patagonia");
		assertEquals("Error in the city name", "Neuquen", loc.getCity());
		assertEquals("Error in the country name", "Argentina", loc.getCountry());
		assertEquals("Error in the latitude", "-30", loc.getLatitude());
		assertEquals("Error in the longitude", "55", loc.getLongitude());
		assertEquals("Error in the region", "Patagonia", loc.getRegion());
	}
	
	@Test
	public void Wind() {
		Actualday.Wind wind = new Actualday.Wind("40", "N", "20.3");
		assertEquals("Error in chill", "40", wind.getChill());
		assertEquals("Error in direction", "N", wind.getDirection());
		assertEquals("Error in speed", "20.3", wind.getSpeed());
	}
	
	@Test
	public void WindSettersAndGetters() {
		Actualday.Wind wind = new Actualday.Wind("40", "N", "20.3");
		wind.setChill("55");
		wind.setDirection("S");
		wind.setSpeed("95");
		assertEquals("Error in chill", "55", wind.getChill());
		assertEquals("Error in direction", "S", wind.getDirection());
		assertEquals("Error in speed", "95", wind.getSpeed());
	}
	
	@Test
	public void Atmosphere() {
		Actualday.Atmosphere at = new Actualday.Atmosphere("89", "1024", "600", "6.3");
		
		assertEquals("Error in humidity", "89", at.getHumidity());
		assertEquals("Error in pressure", "1024", at.getPressure());
		assertEquals("Error in rising", "600", at.getRising());
		assertEquals("Error in visibility", "6.3", at.getVisibility());
	}
	
	@Test
	public void AtmosphereSettersAndGetters() {
		Actualday.Atmosphere at = new Actualday.Atmosphere("89", "1024", "600", "6.3");
		at.setHumidity("99");
		at.setPressure("1020");
		at.setRising("630");
		at.setVisibility("1.3");
		assertEquals("Error in humidity", "99", at.getHumidity());
		assertEquals("Error in pressure", "1020", at.getPressure());
		assertEquals("Error in rising", "630", at.getRising());
		assertEquals("Error in visibility", "1.3", at.getVisibility());
	}
	
	@Test
	public void Astronomy() {
		Actualday.Astronomy as = new Actualday.Astronomy("6:00", "19:00");
		assertEquals("Error in sunrise", "6:00", as.getSunrise());
		assertEquals("Error in sunset", "19:00", as.getSunset());
		
	}
	
	@Test
	public void AstronomySettersAndGetters() {
		Actualday.Astronomy as = new Actualday.Astronomy("6:00", "19:00");
		as.setSunrise("7:00");
		as.setSunset("18:00");
		assertEquals("Error in sunrise", "7:00", as.getSunrise());
		assertEquals("Error in sunset", "18:00", as.getSunset());
	}
}