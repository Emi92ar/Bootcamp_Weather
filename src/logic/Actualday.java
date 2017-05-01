package logic;

public class Actualday {

	private String date;
	private float temperature;

	// Class constructor 
	public Actualday(String date, float temperature){
		this.date = date;
		this.temperature = temperature;
	}
	
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString(){
		return "Information about Actual Day\n Date :" + getDate() + "\n Temperature " + getTemperature();
	}
	
	
	// Nested class
	public static class Location{
		private String city;
		private String country;
		private String region;
		private float latitude;
		private float longitude;
		
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

		public float getLatitude() {
			return latitude;
		}

		public void setLatitude(float latitude) {
			this.latitude = latitude;
		}

		public float getLongitude() {
			return longitude;
		}

		public void setLongitude(float longitude) {
			this.longitude = longitude;
		}
		
		public String toString(){
			return "\nInformation about Location\n City "+ getCity() + "\n Country() " + getCountry() + "\n Region() "+ getRegion() + "\n Latitude() " + getLatitude() + "\n Longitude " + getLongitude();
		}
	}
	
	public static class Wind{
		private String chill;
		private float direction;
		private float speed;
		
		public Wind(String chill, float direction, float speed){
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
		public float getDirection() {
			return direction;
		}
		public void setDirection(float direction) {
			this.direction = direction;
		}
		public float getSpeed() {
			return speed;
		}
		public void setSpeed(float speed) {
			this.speed = speed;
		}
		public String toString(){
			return "\nInformation About Wind\n Chill "+ getChill() + "\n Direction " + getDirection() + "\n Speed " + getSpeed();
		}
	}
	
	public static class Atmosphere{
		private float humedity;
		private float pressure;
		private float rising;
		private float visibility;
		
		public Atmosphere(float humedity, float pressure, float rising, float visibility){
			this.humedity = humedity;
			this.pressure = pressure;
			this.rising = rising;
			this.visibility = visibility;
		}
		
		public float getHumedity() {
			return humedity;
		}
		public void setHumedity(float humedity) {
			this.humedity = humedity;
		}
		public float getPressure() {
			return pressure;
		}
		public void setPressure(float pressure) {
			this.pressure = pressure;
		}
		public float getRising() {
			return rising;
		}
		public void setRising(float rising) {
			this.rising = rising;
		}
		public float getVisibility() {
			return visibility;
		}
		public void setVisibility(float visibility) {
			this.visibility = visibility;
		}
		
		public String toString(){
			return "\nInformation about Atmosphere\n Humidity "+ getHumedity() + "\n Pressure "+ getPressure()+"\n Rising "+ getRising()+ "\n Visibility "+ getVisibility();
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
			return "\nInformation about Astronomy\n Sunrise " + getSunrise() + "\n Sunset " + getSunset();
		}
	}
	
}