package team21.Cloudy;

/**
 * Class extending TermObject to store 1 MarsData (TermObject used for consistency)
 *
 * @author: Gurpreet
 */
public class MarsTerm extends TermObject
{
    /**
     * The mars data array of information
     */
    private MarsData[] Mdata;

    /**
     * @param Mdata The data used to initialize the TermObject
     */
    public MarsTerm(MarsData [] Mdata)
    {
        super(1);
        this.Mdata = Mdata;
        parseTermData();
    }

    /**
     *
     * @return Mars BaseData Array
     */
    public BaseData[] getData()
    {
        return Mdata;
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
