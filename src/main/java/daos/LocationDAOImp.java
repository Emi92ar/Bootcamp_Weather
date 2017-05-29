package daos;

import model.Actualday;
import org.springframework.stereotype.Component;
@Component
public class LocationDAOImp extends BASEWeatherDAOImp implements WeatherDAO{

	@Override
	public void Insert(Object o) {
		Actualday.Location loc = (Actualday.Location) o;
		String toInsert = ("INSERT INTO location(city_name,longitude,latitude,country_id)"
				+ "SELECT '"+loc.getCity()+"','"+loc.getLongitude()+"','"+loc.getLatitude()+"',id_country from country "
				+ "where not exists (" 
				+ "SELECT city_name from location "
				+ "where city_name = '"+loc.getCity()+"')"
				+ "AND country_name = '"+loc.getCountry()+"';");
		super.InsertGeneric(toInsert);
		toInsert = ("INSERT INTO country (country_name) "
				+ "SELECT * FROM (SELECT '"+loc.getCountry()+"') AS tmp "
				+ "WHERE NOT EXISTS ("
				+ "SELECT country_name from country "
				+ "where country_name = '"+loc.getCountry()+"'"
				+ ")limit 1;");
		super.InsertGeneric(toInsert);
		toInsert = ("INSERT INTO actualday_location (`id_actual`, `id_location`) "
				+"SELECT MAX(id_actual), id_location from actualday "
				+"inner join location "
				+ "where city_name = '"+loc.getCity()+"';");
		super.InsertGeneric(toInsert);
	}
}
