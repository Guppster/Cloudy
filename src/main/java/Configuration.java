/**
 * @author: Gurpreet
 */
public class Configuration 
{
    private LocationList locations;
    private tempUnits degrees;
    private boolean[] viewObject;

    /**
     * * 
     * @param locations
     * @param degrees
     * @param viewObject
     */
    public Configuration(LocationList locations, tempUnits degrees, boolean[] viewObject)
    {
        this.locations = locations;
        this.degrees = degrees;
        this.viewObject = viewObject;
    }

    /**
     * * 
     * @param locations
     */
    public Configuration(LocationList locations)
    {
        this.locations = locations;
        viewObject = new boolean[10]; //Assuming there are 10 viewable objects
    }

    /**
     * * 
     * @return
     */
    public LocationList getLocations() {
        return locations;
    }

    /**
     * * 
     * @param locations
     */
    public void setLocations(LocationList locations) {
        this.locations = locations;
    }

    /**
     * * 
     * @return
     */
    public boolean[] getViewObject() {
        return viewObject;
    }

    /**
     * *
     * @param viewObject
     */
    public void setViewObject(boolean[] viewObject) {
        this.viewObject = viewObject;
    }

    /**
     * * 
     * @return
     */
    public tempUnits getDegrees()
    {
        return degrees;
    }

    /**
     * *
     * @param degrees
     */
    public void setDegrees(tempUnits degrees)
    {
        this.degrees = degrees;
    }
}//End of Configuration
