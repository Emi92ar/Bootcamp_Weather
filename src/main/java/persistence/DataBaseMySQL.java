package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


import daos.DataBaseDAO;
@Component
public class DataBaseMySQL implements DataBaseDAO {

	
	private Connection con;
	private BufferedReader br;
	private String _dbName;
	private String _driver;
	private String _url;
	private String _user;
	private String _pwd;

	/*
	 * Create the Data Base if not exists and the connection with the DB
	 */
	public DataBaseMySQL(){
		try{
			//The next line read the Script
			br = new BufferedReader(new FileReader("src/main/resources/dbScript"));
		    String line = br.readLine();
		    String[] temp = line.split("\\s+");
		    _dbName = temp[5];
		    //In the next line I will connect to the db
//		    con = (Connection) DbConnection.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306", "root", "root").getConnection();
//		    dbconnection.CreateDbConnection();
//		    con = (Connection) dbconnection.getConnection();
		    
		    CreateDbConnection();
		    Statement st = (Statement) con.createStatement();
		    while (line != null) {
//		    	System.out.println(line);
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
	
	private void CreateDbConnection(){
		try{
			this._driver = "com.mysql.jdbc.Driver";	//com.mysql.jdbc.Driver
			this._url = "jdbc:mysql://localhost:3306";			// 
		    this._user = "root";		// root
		    this._pwd = "root";			//, root
			Class.forName(_driver);
			con =  (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(con != null) System.out.println("Connection created");
    	}
		catch(SQLException | ClassNotFoundException e){
			System.out.println("Error to connect to db");
		}
	}
	
	
	public void ReadBdPlacesConsulted(){
		try{
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("use "+_dbName+";");
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
