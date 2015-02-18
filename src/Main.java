/**
 * @author: Gurpreet
 */
public class Main 
{
    private static LocationList locations;
    private static Configuration config;
    private static NetworkController netController;

    public static void main(String [] args)
    {
        locations = new LocationList();
        config = new Configuration(locations);
        netController = new NetworkController();
    }//End of main method
    
    private static void update()
    {
        
    }
}//End of main class
