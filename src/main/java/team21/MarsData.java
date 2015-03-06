package team21;

/**
 * Class storing the weather data for Mars. it has no additional data apart form those in BaseData
 *
 * @author: Gurpreet
 */
public class MarsData extends BaseData
{
    /**
     *
     * @param temp
     * @param tempMin
     * @param tempMax
     * @param windSpeed
     * @param windDirection
     * @param pressure
     * @param iconID
     * @param description
     */
    public MarsData(double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
    }
}
