
/**
 * @author: Gurpreet
 */
public class NetworkController
{
    private Location location;
    private Parser parser;
    private TermObject term;
    private BaseData[] data;

    //Specific constructor
    public NetworkController(Location location)
    {
        this.location = location;
    }
    
    //General constructor
    public NetworkController()
    {
        
    }

    public void fetchCurrent()
    {
        parser = new CurrentTermParser(location.getName());
        
        term = parser.parse();
        
        location.setCurrentTerm(term);

    }

    public void fetchLong()
    {
        parser = new LongTermParser(location.getName());
        
        term = parser.parse();

        location.setCurrentTerm(term);

    }

    public void fetchShort()
    {
        parser = new ShortTermParser(location.getName());

        term = parser.parse();

        location.setCurrentTerm(term);

    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }
}
