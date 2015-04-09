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
     * @param time          The time at which for the other attributes are true
     * @param temp          The current temperature
     * @param tempMin       The minimum predicted temperature
     * @param tempMax       The maximum predicted temperature
     * @param windSpeed     The expected wind speed
     * @param windDirection The direction the wind blows
     * @param pressure      The current air pressure
     * @param iconID        The string representing the icon that will be used to visually display the current weather
     * @param description   A short description of the weather
     * @param dt            The date time object indicating the time this was retrieved
     * @param humidity      The amount of humidity present in this data object
     */
    public ShortTermData(String time, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, int dt, double humidity)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description, dt, humidity);
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
