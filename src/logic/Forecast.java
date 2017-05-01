package logic;

import java.util.ArrayList;
/**
 * This class is composed of one array of object from "day"
 * @author Emiliano Bentivegna
 * @version 24/04/2017
 */
public class Forecast {
	private ArrayList<Day> days;
	
	/**
	 * Class Constructor to create the ArrayList that contains each day of the forecast
	 * @param amount Receives the number of days that will have the extended forecast
	 *  adding the days in the ArrayList days
	 */
	public Forecast(){
		days = new ArrayList<Day>();
	}
	
	/**
	 * @param day It will be the day added to the ArrayList
	 */
	public void Adding_days(Day day){
		days.add(day);		
	}
	
	public Day getDay(int index){
		return days.get(index); 
	}
	
	public int AmountDay(){
		return days.size();
	}
}
