package daos;

import model.Actualday;

public class ActualdayDAOImp extends BASEWeatherDAOImp implements WeatherDAO{
	
	public void Insert(Object o){
		String toInsert;
		Actualday actual = (Actualday) o;
		toInsert = ("INSERT INTO actualday(fulldate, temperature)values ('"+actual.getDate()+"','"+actual.getTemperature()+"');");
		super.InsertGeneric(toInsert);
		
	}	
}
