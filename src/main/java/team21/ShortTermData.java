package team21;

/**
 * @author: Gurpreet
 */
public class ShortTermData extends BaseData 
{
    private String time;

    public ShortTermData(String time, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description);
        this.time = time;
    }//End of constructor

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}//End of class
