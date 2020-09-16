

/**
 * This class is designed for Vehicle
 * @author Jing Dong
 * @author Ran Yan
 *
 */
public class Vehicle{
	public int size;
	public int x;
	public int y;
	public String Orientation;
	public String moveInfo;
	
	
	
	
	/**
	 * The constructor for vehicle
	 * @param x the x coordinate of the vehicle, which starts from the most left grid of the vehicle
	 * @param y the y coordinate of the vehicle, which starts from the most bottom grid of the vehicle
	 * @param size the size of the vehicle
	 * @param Orientation the orientation of the vehicle
	 */
	public Vehicle(int x, int y, int size, String Orientation)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.Orientation = Orientation;

	}
	
	

	
	
	/**
	 * This function lets the vehicle to move up
	 * 
	 */
	public void moveUp()
	{
		this.y ++;
		this.moveInfo = "move up";
	}
	
	/**
	 * This function lets the vehicle to move down
	 * 
	 */
	public void moveDown()
	{
		this.y --;
		this.moveInfo = "move down";
	}
	
	/**
	 * This function lets the vehicle to move right
	 * 
	 */
	public void moveRight()
	{
		this.x ++;
		moveInfo = "move right";
	}
	
	/**
	 * This function lets the vehicle to move left
	 * 
	 */
	public void moveLeft()
	{
		this.x--;
		moveInfo = "move left";
	}
	
	
	//
	
	/**
	 * This is the copy method of a Vehicle
	 * @param v the Vehicle needs to be copied
	 * @return the new copy of the Vehicle v
	 */
	public Vehicle copy(Vehicle v)
	{
		return new Vehicle(v.x, v.y, v.getSize(), v.getOrientation());
		
	}
	
	//
	
	
//	/**
//	 * Getter for the move information
//	 * @return the move information of the 
//	 */
//	
//	public String getMoveInfo ()
//	{
//		return moveInfo;
//	}
//		
	//
	
	/**
	 * Getter for the size of the Vehicle
	 * @return the size of the Vehicle
	 */
	public int getSize()
	{
		return this.size;
	}
	
	
	//
	
	/**
	 * Getter for the orientation of the Vehicle
	 * @return the orientation of the Vehicle
	 */
	public String getOrientation()
	{
		return this.Orientation;
	}

	
	//
	
	/**
	 * This function checks whether this Vehicle is the same as Vehicle v
	 * @param v the Vehicle needs to be checked
	 * @return boolean, true if the vehicles are the sames, false if not
	 */
	public boolean equals(Vehicle v)
	{
		return (this.x==v.x && this.y == v.y && this.size==v.getSize() && this.Orientation.equals(v.getOrientation()));
	}
	
}
