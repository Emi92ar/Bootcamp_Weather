package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import daos.ActualdayDAOImp;
//import ActualdayDAO;
import model.Actualday;
import persistence.DataBaseH2;

public class DataBaseH2Test {
	
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
//No funciona mas porque no existe el getinstance
//	@Test
//	public void Singleton() {
//		DataBaseH2 db = new DataBaseH2();
//		DataBaseH2 db1 = new DataBaseH2();
//		//Verify if conecction is the same between these objects
//		assertEquals(db.getCon().toString(),db1.getCon().toString());
//	}
	
	@Test
	public void WritingAndReadingBd_Location() {
		DataBaseH2 db = new DataBaseH2();
		Actualday.Location loc = new Actualday.Location("Zapala");
		loc.setCountry("Argentina");
		loc.setLatitude("-66");
		loc.setLongitude("66");
		loc.setRegion("Patagonia");
//		ActualdayDAO actuall = new ActualdayDAOImp();
		
//		Actualday actual = new Actualday("22 tues", "55");
//		Actualday.Wind wind = new Actualday.Wind("20", "S", "55.2");		
//		Actualday.Atmosphere atmosphere = new Actualday.Atmosphere("89", "1023", "2016", "10,km");
//		Actualday.Astronomy astronomy = new Actualday.Astronomy("5:59", "20:50");
		
		//Using builder
		Actualday actual = new Actualday.ActualdayBuilder().date("22 tues").temperature("55").build();
		Actualday.Wind wind = new Actualday.WindBuilder().chill("20").direction("S").speed("55.2").build();
		Actualday.Atmosphere atmosphere = new Actualday.AtmosphereBuilder().humidity("89").pressure("1023").rising("2016").visibility("10,km").build();
		Actualday.Astronomy astronomy = new Actualday.AstronomyBuilder().sunrise("5:59").sunset("20:50").build();
		
//		actuall.Insert(actual, astronomy, atmosphere, loc, wind);
//		db.SetInfoLocation(loc);
		try{
			Statement st = (Statement) db.getCon().createStatement();
			ResultSet rs = st.executeQuery("SELECT * from location "
										+ "inner join country on country_id = id_country;");
			while(rs.next()){
				assertEquals("1", rs.getObject("Id_location").toString());
				assertEquals("1",  rs.getObject("country_id").toString());
				assertEquals("Zapala", rs.getObject("city_name").toString());
				assertEquals("66", rs.getObject("longitude").toString());
				assertEquals("-66", rs.getObject("latitude").toString());
				assertEquals("1", rs.getObject("id_country").toString());
				assertEquals("Argentina", rs.getObject("country_name").toString());
			}
			rs.close();
		}
		catch (SQLException ex){
			System.out.println("SQL Exception ");
		}
		catch (Exception e){
			System.out.println("***********Falleeeee*****************");
		}
		
	}
}
