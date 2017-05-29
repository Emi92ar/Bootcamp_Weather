package logic;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.Actualday;
import daos.WeatherDAO;
import java.util.ArrayList;


import model.Day;
import model.Forecast;
import persistence.DataBaseMySQL;
/**
 * Main class
 *@author Emiliano Bentivegna
 */

@Component
public class Main 
{
	private String city; //It will used to ask in the URL
	private Actualday actual;
	private Forecast forecast;
	private Actualday.Location location;
	private Actualday.Wind wind;
	private Actualday.Atmosphere atmosphere;
	private Actualday.Astronomy astronomy;
	
	//The following field is an example how this class store the information provided by the parsed
	private String sunrise ="19:00hs";
	private String sunset = "7:00hs";
	private String humidity = "72";
	private String pressure = "80";
	private String rising = "30";
	private String visibility = "12";
	private String chill = "20";
	private String direction = "3";
	private String speed = "90";
	private String country = "Chile";
	private String region = "Center";
	private String latitude = "-20";
	private String longitude = "50";
	private String date = "Wed";
	private String temperature = "36";
	
	//In dat creo la base de datos si no existe, una vez que me aseguro que existe o se creo, llamo a dbConnection para conectarme a la misma
	

	
	@Autowired
	@Qualifier("dataBaseMySQL")
	private DataBaseMySQL dat;
	//Los siguientes autowires son las interfaces
	
	@Autowired
	@Qualifier("actualdayDAOImp")
	private WeatherDAO actualInyectado;
	
	@Autowired
	@Qualifier("atmosphereDAOImp")
	private WeatherDAO atmospheree;	
	
	@Autowired
	@Qualifier("astronomyDAOImp")
	private WeatherDAO astronomyy;
	
	@Autowired
	@Qualifier("windDAOImp")
	private WeatherDAO windd;
	
	@Autowired
	@Qualifier("locationDAOImp")
	private WeatherDAO locationn;
	
	@Autowired
	@Qualifier("forecastDAOImp")		
	private WeatherDAO forecastt;
	
	
	
	//I Use this Array to fill the information, 
	//When I have the information of the URL, I will not need these array
	private ArrayList<String> days;
	private ArrayList<Float> tempMin;
	private ArrayList<Float> tempMax;
	private ArrayList<Day> daysList;
	
    public static void main( String[] args )
    {	
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Main main =  context.getBean(Main.class);
    	main.startApp(args);    
    	main.PrintInformation();
    }
	
	public void startApp(String[] args){
		System.out.println ("Starting");
        System.out.println ("Insert the city's name to know the weather condition");
        String keyboardInput = "";
        Scanner scannerInput = new Scanner (System.in); 
        keyboardInput = scannerInput.nextLine();
        System.out.println ("You ask for: " + keyboardInput +".");
        
        //Create location
        Actualday.Location location = new Actualday.Location(keyboardInput);
        
		days = new ArrayList<String>();
		daysList = new ArrayList<Day>();
		tempMin = new ArrayList<Float>();
		tempMax = new ArrayList<Float>();
		
		days.add(0, "Wed"); 
		days.add(1, "Thu");
		days.add(2, "Fri"); 
		days.add(3, "Sat");
		days.add(4, "Sun");
		//create days
		for(int i = 0 ; i < 5; i ++){
			tempMax.add(i, Float.valueOf(40 + i));
			tempMin.add(i, Float.valueOf(10 + i));
		}
		
		location.setCountry(country);
		location.setRegion(region);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		
		for(int i=0; i < 5 ; i++){
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
				
		//The followings created they should be implemented using spring 
//		WeatherDAO actuall = new ActualdayDAOImp();
		actualInyectado.Insert(actual);
				
//		WeatherDAO atmospheree = new AtmosphereDAOImp();
		atmospheree.Insert(atmosphere);
				
//		WeatherDAO astronomyy = new AstronomyDAOImp();
		astronomyy.Insert(astronomy);
				
//		WeatherDAO windd = new WindDAOImp();
		windd.Insert(wind);
		
//		WeatherDAO locationn = new LocationDAOImp();
		locationn.Insert(location);
				
//		WeatherDAO forecastt = new ForecastDAOImp();
		forecastt.Insert(forecast);
		
		System.out.println("Termine de insertar");
			
//        Manager man = new Manager(location);
//        man.setLocation(location);
//        man.AskForYahooForecast("yahoo");
//		man.ParserInfo();
//		man.CreateNodesWithInfo();
//		man.PrintInformation();
	}
	
	public void PrintInformation(){
//		dat = new DataBaseMySQL();
		dat.ReadBdPlacesConsulted();
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

