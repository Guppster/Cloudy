package team21;

/**
 * @author: Gurpreet
 */
public class MarsTerm extends TermObject
{
    private MarsData[] Mdata;
    
    public MarsTerm(MarsData [] Mdata)
    {
        super(1);
        this.Mdata = Mdata;
        parseTermData();
    }
    
    @Override
    protected void parseTermData()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = Mdata[i];
        }
    }
}
