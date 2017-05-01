package logic;

/**
 * This class is used to create an object of each day of the forecast
 * @author Emiliano Bentivegna
 * @version 24/04/2017
 */
public class Day {
	private float TempMax;
	private float TempMin;
	private String Day;
	
	//Class constructor 
	public Day(float TempMax, float TempMin, String Day){
		this.TempMax = TempMax;
		this.TempMin = TempMin;
		this.Day = Day;
	}
	
	public float getTempMax() {
		return TempMax;
	}
	public void setTempMax(float tempMax) {
		TempMax = tempMax;
	}
	public float getTempMin() {
		return TempMin;
	}
	public void setTempMin(float tempMin) {
		TempMin = tempMin;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	
	public String toString(){
		return "\n Day " + getDay() + "\n Temp Max "+ getTempMax() + "\n Temp Min " + getTempMin();
	}
	

}
