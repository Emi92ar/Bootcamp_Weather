package logic;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import daos.ActualdayDAOImp;
import daos.AstronomyDAOImp;
import daos.AtmosphereDAOImp;
import daos.ForecastDAOImp;
import daos.LocationDAOImp;
import daos.WeatherDAO;
import daos.WindDAOImp;

import model.Actualday;
import model.Day;
import model.Forecast;
import persistence.DataBaseMySQL;



/**
 * The manager is in charge of checking the weather 
 * on the yahoo page and fill in the information in the java code
 * @author Emiliano Bentivegna
 * @version 24/04/2017
 */
@Component
public class Manager {
	
	private String city; //It will used to ask in the URL
	private Actualday actual;
	private Forecast forecast;
	private Actualday.Location location;
	private Actualday.Wind wind;
	private Actualday.Atmosphere atmosphere;
	private Actualday.Astronomy astronomy;
	
	
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
	
	@Autowired
	private DataBaseMySQL dat;

	
	//I Use this Array to fill the information, 
	//When I have the information of the URL, I will not need these array
	private ArrayList<String> days;
	private ArrayList<Float> tempMin;
	private ArrayList<Float> tempMax;
	private ArrayList<Day> daysList;
	
	public Manager(/*Actualday.Location city_city*/){
		//here the class call a method to go to URL with the name of the City
//		this.city = city_city.getCity();
//		location = city_city;
		days = new ArrayList<String>();
		daysList = new ArrayList<Day>();
		tempMin = new ArrayList<Float>();
		tempMax = new ArrayList<Float>();
		
//		AskForYahooForecast("yahoo");
//		ParserInfo();
//		CreateNodesWithInfo();
	}
	
	// Will return the information from the URL. It is not a void method
	public void AskForYahooForecast(String city){

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
	
	public void setLocation(Actualday.Location location){
		this.location = location;
	}
	

	
	public void CreateNodesWithInfo(){
		
		//Filling the missing information in the object location
		location.setCountry(country);
		location.setRegion(region);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		city = location.getCity();
		location.setCity(city);
		//Creating the information about the forecast of each day
		for(int i=0; i < 5 ; i++){
//			daysList.add(i, new Day(tempMax.get(i), tempMin.get(i), days.get(i)));
			daysList.add(i, new Day.DayBuilder(tempMax.get(i), tempMin.get(i)).day(days.get(i)).build());
		}
		//Creating the ArrayList with the forecast
		forecast = new Forecast();
		// Here I create the 5 days (one object for each one) of the forecast
		for(int i = 0; i < 5 ; i ++){
			forecast.Adding_days(daysList.get(i));
		}

		//Creating the information about that day
		actual = new Actualday.ActualdayBuilder().date(date).temperature(temperature).build();
		actual.setLocation(location);
		
		wind = new Actualday.WindBuilder().chill(chill).direction(direction).speed(speed).build();
		actual.setWind(wind);
		
		atmosphere = new Actualday.AtmosphereBuilder().humidity(humidity).pressure(pressure).rising(rising).visibility(visibility).build();
		actual.setAtmosphere(atmosphere);
		
		astronomy = new Actualday.AstronomyBuilder().sunrise(sunrise).sunset(sunset).build();
		actual.setAstronomy(astronomy);
		
		WeatherDAO actuall = new ActualdayDAOImp();
		actuall.Insert(actual);
		
		WeatherDAO atmospheree = new AtmosphereDAOImp();
		atmospheree.Insert(atmosphere);
		
		WeatherDAO astronomyy = new AstronomyDAOImp();
		astronomyy.Insert(astronomy);
		
		WeatherDAO windd = new WindDAOImp();
		windd.Insert(wind);
		
		WeatherDAO locationn = new LocationDAOImp();
		locationn.Insert(location);
		
		WeatherDAO forecastt = new ForecastDAOImp();
		forecastt.Insert(forecast);
		
//		dat = new DataBaseMySQL();
		dat.ReadBdPlacesConsulted();
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