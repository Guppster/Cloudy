import java.util.Iterator;

/**
 * @author: Gurpreet
 */
public class Main 
{
    private static LocationList locations;
    private static Configuration config;
    private static NetworkController netController;

    /**
     * * 
     * @param args
     */
    public static void main(String [] args)
    {
        locations = new LocationList();
        config = new Configuration(locations);
        netController = new NetworkController();
    }//End of main method

    /**
     * This methods updates all TermObjects in all location objects within the location list
     */
    private static void update()
    {
        for(Location region : locations.getLocationList())
        {
            netController.setLocation(region);
            netController.fetch();
        }
    }
}//End of main class
