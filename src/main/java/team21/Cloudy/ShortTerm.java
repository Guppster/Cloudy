package team21.Cloudy;

/**
 * Class extending TermObject to store an array of 8 ShortTermData (weather data for every 3 hours for 24 hours = 8 Data object)
 *
 * @author: Gurpreet
 */
public class ShortTerm extends TermObject
{
    /**
     * Stores the array of data that holds weather data for this specific shortterm
     */
    private ShortTermData[] Sdata;

    /**
     * @param Sdata The array of data retrieved from the API call
     */
    public ShortTerm(ShortTermData[] Sdata)
    {
        super(8);
        this.Sdata = Sdata;
        parseTermData();

    }//End of constructor

    /**
     *
     * @return Returns the stored array of information in this object
     */
    public BaseData[] getData()
    {
        return Sdata;
    }

    /**
     * set short term data
     */
    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Sdata[i];
        }
    }
}//End of ShortTerm Class
