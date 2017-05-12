package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logic.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.h2.tools.DeleteDbFiles;

public class DataBaseTest {

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
	public void OnlyOneInstance() {
		DataBase db = DataBase.getInstance();
		DataBase db1 = DataBase.getInstance();
		assertEquals(db,db1);
	}
	
	@Test
	public void Conecction() throws Exception {
		/*
		 * 	private String _user = "root"; 
		 *	private String _pwd = "root";
		 *	private String _dbname;
		 *	static String _url = "jdbc:mysql://localhost:3306/";
		 * 
		 */
	
		DeleteDbFiles.execute("~", "test", true);
	    Class.forName("org.h2.Driver");
	    
	    try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test");

	    	Statement stat = conn.createStatement()) {
	        stat.execute("create table test(id int primary key, name varchar(255))");
	        stat.execute("insert into test values(1, 'Hello')");
	        try (ResultSet rs = stat.executeQuery("select * from test")) {
	            while (rs.next()) {
	                assertEquals("Error writing or reading db", "Hello",rs.getString("name"));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
