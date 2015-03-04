package team21;

/**
 * Abstract Class storing an array of classes, each of which store the specific data at a given time or day
 *
 * @author: Gurpreet
 */
public abstract class TermObject
{
    ///// Attribute //////
    protected BaseData[] data;

    ///// Constructor /////
    protected TermObject(int size)
    {
        data = new BaseData[size];

    }//End of constructor

    //Fill in data field with a specific type of BaseData, this changes the type of BaseData to the specific type. Each TermObject has to implement this method for its specfic data.
    protected abstract void parseTermData();
}//End of TermObject abstract class
