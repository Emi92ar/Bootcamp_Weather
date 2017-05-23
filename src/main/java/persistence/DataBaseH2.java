package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.tools.DeleteDbFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import connections.DbConnection;
import daos.DataBaseDAO;

import java.sql.Statement;
import java.sql.Connection;
@Component 
public class DataBaseH2 implements DataBaseDAO {
	private Connection con;
	private BufferedReader br;
	private String _dbName;
	@Autowired
	private DbConnection connection;
	/*
	 * Data Base to test the logic
	 */
	public DataBaseH2(){
		try{
			//The next line read the Script
			br = new BufferedReader(new FileReader("src/main/resources/dbScript"));
		    String line = br.readLine();
		    //In the next line I will connect to the db
//		    con = DbConnection.getInstance("org.h2.Driver", "jdbc:h2:~/weatherr", "root", "root").getConnection();
//		    Statement st = (Statement) con.createStatement();
		    Statement st = (Statement) connection.getConnection().createStatement();
		    String[] temp = line.split("\\s+");
		    _dbName = temp[5];
		    //Skip the first two lines of the script because they are to create the database and use 
		    line = br.readLine();
		    line = br.readLine();
		    DeleteDbFiles.execute("~", _dbName, true);
		    while (line != null) {
		    	System.out.println(line);
		    	st.executeUpdate(line);
		    	line = br.readLine();
		    }
		    st.close();
		}
		catch(FileNotFoundException ex){
			
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect ");
		}
		catch (Exception ex){
			System.out.println(ex);
		}
	}
	
	
	public void ReadBdPlacesConsulted(){
		try{
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from location "
										+ "inner join country on country_id = id_country;");
			System.out.println("\n\n_____________________________Information privided by the DataBase_____________________________");
			while(rs.next()){
				System.out.println("Id_location= " + rs.getObject("Id_location")+"   ||country_id= "+rs.getObject("country_id")+"   ||city_name= "+rs.getObject("city_name")+""
						+ "   ||longitude= "+rs.getObject("longitude")+"   ||latitude= "+rs.getObject("latitude")+"   ||id_country= "+rs.getObject("id_country")+"   "
						+ "||country_name= "+rs.getObject("country_name"));
			}
			System.out.println("\n______________________________________________________________________________________________");
			rs.close();
		}
		catch (SQLException ex){
			System.out.println("There was a problem with the database when attempting to connect ");
		}
		catch (Exception e){
			System.out.println("***********Fallo reading places*****************");
		}
	}
	
	public String getDbName(){
		return _dbName;
	}
	
	public Connection getCon(){
		return con;
	}
}