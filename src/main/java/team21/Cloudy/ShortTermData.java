package team21.Cloudy;

/**
 * Class storing the short-term weather data. Extends BaseData and have additional attribute, time
 *
 * @author: Gurpreet
 */
public class ShortTermData extends BaseData
{
    private String time;         // time of day in which these current weather value is valid for

    /**
     * Constructor initialize the attribute with the first parameter and sends the rest to the super class
     *
     * @param time          the time at which for the other attributes are true
     * @param temp          the current temperature
     * @param tempMin       the minimum predicted temperature
     * @param tempMax       the maximum predicted temperature
     * @param windSpeed     the expected wind speed
     * @param windDirection the direction the wind blows
     * @param pressure      the current air pressure
     * @param iconID        the string representing the icon that will be used to visually display the current weather
     * @param description   a short description of the weather
     */
    public ShortTermData(String time, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, int dt)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description, dt);
        this.time = time;
    }//End of constructor

    /**
     * Extract the  time
     *
     * @return the time
     */
    public String getTime()
    {
        return time;
    }

    /**
     * Update the time
     *
     * @param time the new time
     */
    public void setTime(String time)
    {
        this.time = time;
    }
}//End of class
