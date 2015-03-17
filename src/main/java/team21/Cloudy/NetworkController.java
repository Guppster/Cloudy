package team21.Cloudy;

import org.json.JSONException;

import java.io.IOException;

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
    public boolean fetch()
    {
        try
        {
            if(location.getName().equalsIgnoreCase("mars"))
            {
                fetchMars();
            }
            else
            {
                //Fetches the current object data and initializes the currentTerm object inside of the specified location
                fetchCurrent();

                //Fetches the long object data and initializes the LongTerm object inside of the specified location
                fetchLong();

                //Fetches the short object data and initializes the shortTerm object inside of the specified location
                fetchShort();
            }
        }
        catch(IOException | JSONException j)
        {
            System.out.println("A Problem as occured while fetching data for: " + location.getName() + "\n");
            System.out.println(j);
            return false;
        }

        //Formats debugging console output
        System.out.println();

        return true;
    }//End of fetchCurrent method

    /**
     * Updates the Current term object inside of the stored location
     */
    private void fetchMars() throws IOException
    {
        //Creates a new specific parser object using the location name
        parser = new MarsTermParser(location.getName());

        //Retrieves the new parsed data object
        term = parser.parse();

        //Initializes the object inside of the location
        location.setCurrentTerm(term);
    }//End of fetchCurrent method

    /**
     * Updates the Current term object inside of the stored location
     */
    private void fetchCurrent() throws IOException
    {
        //Creates a new specific parser object using the location name
        parser = new CurrentTermParser(location.getName());

        //Retrieves the new parsed data object
        term = parser.parse();

        //Initializes the object inside of the location
        location.setCurrentTerm(term);
    }//End of fetchCurrent method

    /**
     * Updates the Long term object inside of the stored location
     */
    private void fetchLong() throws IOException
    {
        //Creates a new specific parser object using the location name
        parser = new LongTermParser(location.getName());

        //Retrieves the new parsed data object
        term = parser.parse();

        //Initializes the object inside of the location
        location.setLongTerm(term);
    }//End of fetchLong method

    /**
     *  Updates the Short term object inside of the stored location
     */
    private void fetchShort() throws IOException
    {
        //Creates a new specific parser object using the location name
        parser = new ShortTermParser(location.getName());

        //Retrieves the new parsed data object
        term = parser.parse();

        //Initializes the object inside of the location
        location.setShortTerm(term);
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
