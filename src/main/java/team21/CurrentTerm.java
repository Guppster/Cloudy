package team21;

/**
 * @author: Gurpreet
 */
public class CurrentTerm extends TermObject
{
    private CurrentTermData[] Cdata;

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
