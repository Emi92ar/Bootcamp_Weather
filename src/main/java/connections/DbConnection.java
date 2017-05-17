package connections;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
//import com.mysql.jdbc.Connection;

public class DbConnection {
	
	private String _user;
	private String _pwd;
	private String _url;
	private String _driver;
	
	private static DbConnection instance = null;
	private Connection con = null;
	/*
	 * Constructor of the db where
	 * @param _driver contatin if there is a H2 o MySQL Db
	 * @param _url Specifies where is the db
	 * @param _user User
	 * @param _pwd Password
	 */
	private DbConnection(String driver, String url, String user, String pwd){
		try{
			this._driver = driver;	//com.mysql.jdbc.Driver
			this._url = url;			// jdbc:mysql://localhost:3306
		    this._user = user;		// root
		    this._pwd = pwd;			//, root
			Class.forName(_driver);
			con =  (Connection) DriverManager.getConnection(_url, _user, _pwd);
			if(con != null) System.out.println("Connection created");
    	}
		catch(SQLException | ClassNotFoundException e){
			System.out.println("Error to connect to db");
		}
	}
	
	public static DbConnection getInstance(String driver, String url, String user, String pwd){
		if(instance == null){
			instance = new DbConnection(driver, url, user, pwd);
		}
		return instance;
	}
	public Connection getConnection(){
		return con;
	}
	
	public void Desconect(){
		con = null;
	}
	
	/*
	 * This method is only used to do other test and reset instance
	 */
	public void Reset(){
		instance = null;
	}
}
