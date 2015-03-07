package team21;

/**
 * Class storing the current weather data. Extends BaseData and have additional attributes, precipitation, sunrise, and sunset
 *
 * @author: Gurpreet
 */

public class CurrentTermData extends BaseData
{

    /**** Fields ****/
    private int precipitation; //level or precipitation
    private int sunrise;//time of expected sunrise
    private int sunset;//time of expected sunset

    /**
     *
     * @param precipitation
     * @param sunrise
     * @param sunset
     * @param temp
     * @param tempMin
     * @param tempMax
     * @param windSpeed
     * @param windDirection
     * @param pressure
     * @param iconID
     * @param description
     */
    public CurrentTermData(int precipitation, int sunrise, int sunset, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
        this.precipitation = precipitation;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }


    /**** Getters and Setters ****/

    public int getPrecipitation()
    {
        return precipitation;
    }

    public void setPrecipitation(int precipitation)
    {
        this.precipitation = precipitation;
    }

    public int getSunrise()
    {
        return sunrise;
    }

    public void setSunrise(int sunrise)
    {
        this.sunrise = sunrise;
    }

    public int getSunset()
    {
        return sunset;
    }

    public void setSunset(int sunset)
    {
        this.sunset = sunset;
    }
}
