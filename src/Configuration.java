/**
 * @author: Gurpreet
 */
public class Configuration 
{
    private LocationList locations;
    private int temp;
    private int direction;
    private int speed;
    private int time;
    private boolean[] viewObject;
    
    //Specific Constructor
    public Configuration(LocationList locations, int temp, int direction, int speed, int time, boolean[] viewObject)
    {
        this.locations = locations;
        this.temp = temp;
        this.direction = direction;
        this.speed = speed;
        this.time = time;
        this.viewObject = viewObject;
    }

    //Default Constructor
    public Configuration(LocationList locations)
    {
        this.locations = locations;
        temp = 1;
        direction = 1;
        speed = 1;
        time = 1;
        viewObject = new boolean[10]; // assuming theres 10 viewable objects
    }

    public LocationList getLocations() {
        return locations;
    }

    public void setLocations(LocationList locations) {
        this.locations = locations;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean[] getViewObject() {
        return viewObject;
    }

    public void setViewObject(boolean[] viewObject) {
        this.viewObject = viewObject;
    }
}
