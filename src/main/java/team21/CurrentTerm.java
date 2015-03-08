package team21;

/**
 + * Class extending TermObject to store 1 CurrentTermData (TermObject used for consistency)
 *
 * @author: Gurpreet
 */
public class CurrentTerm extends TermObject
{
    /****Fields****/
    private CurrentTermData[] Cdata;

    /**
     * @param Cdata
     */
    public CurrentTerm(CurrentTermData[] Cdata)
    {
        super(1);
        this.Cdata = Cdata;
        parseTermData();
    }//End of constructor

    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Cdata[i];
        }
    }
}
