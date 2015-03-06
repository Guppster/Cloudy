package team21;

/**
 * Class extending TermObject to store an array of 5 LongTermData (the weather prediction for the next 5 days)
 *
 * @author: Gurpreet
 */
public class LongTerm extends TermObject
{
    ///// Attribute //////
    private LongTermData[] Ldata;

    ///// Constructor /////
    public LongTerm(LongTermData[] Ldata)
    {
        super(5);
        this.Ldata = Ldata;
        parseTermData();

    }//End of constructor

    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Ldata[i];
        }
    }
}
