package team21.Cloudy;

import java.util.ArrayList;

/**
 * Class storing a list of locations.
 */
public class LocationList
{
    private ArrayList<Location> regions; // a list of locations

    /**
     * General Constructor initialize regions with an empty
     */
    public LocationList()
    {
        regions = new ArrayList<Location>();
    }//End of general constructor

    /**
     * Specific Constructor initialize regions with argument passed
     *
     * @param regions a list of locations
     */
    public LocationList(ArrayList<Location> regions)
    {
        this.regions = regions;
    }//End of specific constructor

    /**
     * Search for a specific location in a list. It returns a Location object or null (if the location is not in the list)
     *
     * @param name the location as a string
     * @return Location  object that contains data for the passed name or <code>null</code>  if the location is not in the list
     */
    public Location searchList(String name)
    {
        if (!isListEmpty())
        {
            //if the list is not empty, illiterate to see if location exist
            for (int i = 0; i < regions.size(); i++)
            {
                if (regions.get(i).getName().equals(name))
                {
                    return regions.get(i);
                }
            }
        }

        //return null as default
        return null;
    }

    /**
     * Add a location to the list
     *
     * @param location the location being added
     */
    public void addRegion(Location location)
    {
        regions.add(location);
    }

    /**
     * Remove a location from the list
     *
     * @param location the
     */
    public void deleteRegion(Location location)
    {
        if (!isListEmpty())
        {
            //if the list is not empty, illiterate to see if location exist
            for (int i = 0; i < regions.size(); i++)
            {
                if (regions.get(i).getName().equals(location.getName()))
                {
                    regions.remove(i);
                }
            }
        }
    }

    /**
     * Tells the classes interacting with the list if it is empty or not
     * @return  boolean showing if list is empty or not
     */
    public boolean isListEmpty()
    {
        return regions.isEmpty();
    }

    /**
     * Extract the entire list of locations
     *
     * @return the ArrayList of locations
     */
    public ArrayList<Location> getLocationList()
    {
        return regions;
    }
}//End of LocationList
