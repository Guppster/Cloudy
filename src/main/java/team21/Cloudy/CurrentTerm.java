package team21.Cloudy;

/**
 + * Class extending TermObject to store 1 CurrentTermData (TermObject used for consistency)
 *
 * @author: Gurpreet
 */
public class CurrentTerm extends TermObject
{
    /****Fields****/
    protected CurrentTermData[] Cdata;

    /**
     * Constructor
     * @param Cdata The array of data retrieved from the API call
     */
    public CurrentTerm(CurrentTermData[] Cdata)
    {
        super(1);
        this.Cdata = Cdata;
        parseTermData();
    }//End of constructor

    /**
     * Gets the current term data
     * @return set of current term data
     */
    public BaseData[] getData()
    {
        return Cdata;
    }

    /**
     * Converts current term data into baseterm data
     */
    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Cdata[i];
        }
    }
}
