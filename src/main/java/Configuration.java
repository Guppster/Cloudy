import sun.security.util.BitArray;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * @author: Gurpreet
 */
public class Configuration 
{
    private Preferences prefs;
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
        prefs = Preferences.userRoot().node(this.getClass().getName());
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
    
    public boolean save()
    {
        try
        {
            prefs.putByteArray("locations", locationsToByteArray());
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        prefs.put("tempUnits", degrees.name());
        
        prefs.putByteArray("viewableObjects", viewableObjectsToByteArray());
        
        return true;
    }
    
    public boolean load()
    {
        return false;
    }

    /**
     * * 
     * @return
     * @throws IOException
     */
    private byte[] locationsToByteArray() throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        
        for (Location element : locations.getLocationList()) 
        {
            out.writeUTF(element.getName());
        }
        
        return baos.toByteArray();
    }

    /**
     * * 
     * @param bytes
     * @return
     * @throws IOException
     */
    private ArrayList<Location> byteArrayToLocations(byte[] bytes) throws IOException
    {
        ArrayList<Location> arr = new ArrayList<Location>();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);
        
        while (in.available() > 0)
        {
            arr.add(new Location(in.readUTF()));
        }
        
        return arr;
    }

    /**
     * * 
     * @return
     */
    private byte[] viewableObjectsToByteArray()
    {
        BitArray bits = new BitArray(viewObject);
        return bits.toByteArray();
    }

    /**
     * *
     * @param bytes
     * @return
     */
    private boolean[] byteArrayToBoolean(byte[] bytes)
    {
        boolean[] bits = new boolean[bytes.length * 8];
        
        for (int i = 0; i < bytes.length * 8; i++) 
        {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
            {
                bits[i] = true;
            }
        }
        
        return bits;
    }
}//End of Configuration
