package team21.Cloudy;

/*
 * Abstract Class for the storage of all common data related to weather regardless of specific time
 *
 * @author: Gurpreet
 */
public abstract class BaseData
{
    private double temp;                //current temperature
    private double tempMin;             //minimum temperature
    private double tempMax;             //maximum temperature
    private double windSpeed;           //wind speed
    private int windDirection;          //wind Direction
    private double pressure;            //pressure
    private double humidity;
    private String iconID;              //address to icon
    private String description;         //short description of current weather
    private String countryCode;
    private int dt;
    private int sunset;
    private int sunrise;

    /*
     * Class Constructor initialize all of attributes to the class
     *
     * @param temp          the current temperature
     * @param tempMin       the minimum predicted temperature
     * @param tempMax       the maximum predicted temperature
     * @param windSpeed     the expected wind speed
     * @param windDirection the direction the wind blows
     * @param pressure      the current air pressure
     * @param iconID        the string representing the icon that will be used to visually display the current weather
     * @param description   a short description of the weather
     */
    protected BaseData(double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, int dt, double humidity)
    {
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

    /*
     * Extract the current temperature.
     *
     * @return the current temperature
     */
    public double getTemp()
    {
        return temp;
    }

    /*
     * Update the current temperature.
     *
     * @param temp the new current temperature to be stored
     */
    public void setTemp(double temp)
    {
        this.temp = temp;
    }

    /*
     * Extract the minimum temperature
     *
     * @return the minimum temperature
     */
    public double getTempMin()
    {
        return tempMin;
    }

    /*
     * Update the minimum temperature
     *
     * @param tempMin the new minimum temperature to be stored
     */
    public void setTempMin(double tempMin)
    {
        this.tempMin = tempMin;
    }

    /*
     * Extract the maximum temperature
     *
     * @return the maximum temperature
     */
    public double getTempMax()
    {
        return tempMax;
    }

    /*
     * Update the maximum temperature
     *
     * @param tempMax the new maximum temperature to be stored
     */
    public void setTempMax(double tempMax)
    {
        this.tempMax = tempMax;
    }

    /*
     * Extract the wind speed
     *
     * @return the wind speed
     */
    public double getWindSpeed()
    {
        return windSpeed;
    }

    /*
     * Update the wind speed
     *
     * @param windSpeed the new wind speed to be stored
     */
    public void setWindSpeed(double windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    /*
     * Extract the wind direction
     *
     * @return the wind direction
     */
    public int getWindDirection()
    {
        return windDirection;
    }

    /*
     * Update the wind direction
     *
     * @param windDirection the new wind direction to be stored
     */
    public void setWindDirection(int windDirection)
    {
        this.windDirection = windDirection;
    }

    /*
     * Extract the air pressure
     *
     * @return the air pressure
     */
    public double getPressure()
    {
        return pressure;
    }

    /*
     * Update the air pressure
     *
     * @param pressure the new air pressure to be stored
     */
    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    /*
     * Extract the string representing the image for this particular weather situation
     *
     * @return the string representing the icon
     */
    public String getIconID()
    {
        return iconID;
    }

    /*
     * Update the string representing the image for this particular weather situation
     *
     * @param iconID the new icon code
     */
    public void setIconID(String iconID)
    {
        this.iconID = iconID;
    }

    /*
     * Extract the short description on the current weather
     *
     * @return the short description on the current weather
     */
    public String getDescription()
    {
        return description;
    }

    /*
     * Update the short description on the current weather
     *
     * @param description the new the short description on the current weather
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /*
     *get the code of the country
     */
    public String getCountryCode()
    {
        return countryCode;
    }

    /*
     *set the code of the country
     */
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getName()
    {
        return dt + "";
    }

    /*
     *get the humidity
     */
    public double getHumidity()
    {
        return humidity;
    }

     /*
      *set the humidity
      */    
    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

     /*
      *get the time of sunset
      */
    public int getSunset()
    {
        return sunset;
    }

     /*
      *set the time of sunset
      */
    public void setSunset(int sunset)
    {
        this.sunset = sunset;
    }

    /*
     *get the time of sunrise
     */
    public int getSunrise()
    {
        return sunrise;
    }

    /*
     *set the time of sunrise
     */
    public void setSunrise(int sunrise)
    {
        this.sunrise = sunrise;
    }
}//End of BaseData class
