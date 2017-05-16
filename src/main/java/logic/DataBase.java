package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.Connection;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

import org.h2.tools.DeleteDbFiles;

public class DataBase implements DataBaseDao {
	

//	private String _user = "root"; 
//	private String _pwd = "root";
	private String _dbname;
//	static String _url = "jdbc:mysql://localhost:3306/";
	private Connection conn = null;
	private String _user;
	private String _pwd;
	private String _url;
	private String _driver;
	
	private static DataBase instance = null;

	private DataBase(String _driver, String _url, String _user, String _pwd){
		try
		{	
			// In the next line read my script to create the db and their tables
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/dbScript"));
			//With the following lines I find the db'name provided by the script 
		    String line = br.readLine();
		    String[] temp = line.split("\\s+");
		    _dbname = temp[5];
		    try {
				Class.forName(_driver);
				conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
				if(conn != null){
				  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
				 }
				Statement st = (Statement) conn.createStatement();
				if(_driver == "org.h2.Driver"){
//					System.out.println("This line it not execute because it's h2 and I don't need to execute the first line to create database" +line);
					line = br.readLine();
//					System.out.println("This line it now excute because it's h2" +line);
					line = br.readLine();
				}
				//I execute all the script's lines
			    while (line != null) {
//			    	System.out.println(line);
			    	st.executeUpdate(line);
			    	line = br.readLine();
			    }
			    st.close();
			    conn.close();
			    this._url = _url;
			    this._user = _user;
			    this._pwd = _pwd;
			    this._driver = _driver;
			} 
			catch (SQLException ex){
				System.out.println("There was a problem with the database when attempting to connect " + _url);
			}
			catch (ClassNotFoundException ex){
				System.out.println(ex);
			}
			
			finally {
				conn.close();
			}
		   br.close();
		}
		catch (FileNotFoundException ex){
			System.out.println("The file does not exists");
			}
		
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
	
	public static DataBase getInstance(String driver, String url, String user, String pwd){
		if(instance == null){
			instance = new DataBase(driver, url, user, pwd);
		}
		return instance;
	}
	
	
	public void SetInfoLocation(Actualday.Location loc){
		try{
			Class.forName(_driver);	
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO country (country_name) "
							+ "SELECT * FROM (SELECT '"+loc.getCountry()+"') AS tmp "
							+ "WHERE NOT EXISTS ("
							+ "SELECT country_name from country "
							+ "where country_name = '"+loc.getCountry()+"'"
							+ ")limit 1;");		
			st.executeUpdate("INSERT INTO location(city_name,longitude,latitude,country_id)"
							+ "SELECT '"+loc.getCity()+"','"+loc.getLongitude()+"','"+loc.getLatitude()+"',id_country from country "
							+ "where not exists (" 
							+ "SELECT city_name from location "
							+ "where city_name = '"+loc.getCity()+"')"
							+ "AND country_name = '"+loc.getCountry()+"';");
			System.out.println("SetInfoLocation Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
	
	
	public void SetInfoActualday(Actualday actual){
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO actualday(fulldate, temperature)values ('"+actual.getDate()+"','"+actual.getTemperature()+"');");
			System.out.println("SetInfoActualDay Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}

	
	public void SetInfoWind(Actualday.Wind wind){
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO wind (`detaildate`, `chill`, `direction`, `speed`)"
								+ "SELECT MAX(id_actual),'"+wind.getChill()+"','"+wind.getDirection()+"','"+wind.getSpeed()+"'"
								+ "from actualday;");
			System.out.println("SetInfoWind Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}

	public void SetInfoAtmosphere(Actualday.Atmosphere at){
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO atmosphere (`detaildate`, `humidity`, `pressure`, `rising`, `visibility`)"
							+ "SELECT MAX(id_actual),'"+at.getHumidity()+"','"+at.getPressure()+"','"+at.getRising()+"','"+at.getVisibility()+"'"
							+ "from actualday;");
			System.out.println("SetInfoAtmosphere Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
	
	public void SetInfoAstronomy(Actualday.Astronomy as){
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO astronomy(`detaildate`, `sunrise`, `sunset`) "
							+ "SELECT MAX(id_actual),'"+ as.getSunrise()+"','"+as.getSunset()+"' "
							+ "from actualday;");
			System.out.println("SetInfoAstronomy Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
	
	public void SetInfoDay(Day day){
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO days(`day_name`, `forecast_id`, `tempmax`, `tempmin`) "
							+ "SELECT '"+day.getDay()+"', MAX(id_actual),'"+ day.getTempMax()+"','"+day.getTempMin()+"' "
							+ "from actualday;");
			System.out.println("SetInfoDay Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
	
	public void SetInfoActual_Location(String city){
		try{
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			st.executeUpdate("INSERT INTO actualday_location (`id_actual`, `id_location`)"
								+"SELECT MAX(id_actual), id_location from actualday"
								+"inner join location"
								+ "where city_name = '"+city+"';");
			System.out.println("SetInfoActual_Location Satisfactoriamente");
			st.close();
			conn.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo*****************");
		}
	}	
	
	/**
	 * @param forecast_num It the number of the Forecast query number,That is, 
	 * if you ask for 5 forecasts at different times, the number you choose will be the one that will inform you
	 */
	public void ReadBdForecast(int number){
		try{
			System.out.println("Reading the database-------------------------------------------");
			Class.forName(_driver);
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			if(_driver != "org.h2.Driver"){
				st.executeUpdate("use "+_dbname+";");
			}
			ResultSet rs = st.executeQuery("SELECT day_name, tempmax, tempmin from days "
					+ "where forecast_id='"+number+"'");
			System.out.println("\n\n_____________________________Information privided by the DataBase_____________________________");
			while(rs.next()){
				System.out.println("Forecast day: " + rs.getObject("day_name")+"   ||Temp Max= "+rs.getObject("tempmax")+"   ||Temp Min= "+rs.getObject("tempmax"));
			}
			System.out.println("\n______________________________________________________________________________________________");

			rs.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo*****************");
		}
	}
		/**
		 *  This method give a list of the places Consulted
		 */
				
		public void ReadBdPlacesConsulted(){
			try{
				System.out.println("Reading the database-------------------------------------------");
				Class.forName(_driver);
				conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
				if(conn != null){
				  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
				 }
				Statement st = (Statement) conn.createStatement();
				if(_driver != "org.h2.Driver"){
					st.executeUpdate("use "+_dbname+";");
				}
				ResultSet rs = st.executeQuery("SELECT * from location "
											+ "inner join country on country_id = id_country;");
				System.out.println("\n\n_____________________________Information privided by the DataBase_____________________________");
				while(rs.next()){
					System.out.println("Id_location= " + rs.getObject("Id_location")+"   ||country_id= "+rs.getObject("country_id")+"   ||city_name= "+rs.getObject("city_name")+""
							+ "   ||longitude= "+rs.getObject("longitude")+"   ||latitude= "+rs.getObject("latitude")+"   ||id_country= "+rs.getObject("id_country")+"   "
							+ "||country_name= "+rs.getObject("country_name"));
				}
				System.out.println("\n______________________________________________________________________________________________");
				rs.close();
			}
			catch (SQLException ex){
				System.out.println("There was a problem with the database when attempting to connect " + _url+_dbname);
			}
			catch (ClassNotFoundException ex){
				System.out.println(ex);
			}
			catch (Exception e){
				System.out.println("***********Falloooooooooooooooooooooo*****************");
			}
	}		
		/*
		 * This method is only used to do other test and reset instance
		 */
		public void ResetClass(){
			instance = null;
		}
}
