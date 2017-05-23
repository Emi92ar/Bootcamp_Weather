package daos;

import java.sql.Connection;


public interface DataBaseDAO {
	
	public Connection getCon();
	public String getDbName();
	
	// Reads
//	public void ReadBdForecast(int number);
	public void ReadBdPlacesConsulted();

}
