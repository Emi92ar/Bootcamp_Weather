package daos;

import model.Actualday;
import model.Actualday.Wind;
import org.springframework.stereotype.Component;
@Component
public class WindDAOImp extends BASEWeatherDAOImp implements WeatherDAO {

	@Override
	public void Insert(Object o) {
		Actualday.Wind wind = (Actualday.Wind) o;
		String toInsert = ("INSERT INTO wind (`detaildate`, `chill`, `direction`, `speed`)"
							+ "SELECT MAX(id_actual),'"+wind.getChill()+"','"+wind.getDirection()+"','"+wind.getSpeed()+"'"
							+ "from actualday;");
		super.InsertGeneric(toInsert);
	}

}
