package team21.Cloudy;

/**
 * Abstract Class for the storage of all common data related to weather
 * regardless of specific time
 *
 * @author: Gurpreet
 */
public abstract class BaseData {
    /**
     * current temperature
     */
    private double temp;
    /**
     * minimum temperature
     */
    private double tempMin;
    /**
     * maximum temperature
     *
     */
    private double tempMax; //
    /**
     * wind speed
     */
    private double windSpeed;
    /**
     * wind Direction
     */
    private int windDirection;
    /**
     * air pressure
     */
    private double pressure;
    /**
     * humidity level
     */
    private double humidity;
    /**
     * address to icon
     */
    private String iconID;
    /**
     * short description of current weather
     */
    private String description;
    /**
     * short 2 letter code representing the country the city is located in
     */
    private String countryCode;
    /***
     * Date time storage
     */
    private int dt;
    /**
     * time of sunset
     */
    private int sunset;
    /**
     * time of sunrise
     */
    private int sunrise;

    /**
     * Class Constructor initialize all of attributes to the class
     *
     * @param temp the current temperature
     * @param tempMin the minimum predicted temperature
     * @param tempMax the maximum predicted temperature
     * @param windSpeed the expected wind speed
     * @param windDirection the direction the wind blows
     * @param pressure the current air pressure
     * @param iconID the string representing the icon that will be used to visually display the current weather
     * @param description a short description of the weather
     * @param dt The date time object indicating the time this was retrieved                    
     * @param humidity The amount of humidity present in this data object
     */
    protected BaseData(double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, int dt, double humidity) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.iconID = iconID;
        this.description = description;
        this.dt = dt;
        this.humidity = humidity;
    }

    /**
     * Extract the current temperature.
     *
     * @return the current temperature
     */
    public double getTemp() {
        return temp;
    }

    /**
     * Update the current temperature.
     *
     * @param temp the new current temperature to be stored
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * Extract the minimum temperature
     *
     * @return the minimum temperature
     */
    public double getTempMin() {
        return tempMin;
    }

    /**
     * Update the minimum temperature
     *
     * @param tempMin the new minimum temperature to be stored
     */
    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Extract the maximum temperature
     *
     * @return the maximum temperature
     */
    public double getTempMax() {
        return tempMax;
    }

    /**
     * Update the maximum temperature
     *
     * @param tempMax the new maximum temperature to be stored
     */
    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    /**
     * Extract the wind speed
     *
     * @return the wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Update the wind speed
     *
     * @param windSpeed the new wind speed to be stored
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Extract the wind direction
     *
     * @return the wind direction
     */
    public int getWindDirection() {
        return windDirection;
    }

    /**
     * Update the wind direction
     *
     * @param windDirection the new wind direction to be stored
     */
    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Extract the air pressure
     *
     * @return the air pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Update the air pressure
     *
     * @param pressure the new air pressure to be stored
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * Extract the string representing the image for this particular weather
     * situation
     *
     * @return the string representing the icon
     */
    public String getIconID() {
        return iconID;
    }

    /**
     * Update the string representing the image for this particular weather
     * situation
     *
     * @param iconID the new icon code
     */
    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    /**
     * Extract the short description on the current weather
     *
     * @return the short description on the current weather
     */
    public String getDescription() {
        return description;
    }

    /**
     * Update the short description on the current weather
     *
     * @param description the new the short description on the current weather
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Extract the country code of the location
     *
     * @return Gets the two letter country code from this object
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Update the country code
     *
     * @param countryCode Sets the two letter country code in this object
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Extract the Name of the location
     *
     * @return dt followed by empty string
     */
    public String getName() {
        return dt + "";
    }

    /**
     * Extract the humidity level of the current weather
     *
     * @return humidity level
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Update the humidity level to that of the new value in the parameter
     *
     * @param humidity The humidity value to set in this object
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * Retrieve the time for the sunset
     *
     * @return the time for predicted sunset
     */
    public int getSunset() {
        return sunset;
    }

    /**
     * Update the time for the sunset
     *
     * @param sunset The sunset time
     */
    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    /**
     *
     * Retrieve the time for the sunrise
     *
     * @return the time for predicted sunrise
     *
     */
    public int getSunrise() {
        return sunrise;
    }

    /**
     ** Update the time for the sunrise
     *
     * @param sunrise The sunrise time
     */
    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }
}// End of BaseData class
