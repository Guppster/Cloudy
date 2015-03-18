package team21.Cloudy;

import sun.security.util.BitArray;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * @author: Gurpreet
 */
public class Configuration
{
    /**
     * * Fields ***
     */
    private Preferences prefs;            //User specific preferences
    private LocationList locations;    //List of locations
    private tempUnits degrees;            //Preferred units
    private boolean[] viewObject;
    private static String tempUnit;
    private static String windUnit;
    private static String humidUnit;
    private static String pressureUnit;


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

        //set the units
        if (degrees.equals(tempUnits.METRIC))
        {
            tempUnit = " °C";
            windUnit = " m/s";
            pressureUnit = " kPa";
        } else
        {
            tempUnit = " °F";
            windUnit = " mph";
            pressureUnit = " ksi";
        }
        humidUnit = " %";


    }

    /**
     * Default Constructor to retrieve data
     */
    public Configuration()
    {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    /**
     * General Constructor to make basic configuration object
     *
     * @param locations list of locations
     */
    public Configuration(LocationList locations)
    {
        prefs = Preferences.userRoot().node(this.getClass().getName());
        this.locations = locations;
        viewObject = new boolean[10]; //Assuming there are 10 viewable objects
        degrees = tempUnits.METRIC;
        tempUnit = " °C";
        windUnit = " m/s";
        pressureUnit = " kPa";
        humidUnit = " %";

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

        /*
        try
        {
            locations.setLocationList(byteArrayToLocations(prefs.getByteArray("locations", locationBytes)));
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        */

        units = prefs.get("tempUnits", "IMPERIAL");

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
     * store the locations information into byteArray
     *
     * @return the byteArray
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
     * convert those bytes in byteArray to locations and store them in a new array
     *
     * @param bytes
     * @return new array with the locations
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
     * create new BitArray for viewable objects
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

    ////get locations from location list////
    public LocationList getLocations()
    {
        return locations;
    }

    ////set locations in location list////
    public void setLocations(LocationList locations)
    {
        this.locations = locations;
    }

    ////get viewable object(as boolean)////
    public boolean[] getViewObject()
    {
        return viewObject;
    }

    ////set viewable objects(as boolean)////
    public void setViewObject(boolean[] viewObject)
    {
        this.viewObject = viewObject;
    }

    ////get the degrees with proper units////
    public tempUnits getDegrees()
    {
        return degrees;
    }

    ////set degrees with proper units////
    public void setDegrees(tempUnits degrees)
    {
        this.degrees = degrees;
    }

    public String getTempUnit()
    {
        return tempUnit;
    }

    public String getWindUnit()
    {
        return windUnit;
    }

    public String getHumidUnit()
    {
        return humidUnit;
    }

    public String getPressureUnit()
    {
        return pressureUnit;
    }

}//End of Configuration
