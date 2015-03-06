package team21;

import sun.security.util.BitArray;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * @author: Gurpreet
 */
public class Configuration
{
    /**** Fields ****/
    private Preferences prefs; 			//User specific preferences
    private LocationList locations; 	//List of locations
    private tempUnits degrees; 			//Preferred units
    private boolean[] viewObject;

    /**
     * Specific Constructor
     *
     * @param locations  list of locations
     * @param degrees    preferred unit of measure
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
     * General Constructor
     *
     * @param locations list of locations
     */
    public Configuration(LocationList locations)
    {
        this.locations = locations;
        viewObject = new boolean[10]; //Assuming there are 10 viewable objects
    }

    /**
     * convert data into binary to save preferences
     *
     * @return whether saving has been successful
     */
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

    /**
     * method used to load data and preferences upon starting up the application
     *
     * @return whether loading was successful
     */
    public boolean load()
    {
        byte[] locationBytes = new byte[10];
        byte[] viewableBytes = new byte[10];
        String units = "";

        try
        {
            locations.setLocationList(byteArrayToLocations(prefs.getByteArray("locations", locationBytes)));
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        prefs.get("tempUnits", units);

        switch (units)
        {
            case "IMPERIAL":
                degrees = degrees.IMPERIAL;
                break;
            case "METRIC":
                degrees = degrees.METRIC;
        }

        viewObject = byteArrayToBoolean(prefs.getByteArray("viewableObjects", viewableBytes));

        return true;
    }

    /**
     * *
     *
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
     *
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
     *
     * @return
     */
    private byte[] viewableObjectsToByteArray()
    {
        BitArray bits = new BitArray(viewObject);
        return bits.toByteArray();
    }

    /**
     * *
     *
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

    ///// Getters and Setters /////

    public LocationList getLocations()
    {
        return locations;
    }

    public void setLocations(LocationList locations)
    {
        this.locations = locations;
    }

    public boolean[] getViewObject()
    {
        return viewObject;
    }

    public void setViewObject(boolean[] viewObject)
    {
        this.viewObject = viewObject;
    }

    public tempUnits getDegrees()
    {
        return degrees;
    }

    public void setDegrees(tempUnits degrees)
    {
        this.degrees = degrees;
    }


}//End of Configuration
