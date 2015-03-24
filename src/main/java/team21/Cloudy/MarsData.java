package team21.Cloudy;

/**
 * Class storing the weather data for Mars. it has no additional data apart form those in BaseData
 *
 * @author: Gurpreet
 */
public class MarsData extends BaseData
{
    /**
     * Constructor  sends the rest to the super class
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
    public MarsData(double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, int dt)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description, dt);
    }
}
