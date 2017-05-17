package logic;

public interface DataBaseDao {
	
	//Inserts
	public void SetInfoLocation(Actualday.Location loc);
	public void SetInfoActualday(Actualday actual);
	public void SetInfoWind(Actualday.Wind wind);
	public void SetInfoAtmosphere(Actualday.Atmosphere at);
	public void SetInfoAstronomy(Actualday.Astronomy as);
	public void SetInfoDay(Day day);
	public void SetInfoActual_Location(String city);
	
	// Reads
	public void ReadBdForecast(int number);
	public void ReadBdPlacesConsulted();

}
