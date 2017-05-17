package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import connections.DbConnection;
import model.Actualday;
import model.Day;
import model.Actualday.Astronomy;
import model.Actualday.Atmosphere;
import model.Actualday.Location;
import model.Actualday.Wind;

public class DataBaseMySQL implements DataBaseDAO {
	
	
	private Connection con;
	private BufferedReader br;
	private String _dbName;
	/*
	 * Create the Data Base if not exists and the connection with the DB
	 */
	public DataBaseMySQL(){
		try{
			//The next line read the Script
			br = new BufferedReader(new FileReader("src/main/resources/dbScript"));
		    String line = br.readLine();
		    String[] temp = line.split("\\s+");
		    _dbName = temp[5];
		    //In the next line I will connect to the db
		    con = (Connection) DbConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306", "root", "root").getConnection();
		    Statement st = (Statement) con.createStatement();
		    while (line != null) {
		    	System.out.println(line);
		    	st.executeUpdate(line);
		    	line = br.readLine();
		    }
		    st.close();
		}
		catch(FileNotFoundException ex){
			
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect ");
		}
		catch (Exception ex){
			System.out.println(ex);
		}
	}
	
	public void SetInfoLocation(Actualday.Location loc){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
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
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect ");
		}
		catch (Exception e){
			System.out.println("***********Fallo Location******************");
		}
	}
	
	
	public void SetInfoActualday(Actualday actual){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
			st.executeUpdate("INSERT INTO actualday(fulldate, temperature)values ('"+actual.getDate()+"','"+actual.getTemperature()+"');");
			System.out.println("SetInfoActualDay Satisfactoriamente");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Fallo Actualday******************");
		}
	}

	
	public void SetInfoWind(Actualday.Wind wind){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
			st.executeUpdate("INSERT INTO wind (`detaildate`, `chill`, `direction`, `speed`)"
								+ "SELECT MAX(id_actual),'"+wind.getChill()+"','"+wind.getDirection()+"','"+wind.getSpeed()+"'"
								+ "from actualday;");
			System.out.println("SetInfoWind Satisfactoriamente");
		}
		catch (Exception e){
			System.out.println("***********Fallo Set InfoWind******************");
		}
	}

	public void SetInfoAtmosphere(Actualday.Atmosphere at){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
			st.executeUpdate("INSERT INTO atmosphere (`detaildate`, `humidity`, `pressure`, `rising`, `visibility`)"
							+ "SELECT MAX(id_actual),'"+at.getHumidity()+"','"+at.getPressure()+"','"+at.getRising()+"','"+at.getVisibility()+"'"
							+ "from actualday;");
			System.out.println("SetInfoAtmosphere Satisfactoriamente");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Falloo SetInfoAtmosphere******************");
		}
	}
	
	public void SetInfoAstronomy(Actualday.Astronomy as){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
			st.executeUpdate("INSERT INTO astronomy(`detaildate`, `sunrise`, `sunset`) "
							+ "SELECT MAX(id_actual),'"+ as.getSunrise()+"','"+as.getSunset()+"' "
							+ "from actualday;");
			System.out.println("SetInfoAstronomy Satisfactoriamente");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Fallo Astronomy******************");
		}
	}
	
	public void SetInfoDay(Day day){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
			st.executeUpdate("INSERT INTO days(`day_name`, `forecast_id`, `tempmax`, `tempmin`) "
							+ "SELECT '"+day.getDay()+"', MAX(id_actual),'"+ day.getTempMax()+"','"+day.getTempMin()+"' "
							+ "from actualday;");
			System.out.println("SetInfoDay Satisfactoriamente");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Fallo Info Day******************");
		}
	}
	
	
	public void SetInfoActual_Location(String city){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
			st.executeUpdate("INSERT INTO actualday_location (`id_actual`, `id_location`) "
								+"SELECT MAX(id_actual), id_location from actualday "
								+"inner join location "
								+ "where city_name = '"+city+"';");
			System.out.println("SetInfoActual_Location Satisfactoriamente");
			st.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect ");
		}
		catch (Exception e){
			System.out.println("***********Falloooooooooooooooooooooo*****************");
		}
	}	
	
	public void ReadBdPlacesConsulted(){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
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
			System.out.println("There was a problem with the database when attempting to connect ");
		}
		catch (Exception e){
			System.out.println("***********Fallo reading places*****************");
		}
	}	
	
	public Connection getCon(){
		return con;
	}
}
