package team21;

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
     * @param Ldata
     */
    public LongTerm(LongTermData[] Ldata)
    {
        super(4);
        this.Ldata = Ldata;
        parseTermData();

    }//End of constructor


    /**
     *
     * @return
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
    /**
     * save all data information in an array
     */
}
