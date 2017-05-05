package logic;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


//import java.util.List;

/**
 * The manager is in charge of checking the weather 
 * on the yahoo page and fill in the information in the java code
 * @author Emiliano Bentivegna
 * @version 24/04/2017
 */
public class Manager {
	
	private String city; //It will used to ask in the URL
	private Actualday actual;
	private Forecast forecast;
	private Actualday.Location location;
	private Actualday.Wind wind;
	private Actualday.Atmosphere atmosphere;
	private Actualday.Astronomy astronomy;
	private URL json;
	
	
	//The following field is an example how this class store the information provided by the parsed
	private String sunrise;
	private String sunset;
	private float humidity;
	private float pressure;
	private float rising;
	private float visibility;
	private String chill;
	private float direction;
	private float speed;
	private String country;
	private String region;
	private float latitude;
	private float longitude;
	private String date;
	private float temperature;
	
	//I Use this Array to fill the information, 
	//When I have the information of the URL, I will not need these array
	private ArrayList<String> days;
	private ArrayList<Float> tempMin;
	private ArrayList<Float> tempMax;
	private ArrayList<Day> daysList;

	private String _user = "root"; 
	private String _pwd = "root";
	private static String _bd = "weather";
	static String _url = "jdbc:mysql://localhost:3306/" + _bd;
	private Connection conn = null;
	
	public Manager(Actualday.Location city_city){
		//here the class call a method to go to URL with the name of the City
		this.city = city_city.getCity();
		location = city_city;
		days = new ArrayList<String>();
		daysList = new ArrayList<Day>();
		tempMin = new ArrayList<Float>();
		tempMax = new ArrayList<Float>();
	
		AskForYahooForecast("yahoo");
		ParserInfo();
		CreateNodesWithInfo();
		DataBase();
	}
	
	// Will return the information from the URL. It is not a void method
	public void AskForYahooForecast(String city){
		try{
			json = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			
		}
		catch (MalformedURLException e) {
		    System.out.println("Hubo un error con el link");		
		}
		//ObjectMapper mapper = new ObjectMapper();
	}
	//With this method I want parse the information provided by Yahoo URL
	// Or maybe I will use some framework to do this 
	//Now fill the information manually to corroborate the functionality of the objects 
	public void ParserInfo(){		
		sunrise ="19:00hs";
		sunset = "7:00hs";
		humidity = 72;
		pressure = 80;
		rising = 30;
		visibility = 12;
		chill = "20";
		direction = 3;
		speed = 90;
		country = "Argentina";
		region = "Center";
		latitude = -20;
		longitude = 50;
		date = "Wed 26 Apr 2017";
		temperature = 36;
		days.add(0, "Wed"); 
		days.add(1, "Thu");
		days.add(2, "Fri"); 
		days.add(3, "Sat");
		days.add(4, "Sun");
		for(int i = 0 ; i < 5; i ++){
			tempMax.add(i, Float.valueOf(35 + i));
			tempMin.add(i, Float.valueOf(10 + i));
		}
	}
	
	public void CreateNodesWithInfo(){
		
		//Filling the missing information in the object location
		location.setCountry(country);
		location.setRegion(region);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		//Creating the information about the forecast of each day
		for(int i=0; i < 5 ; i++){
			daysList.add(i, new Day(tempMax.get(i), tempMin.get(i), days.get(i)));
		}
		//Creating the ArrayList with the forecast
		forecast = new Forecast();
		// Here I create the 5 days (one object for each one) of the forecast
		for(int i = 0; i < 5 ; i ++){
			forecast.Adding_days(daysList.get(i));
		}
		//Creating the information about that day
		actual = new Actualday(date, temperature);
		wind = new Actualday.Wind(chill, direction, speed);		
		atmosphere = new Actualday.Atmosphere(humidity, pressure, rising, visibility);
		astronomy = new Actualday.Astronomy(sunrise, sunset);
	}
	
	public void PrintInformation(){
		System.out.println(actual); 
		System.out.println(location);
		System.out.println(wind);
		System.out.println(atmosphere);
		System.out.println(astronomy);
		System.out.println("\nInformation about Forecast");
		for(int i = 0 ; i < 5 ; i++){
			System.out.println(forecast.getDay(i));
		}
		System.out.println(json);
	}
	
	public void DataBase()  {
		try
		{
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = (Connection) DriverManager.getConnection(_url, _user, _pwd);
		   if(conn != null){
			   System.out.println("Connected to the database " + _url + " correctly.");
		   }
		   //Crea un objeto SQLServerStatement para enviar instrucciones SQL a la base de datos
		   Statement st = (Statement) conn.createStatement();
		   //Creo la tabla si no existe
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Location (id_location INT AUTO_INCREMENT, city_name VARCHAR(20), longitude VARCHAR(20), latitude VARCHAR(20), PRIMARY KEY(id_location))");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Actualday (date VARCHAR(20)	NOT NULL, PRIMARY KEY(date))");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Actualday_Location (date VARCHAR(20) NOT NULL, id_location INT AUTO_INCREMENT NOT NULL, PRIMARY KEY(id_location, date))");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Wind (id_wind INT AUTO_INCREMENT, date VARCHAR(20) NOT NULL, id_location INT NOT NULL ,chill VARCHAR(20), direction VARCHAR(20), speed VARCHAR(20), PRIMARY KEY(id_wind),   FOREIGN KEY (date) REFERENCES Actualday (date)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (id_location) REFERENCES Location (id_location)ON DELETE CASCADE ON UPDATE CASCADE)");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Atmosphere (id_atmos INT AUTO_INCREMENT, date VARCHAR(20) NOT NULL, id_location INT NOT NULL, humidity VARCHAR(20), pressure VARCHAR(20), rising VARCHAR(20), visibility VARCHAR(20), PRIMARY KEY(id_atmos),   FOREIGN KEY (date) REFERENCES Actualday (date)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (id_location) REFERENCES Location (id_location)ON DELETE CASCADE ON UPDATE CASCADE)");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Astronomy (id_astro INT AUTO_INCREMENT, date VARCHAR(20) NOT NULL, id_location INT NOT NULL,  sunrise VARCHAR(20), sunset VARCHAR(20), PRIMARY KEY(id_astro),   FOREIGN KEY (date) REFERENCES Actualday (date)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (id_location) REFERENCES Location (id_location)ON DELETE CASCADE ON UPDATE CASCADE)");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Country (id_country INT AUTO_INCREMENT, date VARCHAR(20) NOT NULL, id_location INT NOT NULL, country_name VARCHAR(20), PRIMARY KEY(id_country),  FOREIGN KEY (date) REFERENCES Actualday (date)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (id_location) REFERENCES Location (id_location)ON DELETE CASCADE ON UPDATE CASCADE)");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Region (id_region INT AUTO_INCREMENT NOT NULL, date VARCHAR(20) NOT NULL, id_location INT NOT NULL, region_name VARCHAR(20), PRIMARY KEY(id_region), FOREIGN KEY (date) REFERENCES Actualday (date)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (id_location) REFERENCES Location (id_location)ON DELETE CASCADE ON UPDATE CASCADE)");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Day (day VARCHAR(20) NOT NULL, PRIMARY KEY(day))");
		   st.executeUpdate("CREATE TABLE IF NOT EXISTS Forecast (id_forecast INT AUTO_INCREMENT NOT NULL, PRIMARY KEY(id_forecast))");

		   //Saving data in the database
		   st.executeUpdate("INSERT INTO Location (city_name, longitude, latitude) VALUES ('"+location.getCity()+"','"+location.getLongitude()+"','"+location.getLatitude()+"' )");

//		   st.executeUpdate("INSERT INTO Actualday (date) VALUES ('"+actual.getDate()+"')");
//
//		   st.executeUpdate("INSERT INTO Actualday_Location (date) VALUES ('"+actual.getDate()+"')");
//		   st.executeUpdate("INSERT INTO Wind (date, chill, direction, speed) VALUES ('"+actual.getDate()+"','"+wind.getChill()+"','"+wind.getDirection()+"','"+wind.getSpeed()+"' )");
//		   st.executeUpdate("INSERT INTO Atmosphere (city_name, longitude, latitude) VALUES ('"+location.getCity()+"','"+location.getLongitude()+"','"+location.getLatitude()+"' )");
//		   st.executeUpdate("INSERT INTO Astronomy (city_name, longitude, latitude) VALUES ('"+location.getCity()+"','"+location.getLongitude()+"','"+location.getLatitude()+"' )");
//		   st.executeUpdate("INSERT INTO Country (city_name, longitude, latitude) VALUES ('"+location.getCity()+"','"+location.getLongitude()+"','"+location.getLatitude()+"' )");
//		   st.executeUpdate("INSERT INTO Region (city_name, longitude, latitude) VALUES ('"+location.getCity()+"','"+location.getLongitude()+"','"+location.getLatitude()+"' )");

		   // Asking for some data about Location
		   ResultSet rs = st.executeQuery("SELECT * FROM Location"); 
		   while (rs.next()){
		   System.out.println("id_location ="+rs.getObject("id_location")+
		    		", city_name="+rs.getObject("city_name")+
		   			", latitude="+rs.getObject("latitude")+
		   			", longitude="+rs.getObject("longitude"));
		   }
		   rs.close();
		   st.close();
		   conn.close();

		}catch (SQLException ex){
				System.out.println("There was a problem with the database when attempting to connect " + _url);
		}
		catch (ClassNotFoundException ex){
			System.out.println(ex);
		}
		catch (Exception e){
				System.out.println("***********Falloooooooooooooooooooooo******************");
		}
	}
}
