
/**
 * @author: Gurpreet
 */
public class NetworkController
{
    private Location location;
    private Parser parser;
    private TermObject term;

    /**
     * General constructor 
     */
    public NetworkController()
    {

    }

    /**
     * Specific constructor 
     * @param location
     */
    public NetworkController(Location location)
    {
        this.location = location;
        fetch();
    }

    /**
     * Updates all terms for current location object
     */
    public void fetch()
    {
        fetchCurrent();
        fetchLong();
        fetchShort();
    }//End of fetchCurrent method


    /**
     * Updates the current term
     */
    private void fetchCurrent()
    {
        parser = new CurrentTermParser(location.getName());
        
        term = parser.parse();
        
        location.setCurrentTerm(term);
    }//End of fetchCurrent method

    /**
     * Updates the long term
     */
    private void fetchLong()
    {
        parser = new LongTermParser(location.getName());
        
        term = parser.parse();

        location.setCurrentTerm(term);
    }//End of fetchLong method

    /**
     * Updates the short term
     */
    private void fetchShort()
    {
        parser = new ShortTermParser(location.getName());

        term = parser.parse();

        location.setCurrentTerm(term);
    }//End of fetchShort method

    /**
     * Returns the current location
     * @return  currently stored location object
     */
    public Location getLocation()
    {
        return location;
    }//End of getLocation method

    /**
     * Sets the current location
     * @param location
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }//End of setLocation method
}//End of class
