package team21;

/**
 * Class extending TermObject to store 1 MarsData (TermObject used for consistency)
 *
 * @author: Gurpreet
 */
public class MarsTerm extends TermObject
{
    /**** Fields ****/
    private MarsData[] Mdata;

    /**
     * @param Mdata
     */
    public MarsTerm(MarsData [] Mdata)
    {
        super(1);
        this.Mdata = Mdata;
        parseTermData();
    }

    /**
     * set temperature data for Mars
     */
    @Override
    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Mdata[i];
        }
    }
    /**
     * store the data in an an Array
     */
}
