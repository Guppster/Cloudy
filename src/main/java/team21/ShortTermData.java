package team21;

/**
 * Class storing the short-term weather data. Extends BaseData and have additional attribute, time
 *
 * @author: Gurpreet
 */
public class ShortTermData extends BaseData
{
    private String time; // time of day in which these current weather value is valid for

    public ShortTermData(String time, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
        this.time = time;
    }//End of constructor

    ///// Getters and Setters /////

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}//End of class
