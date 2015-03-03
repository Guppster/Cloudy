package team21;

/**
 * @author: Gurpreet
 */
public class LongTerm extends TermObject
{
    private LongTermData[] Ldata;

    public LongTerm(LongTermData[] Ldata)
    {
        super(8);
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
