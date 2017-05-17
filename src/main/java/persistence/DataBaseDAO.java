package persistence;

import model.Actualday;
import model.Day;
import model.Actualday.Astronomy;
import model.Actualday.Atmosphere;
import model.Actualday.Location;
import model.Actualday.Wind;

public interface DataBaseDAO {
	
	//Inserts
	public void SetInfoLocation(Actualday.Location loc);
	public void SetInfoActualday(Actualday actual);
	public void SetInfoWind(Actualday.Wind wind);
	public void SetInfoAtmosphere(Actualday.Atmosphere at);
	public void SetInfoAstronomy(Actualday.Astronomy as);
	public void SetInfoDay(Day day);
	public void SetInfoActual_Location(String city);
	
	// Reads
//	public void ReadBdForecast(int number);
	public void ReadBdPlacesConsulted();

}
