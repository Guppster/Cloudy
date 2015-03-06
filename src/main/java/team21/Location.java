package team21;

/**
 * Class grouping the various forecast of a specific location.
 *
 * @author: Gurpreet
 */
public class Location
{
    /**** Fields ****/
    private String name;
    private TermObject shortTerm; //
    private TermObject longTerm;
    private TermObject currentTerm;

    /**
     * Specific Constructor
     *
     * @param name        location
     * @param shortTerm   link to short term forecast
     * @param longTerm    link to long term forecast
     * @param currentTerm link to current forecast
     */
    public Location(String name, TermObject shortTerm, TermObject longTerm, TermObject currentTerm)
    {
        this.name = name;
        this.shortTerm = shortTerm;
        this.longTerm = longTerm;
        this.currentTerm = currentTerm;
    }//End of specific constructor

    /**
     * General Constructor
     *
     * @param name location
     */
    public Location(String name)
    {
        this.name = name;
    }//End of general constructor

    /**** Getters and Setters ****/

    public String getName()
    {
        return "";
    }

    public TermObject getShortTerm()
    {
        return shortTerm;
    }

    public TermObject getLongTerm()
    {
        return longTerm;
    }

    public TermObject getCurrentTerm()
    {
        return currentTerm;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setShortTerm(TermObject shortTerm)
    {
        this.shortTerm = shortTerm;
    }

    public void setLongTerm(TermObject longTerm)
    {
        this.longTerm = longTerm;
    }

    public void setCurrentTerm(TermObject currentTerm)
    {
        this.currentTerm = currentTerm;
    }
}//End of Location class
