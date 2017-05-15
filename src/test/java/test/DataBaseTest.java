package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logic.Actualday;
import logic.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.DeleteDbFiles;

public class DataBaseTest {
	private String _driver = "org.h2.Driver";
	private String _url = "jdbc:h2:~/weatherr";
	private String _user = "root";
	private String _pwd = "root";
	private String _dbName= "weatherr";
	private Connection conn = null;
	private DataBase db;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		DeleteDbFiles.execute("~", _dbName, true);
		db = DataBase.getInstance(_driver,_url, _user, _pwd);
	}

	@After
	public void tearDown() throws Exception {
		db.ResetClass();
	}

	@Test
	public void CreateAndConnectToDataBase() {
		DataBase db1 = DataBase.getInstance(_driver,_url, _user, _pwd);
		assertEquals(db,db1);
	}
	
	@Test
	public void WritingAndReadingBd_Location() {
		Actualday.Location loc = new Actualday.Location("Zapala");
		loc.setCountry("Argentina");
		loc.setLatitude("-66");
		loc.setLongitude("66");
		loc.setRegion("Patagonia");
		db.SetInfoLocation(loc);
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			Statement st = (Statement) conn.createStatement();
			
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
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("SQL Exception ");
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falleeeee*****************");
		}
		
	}
	
}
