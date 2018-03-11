/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
	public boolean equals(Location loc)
	{
		 if (loc == null)
		{
            return false;
        }
		if(loc.xCoord==xCoord && loc.yCoord==yCoord)
			return true;
		return false;
	}
	public int hashCode() 
    {
        int hk = 0;
        hk = hk + xCoord;
        hk = hk + yCoord;
        return hk;
    }
}