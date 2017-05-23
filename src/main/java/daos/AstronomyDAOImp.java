package daos;

import model.Actualday;
import model.Actualday.Astronomy;

public class AstronomyDAOImp extends BASEWeatherDAOImp implements WeatherDAO{

	@Override
	public void Insert(Object o) {
		Actualday.Astronomy astro = (Actualday.Astronomy) o;
		String toInsert = ("INSERT INTO astronomy(`detaildate`, `sunrise`, `sunset`) "
				+ "SELECT MAX(id_actual),'"+ astro.getSunrise()+"','"+astro.getSunset()+"' "
				+ "from actualday;");
		super.InsertGeneric(toInsert);
	}

}
