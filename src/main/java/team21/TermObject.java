package team21;

/**
 * @author: Gurpreet
 */
public abstract class TermObject 
{
    protected BaseData[] data;

    protected TermObject(int size)
    {
        data = new BaseData[size];
    }//End of constructor

    //Fill in data field with a specific type of BaseData, this changes the type of BaseData to the specific type. Each TermObject has to implement this method for its specfic data.
    protected abstract void parseTermData();
}//End of TermObject abstract class
