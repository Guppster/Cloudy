/**
 * @author: Gurpreet
 */
public class Location 
{
    private String name;
    private TermObject shortTerm;
    private TermObject longTerm;
    private TermObject currentTerm;
    
    public Location(String name, TermObject shortTerm, TermObject longTerm, TermObject currentTerm)
    {
        this.name = name;
        this.shortTerm = shortTerm;
        this.longTerm = longTerm;
        this.currentTerm = currentTerm;
    }//End of specific constructor
    
    public Location(String name)
    {
        this.name = name;
    }//End of general constructor
    
    public String getName()
    {
        return "";
    }
    
    public TermObject getShortTerm()
    {
        return new TermObject() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }

    public TermObject getLongTerm()
    {
        return new TermObject() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }

    public TermObject getCurrentTerm()
    {
        return new TermObject() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
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
