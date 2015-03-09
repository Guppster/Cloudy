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
     * @param Cdata
     */
    public CurrentTerm(CurrentTermData[] Cdata)
    {
        super(1);
        this.Cdata = Cdata;
        parseTermData();
    }//End of constructor

    /**
     *
     * @return
     */
    public BaseData[] getData()
    {
        return Cdata;
    }

    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Cdata[i];
        }
    }
}
