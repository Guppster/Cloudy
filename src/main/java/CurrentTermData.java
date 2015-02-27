
/**
 * @author: Gurpreet
 */
public class CurrentTermData extends BaseData
{
    private int precipitation;
    private int sunrise;
    private int sunset;

    public CurrentTermData(int precipitation, int sunrise, int sunset, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
        this.precipitation = precipitation;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}
