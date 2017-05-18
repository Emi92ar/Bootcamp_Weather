package model;

/**
 * This class is used to create an object of each day of the forecast
 * @author Emiliano Bentivegna
 * @version 24/04/2017
 */
public class Day{
	private double tempmax;
	private double tempmin;
	private double thermal_amplitude;

	private String day;
	
	//Class constructor 
	public Day(double tempmax, double tempmin, String day){
		if(tempmax < tempmin){
			// If this condition is true, there is an error, so I set the default values
			this.tempmax = 99999999;
			this.tempmin = 99999999;
		}
		else{
			this.tempmax = tempmax;
			this.tempmin = tempmin;
			thermal_amplitude = tempmax - tempmin;
			this.day = day;
		}
	}
	
	public double getTempMax() {
		return tempmax;
	}
	public void setTempMax(double tempMax) {
		tempmax = tempMax;
		setThermalAmplitude();
	}
	public double getTempMin() {
		return tempmin;
	}
	public void setTempMin(double tempMin) {
		tempmin = tempMin;
		setThermalAmplitude();
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public void setThermalAmplitude(){
		thermal_amplitude = (tempmax - (tempmin));
	}
	
	public double getThermalAmplitude(){
		return thermal_amplitude;
	}
	
	public String toString(){
		return "\n Day " + getDay() + "\n Temp Max "+ getTempMax() + "\n Temp Min " + getTempMin();
	}
	

}
