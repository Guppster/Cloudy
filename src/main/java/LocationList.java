

import java.util.ArrayList;

/**
 * @author: Gurpreet
 */
public class LocationList 
{
    private ArrayList<Location> regions;

    /**
     * General Constructor
     */
    public LocationList()
    {
        regions = new ArrayList<Location>();
    }//End of general constructor

    /**
     * Specific Constructor
     * @param regions
     */
    public LocationList(ArrayList<Location> regions)
    {
        this.regions = regions;
    }//End of specific constructor

    /**
     * * 
     * @param name
     * @return
     */
    public Location searchList(String name)
    {
        return new Location("London");
    }

    /**
     * * 
     * @param location
     * @return
     */
    public boolean isLocationInList(Location location)
    {
        return true;
    }

    /**
     * * 
     * @param location
     */
    public void addRegion(Location location)
    {
        
    }

    /**
     * * 
     * @param location
     */
    public void deleteRegion(Location location)
    {
        
    }

    /**
     * @return
     */
    public ArrayList<Location> getLocationList()
    {
        return regions;
    }

    /**
     * *
     * @param regions
     */
    public void setLocationList(ArrayList<Location> regions)
    {
        this.regions = regions; 
    }
}//End of LocationList
