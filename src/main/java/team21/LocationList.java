package team21;

import java.util.ArrayList;

/**
 * Class storing a list of locations.
 *
 * @author: Gurpreet
 */
public class LocationList
{
    private ArrayList<Location> regions; // a list of locations

    /**
     * General Constructor initialize regions with an empty {@ArrayList}
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
        if (!regions.isEmpty())
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

        //return london as default
        return null;

    }

    /**
     * Check if the location exist in the list. It's similar to search list, except it passes a Location argument and returns a boolean value.
     *
     * @param location
     * @return
     */
    public boolean isLocationInList(Location location)
    {
        if (!regions.isEmpty())
        {
            //if the list is not empty, illiterate to see if location exist
            for (int i = 0; i < regions.size(); i++)
            {
                if (regions.get(i).equals(location))
                {
                    return true;
                }
            }
        }
        return false;
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
        if (!regions.isEmpty())
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
     * Extract the entire list of locations
     *
     * @return the ArrayList of locations
     */
    public ArrayList<Location> getLocationList()
    {
        return regions;
    }

    /**
     * Update the entire list of locations
     *
     * @param regions a new list of locations
     */
    public void setLocationList(ArrayList<Location> regions)
    {
        this.regions = regions;
    }
}//End of LocationList
