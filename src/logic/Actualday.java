package logic;

public class Actualday {

	private String date;
	private String temperature;

	// Class constructor 
	public Actualday(String date, String temperature){
		this.date = date;
		this.temperature = temperature;
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
	
	public static class Wind{
		private String chill;
		private String direction;
		private String speed;
		
		public Wind(String chill, String direction, String speed){
			this.chill = chill;
			this.direction = direction;
			this.speed = speed;
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
	
	public static class Atmosphere{
		private String humedity;
		private String pressure;
		private String rising;
		private String visibility;
		
		public Atmosphere(String humedity, String pressure, String rising, String visibility){
			this.humedity = humedity;
			this.pressure = pressure;
			this.rising = rising;
			this.visibility = visibility;
		}
		
		public String getHumedity() {
			return humedity;
		}
		public void setHumedity(String humedity) {
			this.humedity = humedity;
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
			return "\nInformation about the object Atmosphere\n Humidity "+ getHumedity() + "\n Pressure "+ getPressure()+"\n Rising "+ getRising()+ "\n Visibility "+ getVisibility();
		}
	}
	
	public static class Astronomy{
		private String sunrise;
		private String sunset;
		
		public Astronomy(String sunrise, String sunset){
			this.sunrise = sunrise;
			this.sunset = sunset;
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
	
}