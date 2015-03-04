package team21;

/**
 * Abstract Class for the storage of all data related to weather
 *
 * @author: Gurpreet
 */
public abstract class BaseData
{
    ///// Attributes //////
    private double temp; //current temperature
    private double tempMin; //minimum temperature
    private double tempMax; //maximum temperature
    private double windSpeed; //wind speed
    private int windDirection; //wind Direction
    private double pressure; //pressure
    private String iconID; //address to icon
    private String description; //short description of current weather

    ///// Constructor /////
    protected BaseData(double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description)
    {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.iconID = iconID;
        this.description = description;
    }

    ///// GETTERS AND SETTER /////

    public double getTemp()
    {
        return temp;
    }

    public void setTemp(double temp)
    {
        this.temp = temp;
    }

    public double getTempMin()
    {
        return tempMin;
    }

    public void setTempMin(double tempMin)
    {
        this.tempMin = tempMin;
    }

    public double getTempMax()
    {
        return tempMax;
    }

    public void setTempMax(double tempMax)
    {
        this.tempMax = tempMax;
    }

    public double getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection()
    {
        return windDirection;
    }

    public void setWindDirection(int windDirection)
    {
        this.windDirection = windDirection;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    public String getIconID()
    {
        return iconID;
    }

    public void setIconID(String iconID)
    {
        this.iconID = iconID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}//End of BaseData class
