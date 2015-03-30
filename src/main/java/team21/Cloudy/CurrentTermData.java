package team21.Cloudy;

/**
 * Class storing the current weather data. Extends BaseData and have additional attributes, precipitation, sunrise, and sunset
 *
 * @author: Gurpreet
 */

public class CurrentTermData extends BaseData
{

    private int precipitation;      //level or precipitation
    private int sunrise;            //time of expected sunrise
    private int sunset;             //time of expected sunset

    /**
     * Constructor initialize the attributes with the first three parameters and sends the rest to the super class
     *
     * @param precipitation the amount of precipitation predicted
     * @param sunrise       time of expected sunrise
     * @param sunset        time of expected sunset
     * @param temp          the current temperature
     * @param tempMin       the minimum predicted temperature
     * @param tempMax       the maximum predicted temperature
     * @param windSpeed     the expected wind speed
     * @param windDirection the direction the wind blows
     * @param pressure      the current air pressure
     * @param iconID        the string representing the icon that will be used to visually display the current weather
     * @param description   a short description of the weather
     */
    public CurrentTermData(int precipitation, int sunrise, int sunset, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, String countryCode, int dt, double humidity)
    {
        //Initialize fields and super class fields from parameters
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description, dt, humidity);
        super.setCountryCode(countryCode);
        this.precipitation = precipitation;
        this.sunrise = sunrise;
        this.sunset = sunset;
        super.setSunrise(sunrise);
        super.setSunset(sunset);
    }

    /**
     * Extract the precipitation level
     *
     * @return the precipitation level
     */
    public int getPrecipitation()
    {
        return precipitation;
    }

    /**
     * Update the precipitation level
     *
     * @param precipitation the new precipitation level
     */
    public void setPrecipitation(int precipitation)
    {
        this.precipitation = precipitation;
    }

    /**
     * Extract the sunrise time
     *
     * @return the sunrise time
     */
    public int getSunrise()
    {
        return sunrise;
    }

    /**
     * Update the sunrise time
     *
     * @param sunrise the new sunrise time
     */
    public void setSunrise(int sunrise)
    {
        this.sunrise = sunrise;
    }

    /**
     * Extract the sunset time
     *
     * @return the sunset time
     */
    public int getSunset()
    {
        return sunset;
    }

    /**
     * Update the sunset time
     *
     * @param sunset the new  sunset time
     */
    public void setSunset(int sunset)
    {
        this.sunset = sunset;
    }
}//End of CurrentTermData class
