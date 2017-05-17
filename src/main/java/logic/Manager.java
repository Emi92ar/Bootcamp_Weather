package logic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import connections.DbConnection;
import model.Actualday;
import model.Day;
import model.Forecast;
import persistence.DataBaseDAO;
import persistence.DataBaseMySQL;

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
	private String humidity;
	private String pressure;
	private String rising;
	private String visibility;
	private String chill;
	private String direction;
	private String speed;
	private String country;
	private String region;
	private String latitude;
	private String longitude;
	private String date;
	private String temperature;
	private DataBaseDAO db;
	private DbConnection dbcon;
	
	//I Use this Array to fill the information, 
	//When I have the information of the URL, I will not need these array
	private ArrayList<String> days;
	private ArrayList<Float> tempMin;
	private ArrayList<Float> tempMax;
	private ArrayList<Day> daysList;

	private String _user = "root"; 
	private String _pwd = "root";
	private static String _bd = "weatherr";
	static String _url = "jdbc:mysql://localhost:3306/" /*+ _bd*/;
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
	}
	
	// Will return the information from the URL. It is not a void method
	public void AskForYahooForecast(String city){
//		try{
//			json = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
//			
//		}
//		catch (MalformedURLException e) {
//		    System.out.println("Hubo un error con el link");		
//		}
		//ObjectMapper mapper = new ObjectMapper();
	}
	//With this method I want parse the information provided by Yahoo URL
	// Or maybe I will use some framework to do this 
	//Now fill the information manually to corroborate the functionality of the objects 
	public void ParserInfo(){		
		sunrise ="19:00hs";
		sunset = "7:00hs";
		humidity = "72";
		pressure = "80";
		rising = "30";
		visibility = "12";
		chill = "20";
		direction = "3";
		speed = "90";
		country = "Chile";
		region = "Center";
		latitude = "-20";
		longitude = "50";
		date = "Wed";
		temperature = "36";
		days.add(0, "Wed"); 
		days.add(1, "Thu");
		days.add(2, "Fri"); 
		days.add(3, "Sat");
		days.add(4, "Sun");
		for(int i = 0 ; i < 5; i ++){
			tempMax.add(i, Float.valueOf(40 + i));
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
		DataBaseMySQL dat = new DataBaseMySQL();
		dat.SetInfoLocation(location);
		dat.SetInfoActualday(actual);
		dat.SetInfoWind(wind);
		dat.SetInfoAtmosphere(atmosphere);
		dat.SetInfoAstronomy(astronomy);
		for(int i = 0; i<5; i++){
			dat.SetInfoDay(forecast.getDay(i));
		}
		//This entity is new by the relationship many to many
		dat.SetInfoActual_Location(location.getCity());// 
		
//		db.ReadBdForecast(2);
		dat.ReadBdPlacesConsulted();
		

//		db = DataBase.getInstance("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306", "root", "root");
//		db.SetInfoLocation(location);
//		db.SetInfoActualday(actual);
//		db.SetInfoWind(wind);
//		db.SetInfoAtmosphere(atmosphere);
//		db.SetInfoAstronomy(astronomy);
//		for(int i = 0; i<5; i++){
//			db.SetInfoDay(forecast.getDay(i));
//		}
//		//This entity is new by the relationship many to many
//		db.SetInfoActual_Location(location.getCity());// 
//		
////		db.ReadBdForecast(2);
//		db.ReadBdPlacesConsulted();
	}
	
	public void PrintInformation(){
		System.out.println(actual); 
		System.out.println(location);
		System.out.println(wind);
		System.out.println(atmosphere);
		System.out.println(astronomy);
		System.out.println("\nInformation about the ArrayList Forecast");
		for(int i = 0 ; i < forecast.AmountDay() ; i++){
			System.out.println(forecast.getDay(i));
		}
	}
}