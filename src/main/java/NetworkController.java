
/**
 * @author: Gurpreet
 */
public class NetworkController
{
    private Location location;
    private Parser parser;
    private TermObject term;

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
    }//End of fetchCurrent method

    public void fetchLong()
    {
        parser = new LongTermParser(location.getName());
        
        term = parser.parse();

        location.setCurrentTerm(term);
    }//End of fetchLong method

    public void fetchShort()
    {
        parser = new ShortTermParser(location.getName());

        term = parser.parse();

        location.setCurrentTerm(term);
    }//End of fetchShort method

    public Location getLocation()
    {
        return location;
    }//End of getLocation method

    public void setLocation(Location location)
    {
        this.location = location;
    }//End of setLocation method
}//End of class
