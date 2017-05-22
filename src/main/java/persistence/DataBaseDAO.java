package persistence;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import model.Actualday;
import model.Day;
import model.Actualday.Astronomy;
import model.Actualday.Atmosphere;
import model.Actualday.Location;
import model.Actualday.Wind;
@Component
public interface DataBaseDAO {
	
	public Connection getCon();
	public String getDbName();
	
	// Reads
//	public void ReadBdForecast(int number);
	public void ReadBdPlacesConsulted();

}
