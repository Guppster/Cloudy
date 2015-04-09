package team21.Cloudy;

/**
 * Abstract Class storing an array of classes, each of which store the specific data at a given time or day
 *
 * @author: Gurpreet
 */
public abstract class TermObject
{
    /**
     * The array of data storing weather information
     */
    protected BaseData[] data;

    /**
     *
     * @param size The size needed for the array of data
     */
    protected TermObject(int size)
    {
        data = new BaseData[size];

    }//End of constructor

    /**
     * Returns the data array from this object
     * @return The data array from this object
     */
    protected abstract BaseData[] getData();

    /**
     * Fill in data field with a specific type of BaseData,
     * this changes the type of BaseData to the specific type.
     * Each TermObject has to implement this method for its specific data.
     */
    protected abstract void parseTermData();

}//End of TermObject abstract class
