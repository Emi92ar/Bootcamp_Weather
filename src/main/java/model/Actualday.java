package model;


public class Actualday {

	private String date;
	private String temperature;
	private Wind wind;

	// Class constructor 
//	public Actualday(String date, String temperature){
//		this.date = date;
//		this.temperature = temperature;
//	}
	
	private Actualday(ActualdayBuilder builder) {
		this.date = builder.date;
		this.temperature = builder.temperature;
	}

	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString(){
		return "Information about the object Actual Day\n Date :" + getDate() + "\n Temperature " + getTemperature();
	}
	
	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	//------------------------------------Builder-------------------------------------------------
	public static class ActualdayBuilder {														//
		private String date;																	//
		private String temperature;																//
																								//
		public ActualdayBuilder date(String date){												//
			this.date = date;																	//
			return this;																		//
		}																						//
																								//
		public ActualdayBuilder temperature(String temperature){								//
			this.temperature = temperature;														//
			return this;																		//
		}																						//
																								//
		public Actualday build(){																//
			return new Actualday(this);															//
		}																						//
	}																							//
	//------------------------------------Builder-------------------------------------------------
	
	
	// Nested class
	public static class Location{
		private String city;
		private String country;
		private String region;
		private String latitude;
		private String longitude;
		
		public Location(String city){
			this.city = city;
		}
		
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		
		public String toString(){
			return "\nInformation about the object Location\n City "+ getCity() + "\n Country() " + getCountry() + "\n Region() "+ getRegion() + "\n Latitude() " + getLatitude() + "\n Longitude " + getLongitude();
		}
	}
	
	//------------------------------------Builder-------------------------------------------------
	public static class LocationBuilder{
		
	}
	//------------------------------------Builder-------------------------------------------------
	public static class Wind{
		private String chill;
		private String direction;
		private String speed;
		
//		public Wind(String chill, String direction, String speed){
//			this.chill = chill;
//			this.direction = direction;
//			this.speed = speed;
//		}
		private Wind(WindBuilder builderwind){
			this.chill = builderwind.chill;
			this.direction = builderwind.direction;
			this.speed = builderwind.speed;
		}
		
		public String getChill() {
			return chill;
		}
		public void setChill(String chill) {
			this.chill = chill;
		}
		public String getDirection() {
			return direction;
		}
		public void setDirection(String direction) {
			this.direction = direction;
		}
		public String getSpeed() {
			return speed;
		}
		public void setSpeed(String speed) {
			this.speed = speed;
		}
		public String toString(){
			return "\nInformation about the object Wind\n Chill "+ getChill() + "\n Direction " + getDirection() + "\n Speed " + getSpeed();
		}
	}
	//------------------------------------Builder-------------------------------------------------
	public static class WindBuilder{
		private String chill;
		private String direction;
		private String speed;
		
		public WindBuilder chill(String chill){													//
			this.chill = chill;																	//
			return this;																		//
		}																						//
																								//
		public WindBuilder direction(String direction){											//
			this.direction = direction;															//
			return this;																		//
		}																						//
		
		public WindBuilder speed(String speed){													//
			this.speed = speed;																	//
			return this;																		//
		}	
		public Wind build(){																	//
			return new Wind(this);																//
		}				
	}
	//------------------------------------Builder-------------------------------------------------
	public static class Atmosphere{
		private String humidity;
		private String pressure;
		private String rising;
		private String visibility;
		
//		public Atmosphere(String Humidity, String pressure, String rising, String visibility){
//			this.Humidity = Humidity;
//			this.pressure = pressure;
//			this.rising = rising;
//			this.visibility = visibility;
//		}
		private Atmosphere(AtmosphereBuilder atBuilder){
			this.humidity = atBuilder.humidity;
			this.pressure = atBuilder.pressure;
			this.rising = atBuilder.rising;
			this.visibility = atBuilder.visibility;
		}
		
		
		public String getHumidity() {
			return humidity;
		}
		public void setHumidity(String humidity) {
			this.humidity = humidity;
		}
		public String getPressure() {
			return pressure;
		}
		public void setPressure(String pressure) {
			this.pressure = pressure;
		}
		public String getRising() {
			return rising;
		}
		public void setRising(String rising) {
			this.rising = rising;
		}
		public String getVisibility() {
			return visibility;
		}
		public void setVisibility(String visibility) {
			this.visibility = visibility;
		}
		
		public String toString(){
			return "\nInformation about the object Atmosphere\n Humidity "+ getHumidity() + "\n Pressure "+ getPressure()+"\n Rising "+ getRising()+ "\n Visibility "+ getVisibility();
		}
	}
	
	//------------------------------------Builder-------------------------------------------------
	public static class AtmosphereBuilder{														//
		private String humidity;																//
		private String pressure;																//
		private String rising;																	//
		private String visibility;																//
		
		public AtmosphereBuilder humidity(String humidity){										//
			this.humidity = humidity;															//
			return this;																		//
		}																						//
																								//
		public AtmosphereBuilder pressure(String pressure){										//
			this.pressure = pressure;															//
			return this;																		//
		}																						//
		
		public AtmosphereBuilder rising(String rising){											//
			this.rising = rising;																//
			return this;																		//
		}	
		
		public AtmosphereBuilder visibility(String visibility){									//
			this.visibility = visibility;														//
			return this;																		//
		}	
		
		public Atmosphere build(){																//
			return new Atmosphere(this);														//
		}			
	}
	//------------------------------------Builder-------------------------------------------------
	public static class Astronomy{
		private String sunrise;
		private String sunset;
		
//		public Astronomy(String sunrise, String sunset){
//			this.sunrise = sunrise;
//			this.sunset = sunset;
//		}
		
		private Astronomy(AstronomyBuilder asBuilder){
			this.sunrise = asBuilder.sunrise;
			this.sunset = asBuilder.sunset;
		}
		
		public String getSunrise() {
			return sunrise;
		}
		public void setSunrise(String sunrise) {
			this.sunrise = sunrise;
		}
		public String getSunset() {
			return sunset;
		}
		public void setSunset(String sunset) {
			this.sunset = sunset;
		}
		
		public String toString(){
			return "\nInformation about the object Astronomy\n Sunrise " + getSunrise() + "\n Sunset " + getSunset();
		}
	}
	
	//------------------------------------Builder-------------------------------------------------
	public static class AstronomyBuilder{														//
		private String sunrise;
		private String sunset;																	//																//
		
		public AstronomyBuilder sunrise(String sunrise){										//
			this.sunrise = sunrise;																//
			return this;																		//
		}																						//
																								//
		public AstronomyBuilder sunset(String sunset){											//
			this.sunset = sunset;																//
			return this;																		//
		}																						//
		
		public Astronomy build(){																//
			return new Astronomy(this);															//
		}			
	}
	//------------------------------------Builder-------------------------------------------------
}