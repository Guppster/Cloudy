

/**
 * @author: Gurpreet
 */
public class ShortTerm extends TermObject
{
    private ShortTermData[] Sdata;
    
    public ShortTerm(ShortTermData[] Sdata)
    {
        super(5);
        this.Sdata = Sdata;
        parseTermData();
    }//End of constructor

    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Sdata[i];
        }
    }
}//End of ShortTerm Class
