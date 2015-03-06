package team21;

import java.util.ArrayList;

/**
 * Class
 *
 * @author: Gurpreet
 */
public class LocationList
{
    ///// Attribute /////
    private ArrayList<Location> regions; // a list of locations

    /**
     * General Constructor
     */
    public LocationList()
    {
        regions = new ArrayList<Location>();
    }//End of general constructor

    /**
     * Specific Constructor
     *
     * @param regions the list of locations
     */
    public LocationList(ArrayList<Location> regions)
    {
        this.regions = regions;
    }//End of specific constructor

    /**
     * Search for a specific location in a list. It returns a Location object or null (if the location is not in the list)
     *
     * @param name the location as a string
     * @return default Location object (London)
     */
    public Location searchList(String name)
    {
        if (!regions.isEmpty())
        {
            //if the list is not empty, illiterate to see if location exist
            for (int i = 0; i < regions.size(); i++)
            {
                if (region.get(i).getName().equals(name))
                {
                    return region.get(i);
                }
            }
        }

        //return london as default
        return null;

    }

    /**
     * @param location
     * @return
     */
    public boolean isLocationInList(Location location)
    {
        return true;
    }

    /**
     * Add a location to the list
     *
     * @param location
     */
    public void addRegion(Location location)
    {
        regions.add(location);
    }

    /**
     * Remove a location from the list
     *
     * @param location
     */
    public void deleteRegion(Location location)
    {
        if (!regions.isEmpty())
        {
            //if the list is not empty, illiterate to see if location exist
            for (int i = 0; i < regions.size(); i++)
            {
                if (region.get(i).getName().equals(name))
                {
                    regions.remove(i);
                }
            }
        }
    }

    /**
     * get the list
     *
     * @return the entire ArrayList
     */
    public ArrayList<Location> getLocationList()
    {
        return regions;
    }

    /**
     * set the entire list
     *
     * @param regions
     */
    public void setLocationList(ArrayList<Location> regions)
    {
        this.regions = regions;
    }
}//End of LocationList
