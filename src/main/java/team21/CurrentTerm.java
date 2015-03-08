package team21;

/**
 + * Class extending TermObject to store 1 CurrentTermData (TermObject used for consistency)
 *
 * @author: Gurpreet
 */
public class CurrentTerm extends TermObject
{
<<<<<<< HEAD

    /**** Fields ****/
    private int precipitation; //level or precipitation
    private int sunrise;       //time of expected sunrise
    private int sunset;        //time of expected sunset

    /**
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
    /**
     * return the level of precipitaiton
     * @param precipitation
     */

    public void setPrecipitation(int precipitation)
    {
        this.precipitation = precipitation;
    }
    /**
     * set the precipitation level first
     * @return
     */

    public int getSunrise()
    {
        return sunrise;
    }
    /**
     * get the time of sunrise
     * @param sunrise
     */

    public void setSunrise(int sunrise)
    {
        this.sunrise = sunrise;
    }
    /**
     * set the sunrise time
     * @return
     */

    public int getSunset()
=======
    /****Fields****/
    private CurrentTermData[] Cdata;

    /**
     * @param Cdata
     */
    public CurrentTerm(CurrentTermData[] Cdata)
>>>>>>> e27037a8a019c717a1343fc20347409ef4940844
    {
        super(1);
        this.Cdata = Cdata;
        parseTermData();
    }//End of constructor

    /**
<<<<<<< HEAD
     * get the time of sunset
     * @param sunset
     */

    public void setSunset(int sunset)
=======
     *
     */
    protected void parseTermData()
>>>>>>> e27037a8a019c717a1343fc20347409ef4940844
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Cdata[i];
        }
    }
<<<<<<< HEAD
    /**
     * set the sunset time
     */
}
=======
}
>>>>>>> e27037a8a019c717a1343fc20347409ef4940844
