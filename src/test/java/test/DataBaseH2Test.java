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

	@Test
	public void Singleton() {
		DataBaseH2 db = new DataBaseH2();
		DataBaseH2 db1 = new DataBaseH2();
		//Verify if conecction is the same between these objects
		assertEquals(db.getCon().toString(),db1.getCon().toString());
	}
	
	@Test
	public void WritingAndReadingBd_Location() {
		DataBaseH2 db = new DataBaseH2();
		Actualday.Location loc = new Actualday.Location("Zapala");
		loc.setCountry("Argentina");
		loc.setLatitude("-66");
		loc.setLongitude("66");
		loc.setRegion("Patagonia");
		db.SetInfoLocation(loc);
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
