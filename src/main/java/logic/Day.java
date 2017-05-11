package logic;

/**
 * This class is used to create an object of each day of the forecast
 * @author Emiliano Bentivegna
 * @version 24/04/2017
 */
public class Day {
	private float tempmax;
	private float tempmin;
	private String day;
	
	//Class constructor 
	public Day(float tempmax, float tempmin, String day){
		this.tempmax = tempmax;
		this.tempmin = tempmin;
		this.day = day;
	}
	
	public float getTempMax() {
		return tempmax;
	}
	public void setTempMax(float tempMax) {
		tempmax = tempMax;
	}
	public float getTempMin() {
		return tempmin;
	}
	public void setTempMin(float tempMin) {
		tempmin = tempMin;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		day = day;
	}
	
	public String toString(){
		return "\n Day " + getDay() + "\n Temp Max "+ getTempMax() + "\n Temp Min " + getTempMin();
	}
	

}
