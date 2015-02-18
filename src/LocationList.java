import java.util.ArrayList;

/**
 * @author: Gurpreet
 */
public class LocationList 
{
    private ArrayList<Location> regions;
    
    public LocationList()
    {
        regions = new ArrayList<Location>();
    }//End of general constructor
    
    public LocationList(ArrayList<Location> regions)
    {
        this.regions = regions;
    }//End of specific constructor
    
    public Location searchList(String name)
    {
        return new Location("London");
    }
    
    public boolean isLocationInList(Location location)
    {
        return true;
    }
    
    public void addRegion(Location location)
    {
        
    }
    
    public void deleteRegion(Location location)
    {
        
    }
    
    public ArrayList<Location> getLocationList()
    {
        return regions;
    }
}//End of LocationList
