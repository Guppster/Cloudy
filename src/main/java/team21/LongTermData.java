package team21;

/**
 * Class storing the long-term weather data. Extends BaseData and have additional attributes, tempNight, tempEve, and tempMorn
 *
 * @author: Gurpreet
 */
public class LongTermData extends BaseData
{
    ///// Attributes //////
    private double tempNight; //temperature through the night
    private double tempEve; //temperature through the evening
    private double tempMorn; //temperature in the morning

    ///// Constructor /////
    public LongTermData(double tempNight, double tempEve, double tempMorn, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
        this.tempNight = tempNight;
        this.tempEve = tempEve;
        this.tempMorn = tempMorn;
    }//End of constructor

    ///// Getters and Setters /////

    public double getTempNight()
    {
        return tempNight;
    }

    public void setTempNight(double tempNight)
    {
        this.tempNight = tempNight;
    }

    public double getTempEve()
    {
        return tempEve;
    }

    public void setTempEve(double tempEve)
    {
        this.tempEve = tempEve;
    }

    public double getTempMorn()
    {
        return tempMorn;
    }

    public void setTempMorn(double tempMorn)
    {
        this.tempMorn = tempMorn;
    }
}//End of LongTermData class
