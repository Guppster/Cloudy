/**
 * @author: Gurpreet
 */
public class LongTermData extends BaseData
{
    private double tempNight;
    private double tempEve;
    private double tempMorn;

    public LongTermData(double tempNight, double tempEve, double tempMorn, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
        this.tempNight = tempNight;
        this.tempEve = tempEve;
        this.tempMorn = tempMorn;
    }//End of constructor
}//End of LongTermData class
