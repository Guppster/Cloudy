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
}//End of class
