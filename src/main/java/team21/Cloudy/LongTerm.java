package team21.Cloudy;

/**
 * Class extending TermObject to store an array of 5 LongTermData (the weather prediction for the next 5 days)
 *
 * @author: Gurpreet
 */
public class LongTerm extends TermObject
{
    /**** Fields ****/
    private LongTermData[] Ldata;

    /**
     * Constructor
     * @param Ldata initializes class with LongTermData object
     */
    public LongTerm(LongTermData[] Ldata)
    {
        super(5);
        this.Ldata = Ldata;
        parseTermData();

    }//End of constructor


    /**
     * Getter for data
     * @return returns the basedata object
     */
    public BaseData[] getData()
    {
        return Ldata;
    }

    /**
     * set the data of long term
     */
    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Ldata[i];
        }
    }
}//End of LongTerm class
