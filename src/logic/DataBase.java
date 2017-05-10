package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DataBase {
	

	private String _user = "root"; 
	private String _pwd = "root";
	private String _dbname;
	static String _url = "jdbc:mysql://localhost:3306/";
	private Connection conn = null;
	
	private static DataBase instance = null;

	private DataBase(){
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			BufferedReader br = new BufferedReader(new FileReader("dbScript"));
			try {
			    String line = br.readLine();
			    String[] temp = line.split("\\s+");
			    _dbname = temp[5];
			    while (line != null) {
			    	System.out.println(line);
			    	st.executeUpdate(line);
			    	line = br.readLine();
			    }
			} 
			catch (FileNotFoundException ex){
				System.out.println("The file does not exists");
				}
			finally {
			    br.close();
			}
		   st.close();
		   conn.close();
	
		}catch (SQLException ex){
				System.out.println("There was a problem with the database when attempting to connect " + _url);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
	
	public static DataBase getInstance(){
		if(instance == null){
			instance = new DataBase();
		}
		return instance;
	}
	
	public void SetInfoLocation(Actualday.Location loc){
		try{
			Class.forName("com.mysql.jdbc.Driver");	
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
			st.executeUpdate("INSERT INTO atmosphere (`detaildate`, `humidity`, `pressure`, `rising`, `visibility`)"
							+ "SELECT MAX(id_actual),'"+at.getHumedity()+"','"+at.getPressure()+"','"+at.getRising()+"','"+at.getVisibility()+"'"
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(conn != null){
			  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
			 }
			Statement st = (Statement) conn.createStatement();
			st.executeUpdate("use "+_dbname+";");
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
				Class.forName("com.mysql.jdbc.Driver");
				conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
				if(conn != null){
				  System.out.println("Connected to the database " + _url+_dbname + " correctly.");
				 }
				Statement st = (Statement) conn.createStatement();
				st.executeUpdate("use "+_dbname+";");
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
}
