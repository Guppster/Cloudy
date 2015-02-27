

/**
 * @author: Gurpreet
 */
public class MarsTermParser extends Parser
{
    private String locationName;
    private LongTermData[] data;
    
    //Constructor
    public MarsTermParser(String locationName)
    {
        this.locationName = locationName;
    }//End of constructor

    @Override
    protected TermObject parse()
    {
        return null;
    }

    @Override
    protected BaseData getDetails(String rawJSONData)
    {
        return null;
    }
}
