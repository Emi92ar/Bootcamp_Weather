package daos;
import model.Day;
import model.Forecast;

public class ForecastDAOImp extends BASEWeatherDAOImp implements WeatherDAO{
	
	public void Insert(Object o){
		Day day;
		Forecast forecast = (Forecast) o;
		String toInsert;
		for(int i=0; i < forecast.AmountDay(); i++){
			day = forecast.getDay(i);
			toInsert = ("INSERT INTO days(`day_name`, `forecast_id`, `tempmax`, `tempmin`) "
							+ "SELECT '"+day.getDay()+"', MAX(id_actual),'"+ day.getTempMax()+"','"+day.getTempMin()+"' "
							+ "from actualday;");
			super.InsertGeneric(toInsert);
		}
	}
}
