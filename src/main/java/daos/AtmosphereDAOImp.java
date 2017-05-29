package daos;

import model.Actualday;
import model.Actualday.Atmosphere;
import org.springframework.stereotype.Component;
@Component
public class AtmosphereDAOImp extends BASEWeatherDAOImp implements WeatherDAO {

	@Override
	public void Insert(Object o) {
		Actualday.Atmosphere atmos = (Actualday.Atmosphere) o;
		String toInsert = ("INSERT INTO atmosphere (`detaildate`, `humidity`, `pressure`, `rising`, `visibility`)"
						+ "SELECT MAX(id_actual),'"+atmos.getHumidity()+"','"+atmos.getPressure()+"','"+atmos.getRising()+"','"+atmos.getVisibility()+"'"
						+ "from actualday;");
		super.InsertGeneric(toInsert);
		
	}

}
