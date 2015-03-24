package team21.Cloudy;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Class storing the long-term weather data. Extends BaseData and have additional attributes, tempNight, tempEve, and tempMorn
 *
 * @author: Gurpreet
 */
public class LongTermData extends BaseData
{
    private double tempNight; //temperature through the night
    private double tempEve; //temperature through the evening
    private double tempMorn; //temperature in the morning
    private int dt;

    /**
     * Constructor initialize the attributes with the first three parameters and sends the rest to the super class
     *
     * @param tempNight     the temperature at night
     * @param tempEve       the temperature during the evening
     * @param tempMorn      the temperature during the morning
     * @param temp          the current temperature
     * @param tempMin       the minimum predicted temperature
     * @param tempMax       the maximum predicted temperature
     * @param windSpeed     the expected wind speed
     * @param windDirection the direction the wind blows
     * @param pressure      the current air pressure
     * @param iconID        the string representing the icon that will be used to visually display the current weather
     * @param description   a short description of the weather
     */
    public LongTermData(double tempNight, double tempEve, double tempMorn, double temp, double tempMin, double tempMax, double windSpeed, int windDirection, double pressure, String iconID, String description, int dt)
    {
        super(temp, tempMin, tempMax, windSpeed, windDirection, pressure, iconID, description, dt);
        this.tempNight = tempNight;
        this.tempEve = tempEve;
        this.tempMorn = tempMorn;
    }//End of constructor

    /**
     * Extract the temperature at night
     *
     * @return the temperature at night
     */
    public double getTempNight()
    {
        return tempNight;
    }

    /**
     * Updates the temperature at night
     *
     * @param tempNight the new temperature at night
     */
    public void setTempNight(double tempNight)
    {
        this.tempNight = tempNight;
    }

    /**
     * Extract the temperature during the evening
     *
     * @return the temperature during the evening
     */
    public double getTempEve()
    {
        return tempEve;
    }

    /**
     * Update the temperature during the evening
     *
     * @param tempEve the new temperature during the evening
     */
    public void setTempEve(double tempEve)
    {
        this.tempEve = tempEve;
    }

    /**
     * Extract  the temperature during the morning
     *
     * @return the temperature during the morning
     */
    public double getTempMorn()
    {
        return tempMorn;
    }

    /**
     * Update  the temperature during the morning
     *
     * @param tempMorn the new temperature during the morning
     */
    public void setTempMorn(double tempMorn)
    {
        this.tempMorn = tempMorn;
    }
}//End of LongTermData class
