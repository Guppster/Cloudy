package team21.Cloudy;

import sun.security.util.BitArray;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
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
        //Initalize the objects in this class
        prefs = Preferences.userRoot().node(this.getClass().getName());
        this.locations = locations;
        this.degrees = degrees;
        this.viewObject = viewObject;

        updateUnits();
        humidUnit = "%";
    }

    /**
     * Default Constructor to retrieve data
     */
    public Configuration()
    {
        //Initalize the objects in this class
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    /**
     * General Constructor to make basic configuration object
     *
     * @param locations list of locations
     */
    public Configuration(LocationList locations)
    {
        //Initalize the objects in this class if only a location is given
        prefs = Preferences.userRoot().node(this.getClass().getName());

        //If there is a save present load it, else create new config
        if(exists())
        {
            load();
        }
        else
        {
            this.locations = locations;
            viewObject = new boolean[10]; //Assuming there are 10 viewable objects
            degrees = tempUnits.METRIC;
        }
        updateUnits();


        humidUnit = "%";
    }

    /**
     * Updates all the unit strings based on the degrees variable
     */
    private void updateUnits()
    {
        //set the units
        if (degrees.equals(tempUnits.METRIC))
        {
            tempUnit = "°C";
            windUnit = "m/s";
            pressureUnit = "kPa";
        } else
        {
            tempUnit = "°F";
            windUnit = "mph";
            pressureUnit = "ksi";
        }
    }

    /**
     * Checks if preferences exist
     * @return boolean stating if config exists
     */
    public boolean exists()
    {
        try
        {
            return Preferences.userRoot().nodeExists(this.getClass().getName());
        } catch (BackingStoreException e)
        {
            System.out.println("Error: Unable to find Preferences Node");
        }
        return false;
    }//End of exists method

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
        //Declare and initialize temp variables
        byte[] locationBytes = new byte[50];
        byte[] viewableBytes = new byte[10];
        String units;

        try
        {
            //Get locations from config
            locationBytes = prefs.getByteArray("locations", locationBytes);

            LocationList tempList = new LocationList(byteArrayToLocations(locationBytes));

            locations = tempList;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        //Get units from config
        units = prefs.get("tempUnits", "IMPERIAL");

        //Parse the units
        switch (units)
        {
            case "IMPERIAL":
                degrees = degrees.IMPERIAL;
                break;
            case "METRIC":
                degrees = degrees.METRIC;
        }

        //Get view object
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
        String read;

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);

        while (in.available() > 0)
        {
            read = in.readUTF();
            if(!read.equals("mars"))
            {
                arr.add(new Location(read));
            }
        }

        return arr;
    }

    /**
     * *
     * create new BitArray for viewable objects
     *
     * @return byte array extracted from viewableObjects
     */
    private byte[] viewableObjectsToByteArray()
    {
        BitArray bits = new BitArray(viewObject);
        return bits.toByteArray();
    }

    /**
     * *
     * Parses the byte array and sets 1s to true and 0s to false and returns boolean array
     * @param bytes
     * @return boolean array parsing byte array
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
        //set the units
        if (degrees.equals(tempUnits.METRIC))
        {
            return " °C";
        } else
        {
            return " °F";
        }
    }

    public String getWindUnit()
    {
        updateUnits();
        return windUnit;
    }

    public String getHumidUnit()
    {
        return humidUnit;
    }

    public String getPressureUnit()
    {
        updateUnits();
        return pressureUnit;
    }

}//End of Configuration
