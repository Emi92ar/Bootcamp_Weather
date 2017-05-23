package model;

//import model.Actualday.ActualdayBuilder;

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
	
	private Day(DayBuilder dayBuilder){
		this.tempmax = dayBuilder.tempmax;
		this.tempmin = dayBuilder.tempmin;
		this.day = dayBuilder.day;
		thermal_amplitude = tempmax - tempmin;
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
	//--------------------------------------Builder----------------------------------------------
	public static class DayBuilder {															//
		private double tempmax;																	//
		private double tempmin;																	//

		private String day;																		//
		
		public DayBuilder(double tempmax, double tempmin){
			this.tempmax = tempmax;	
			this.tempmin = tempmin;
		}																						//	
			
		public DayBuilder day(String day){														//
			this.day = day;																		//
			return this;																		//
		}		
		public Day build(){	
			Day day = new Day(this);
			if(day.getTempMax() < day.getTempMin()){
				System.out.print("Temp max is lower than temp min");							//
				//Set temperature that meaning error 
				day.setTempMax(99999999); 														//
				day.setTempMin(99999999); 														//
			}
			return day;																			//
		}																						//
	}	
	//--------------------------------------Builder----------------------------------------------
}
