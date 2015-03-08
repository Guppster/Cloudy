package team21;

/**
 * Class grouping the various forecast of a specific location.
 *
 * @author: Gurpreet
 */
public class Location
{
    private String name; //the specific location
    private TermObject shortTerm; //collection of data for short term forecast at the location specified by name
    private TermObject longTerm; //collection of data for long term forecast at the location specified by name
    private TermObject currentTerm; //collection of data for current forecast at the location specified by name
    private boolean nameFlag;

    /**
     * Specific Constructor initialize all attributes
     *
     * @param name        location name
     * @param shortTerm   link to collection of data for short term forecast at the location specified by name
     * @param longTerm    link to collection of data for long term forecast at the location specified by name
     * @param currentTerm link to collection of data for current forecast at the location specified by name
     */
    public Location(String name, TermObject shortTerm, TermObject longTerm, TermObject currentTerm)
    {
        this.name = name;
        nameFlag = false;
        this.shortTerm = shortTerm;
        this.longTerm = longTerm;
        this.currentTerm = currentTerm;
    }//End of specific constructor

    /**
     * General Constructor initialize only the location
     *
     * @param name location
     */
    public Location(String name)
    {
        this.name = name;
    }//End of general constructor

    /**
     * Extract the location name
     *
     * @return the location name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Extract the short-term TermObject
     *
     * @return the short-term TermObject
     */
    public TermObject getShortTerm()
    {
        return shortTerm;
    }

    /**
     * Extract the long-term TermObject
     *
     * @return the long-term TermObject
     */
    public TermObject getLongTerm()
    {
        return longTerm;
    }

    /**
     * Extract the current-term TermObject
     *
     * @return the current-term TermObject
     */
    public TermObject getCurrentTerm()
    {
        return currentTerm;
    }

    /**
     * Update the location name
     *
     * @param name new location
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Update the short-term TermObject
     *
     * @param shortTerm new short-term TermObject
     */
    public void setShortTerm(TermObject shortTerm)
    {
        this.shortTerm = shortTerm;
    }

    /**
     * Update the long-term TermObject
     *
     * @param longTerm new long-term TermObject
     */
    public void setLongTerm(TermObject longTerm)
    {
        this.longTerm = longTerm;
    }

    /**
     * Update the current-term TermObject
     *
     * @param currentTerm new current-term TermObject
     */
    public void setCurrentTerm(TermObject currentTerm)
    {
        this.currentTerm = currentTerm;
    }
}//End of Location class
