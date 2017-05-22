package model;

import java.sql.Connection;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persistence.DataBaseDAO;
import persistence.DataBaseMySQL;
@Component
public class ActualdayDAOImp implements ActualdayDAO{
	@Autowired
	private DataBaseDAO dat;
	@Autowired
	private Connection con;
	
//	public ActualdayDAOImp(){
//		//Call to DataBase MySQL, create if not exists and create the connection
//		dat = new DataBaseMySQL();
//	}
	
	public void Insert(Actualday actual, Actualday.Astronomy astro, Actualday.Atmosphere atmos, Actualday.Location loc, Actualday.Wind wind){
		try{
			con = dat.getCon();
			Statement st = (Statement) con.createStatement();
			String DbName = dat.getDbName();
			st.executeUpdate("use "+DbName+";");
			//Saving information from location
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
			//Saving information from acutalday
			st.executeUpdate("INSERT INTO actualday(fulldate, temperature)values ('"+actual.getDate()+"','"+actual.getTemperature()+"');");
			
			//Saving information from wind
			st.executeUpdate("INSERT INTO wind (`detaildate`, `chill`, `direction`, `speed`)"
					+ "SELECT MAX(id_actual),'"+wind.getChill()+"','"+wind.getDirection()+"','"+wind.getSpeed()+"'"
					+ "from actualday;");
			//Saving information from atmosphere
			st.executeUpdate("INSERT INTO atmosphere (`detaildate`, `humidity`, `pressure`, `rising`, `visibility`)"
					+ "SELECT MAX(id_actual),'"+atmos.getHumidity()+"','"+atmos.getPressure()+"','"+atmos.getRising()+"','"+atmos.getVisibility()+"'"
					+ "from actualday;");
			//Saving information from astronomy
			st.executeUpdate("INSERT INTO astronomy(`detaildate`, `sunrise`, `sunset`) "
					+ "SELECT MAX(id_actual),'"+ astro.getSunrise()+"','"+astro.getSunset()+"' "
					+ "from actualday;");
			//Saving information from the new entity
			st.executeUpdate("INSERT INTO actualday_location (`id_actual`, `id_location`) "
					+"SELECT MAX(id_actual), id_location from actualday "
					+"inner join location "
					+ "where city_name = '"+loc.getCity()+"';");
			System.out.println("**Actualday perfect**");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Error Insert Actualday******************");
		}
	}
	
	
}
