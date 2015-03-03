package team21;

import aurelienribon.tweenengine.Tween;
import team21.slidinglayout.*;

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

        Tween.registerAccessor(ThePanel.class, new ThePanel.Accessor());
        SLAnimator.start();

        TheFrame frame = new TheFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
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