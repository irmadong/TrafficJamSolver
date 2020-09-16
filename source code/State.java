
import java.util.ArrayList;


/**
 * This class if for State which represents each state while AI solving the problem
 * @author Jing Dong
 * @author Ran Yan
 *
 */

public class State {

	ArrayList<Vehicle> vehicles;
	int g; // actual cost
	int h; // herustic function    
	int f; // total cost 
	ArrayList<State> childrenstates;
	String moveinformation;
	final int gridmax =6 ;

	//for heuristic function 2
	int stuckCarIndex;
	boolean h2 = false;




	/**
	 * The constructor for State class
	 * @param vehicles The arraylist of Vehicle that stores all vehicles for that state
	 * @param g The actual cost from initial state to that  state 
	 * @param moveinfo The move information that indicates from the parent state which car moves to where 
	 * @param h2 The boolean argument, is h2 is true, it means it is using the advanced heuristic function 
	 * 		  else it uses the basic heuristic function
	 */
	public State(ArrayList<Vehicle> vehicles,int g, String moveinfo, boolean h2)
	{
		this.vehicles = vehicles;
		childrenstates = new ArrayList();


		this.moveinformation = moveinfo;

		int stuckCarIndex = -1;

		//choose heuristic function
		this.h2 = h2;
		if(h2)
		{
			h = herustic2(vehicles.get(0));
		}
		else{
			h = herustic(vehicles.get(0));
		}
		this.g=g;
		f = g+h; // calculate the total cost
	} 

	/**
	 *  This function produces the children states which means all possible states when there is one action from the parent state
	 *  The function iterates every vehicles and check whether the vehicle can move up/down or can move left/right
	 *  if so, create a new state with the updated vehicle's action
	 *  else just continue the check work 
	 */
	public void produceChildrenStates()
	{

		//Loop over each car in the state 
		for(Vehicle car : vehicles) {
			String updatedinfo = "";

			if (car.getOrientation() == "Horizontal")// Check if the car is horizontal  
			{

				/*If the car can move left, then move left and produce the new state and updated the moveinformation */
				if(canMoveLeft(car)) 
				{

					ArrayList<Vehicle> updatedVehicles = new ArrayList<Vehicle>();

					/* Create a new state by copying each vehicles and move one valid car  */
					for (int i =0; i< vehicles.size(); i++)
					{
						Vehicle newvehicle= vehicles.get(i).copy(vehicles.get(i)); // copy each vehicle 


						if(vehicles.get(i).equals(car))
						{
							newvehicle.moveLeft();
							updatedinfo = "Vehicle " + i + " move left ";

						}
						updatedVehicles.add(newvehicle);  //create a new arraylist with the updated vehicle
						//and other vehicles' coordinate the same as before 

					}
					State child = new State(updatedVehicles, this.g+1,updatedinfo,this.h2);
					childrenstates.add(child);

				}

				/*If the car can move right, then move left and produce the new state and updated the moveinformation */
				if(canMoveRight(car))
				{

					ArrayList<Vehicle> updatedVehicles = new ArrayList<Vehicle>();
					for (int i =0; i< vehicles.size(); i++)
					{
						Vehicle newvehicle= vehicles.get(i).copy(vehicles.get(i));
						if(vehicles.get(i).equals(car))
						{
							newvehicle.moveRight();

							updatedinfo = "Vehicle " + i + " move right";
						}
						updatedVehicles.add(newvehicle);

					}
					State child = new State(updatedVehicles,this.g+1, updatedinfo,this.h2); // who is this ? can be a bug
					childrenstates.add(child);

				}

			}
			else   //When the car is vertical 
			{


				/*If the car can move up, then move left and produce the new state and updated the moveinformation */
				if(canMoveUp(car))
				{

					ArrayList<Vehicle> updatedVehicles = new ArrayList<Vehicle>();
					for (int i =0; i< vehicles.size(); i++)
					{
						Vehicle newvehicle= vehicles.get(i).copy(vehicles.get(i)); // updated version

						if(vehicles.get(i).equals(car))
						{
							newvehicle.moveUp();
							updatedinfo = "Vehicle " + i + " move up ";
						}
						updatedVehicles.add(newvehicle);


					}
					State child = new State(updatedVehicles,this.g+1, updatedinfo,this.h2);
					childrenstates.add(child);
				}

				/*If the car can move left, then move left and produce the new state and updated the moveinformation */
				if(canMoveDown(car))
				{


					ArrayList<Vehicle> updatedVehicles = new ArrayList<Vehicle>();
					for (int i =0; i< vehicles.size(); i++)
					{
						Vehicle newvehicle= vehicles.get(i).copy(vehicles.get(i)); // updated version

						if(vehicles.get(i).equals(car))
						{
							newvehicle.moveDown();
							updatedinfo = "Vehicle " + i + " move down";

						}
						updatedVehicles.add(newvehicle);


					}
					State child = new State(updatedVehicles,this.g+1,updatedinfo,this.h2);
					childrenstates.add(child);
				}

			}
		}

	}



	
	/**
	 * Check whether the vehicle in that state can move up
	 * @param v the vehicle needs to be checked
	 * @return boolean statement, true if it can move up, false if it can not
	 */
	public boolean canMoveUp(Vehicle v)
	{

		if((v.y+v.getSize()<gridmax) && (!stuck(v.x,v.y+v.getSize()))) //horizontal starts from the bottom point  
		{

			return true;
		}

		return false;
	}

	/**
	 * Check whether the vehicle in that state can move down
	 * @param v the vehicle needs to be checked
	 * @return boolean statement, true if it can move up, false if it can not
	 */
	public boolean canMoveDown(Vehicle v)
	{

		if ((v.y-1>=0) && (!stuck(v.x,v.y-1)))//
		{
			return true;
		}

		return false;
	}
	
	/**
	 * Check whether the vehicle in that state can move left
	 * @param v the vehicle needs to be checked
	 * @return boolean statement, true if it can move up, false if it can not
	 */
	public boolean canMoveLeft(Vehicle v)
	{

		if((v.x-1>=0) && !(stuck(v.x-1,v.y))) // vertical count from the left most point  
		{
			return true;
		}

		return false;
	}

	
	/**
	 * Check whether the vehicle in that state can move right
	 * @param v the vehicle needs to be checked
	 * @return boolean statement, true if it can move up, false if it can not
	 */
	public boolean canMoveRight(Vehicle v)
	{


		if((v.x+v.getSize()<gridmax) && (!stuck(v.x+v.getSize(),v.y)))
		{
			return true;
		}  
		return false;
	}



	/**
	 * Check whether there is car stuck on the space of (x,y)
	 * @param x the coordinate of x
	 * @param y the coordinate of y 
	 * @return boolean statement, true is there is a car stuck, false is there are no cars stuck 
	 */
	public boolean stuck(int x, int y)
	{

		/*Check whether there is any vehicle whose coordinate 
		 * from the head of the car to the end of the car that occupied the specific (x,y)
		 */
		for(int i =0; i < this.vehicles.size(); i++)
		{
			Vehicle car = this.vehicles.get(i);
			int vx = car.x;
			int vy = car.y;
			//            

			if (this.vehicles.get(i).getOrientation().equals("Horizontal")){  //if it is horizontal 


				if(x>=vx && x<=vx +car.getSize()-1 && y==vy)
				{
					stuckCarIndex = i;
					return true;
					//Store the index of the stucking car occupying the current space

				}

			}
			else //if it is vertical
			{
				if(y>=vy && y<=vy+car.getSize()-1 && x ==vx)
				{
					stuckCarIndex = i;
					return true;

				}
			}
		}
		// for each cars, check whether it stuck on the specific coordinate
		return false;
	}



	
	
	/**
	 * Basic herustic function that only checks whether there are any cars stuck from the red car to the door
	 * @param red the red car 
	 * @return the cost of herustic function
	 */
	public int herustic(Vehicle red)
	{
		int count = 0;
		int redx = red.x;
		int redy = red.y;
		for(int i =redy+red.getSize(); i < gridmax; i++)
		{
			if(stuck(redx,i))
			{
				count ++;
			}
		}
		// check the number of cars blocking the red cars
		return count;
	}


	/*advanced herustic function*/
	
	/**
	 * The advanced herustic function that check the total number of the car blocking the red car to the door 
	 * and the related cars blocking those cars to move 
	 * @param red
	 * @return
	 */
	public int herustic2(Vehicle red)
	{
		
		ArrayList<Vehicle> stuckCars = new ArrayList();
		int redx = red.x;
		int redy = red.y;
		//store the cars that stuck red car at the same column
		for(int i =redy+red.getSize()-1; i < gridmax; i++)
		{
			if(stuck(redx,i))
			{
				//add stuck cars at the same column
				stuckCars.add(vehicles.get(stuckCarIndex));   			
			}
		}

		/*
		 * for each cars in the stuck cars: check which car stucks it. If so, add it
		 * to the list. If there is a same car in the list, do not add it. end until every 
		 * car in the list is checked (update the list for each iteration)
		 */
		int size = stuckCars.size();
		for(int index = 0; index < size; index++)
		{

			//first check if c is horizontal or vertical
			//then check if the potential moving space is stuck
			Vehicle c = stuckCars.get(index);

			if (c.getOrientation().equals("Horizontal"))
			{                    
				//check if the horizontal two sides are stuck
				if(stuck(c.x-1,c.y))
				{

					//if the stucking car at the left is not in the list, add it to list
					if(!stuckCars.contains(vehicles.get(stuckCarIndex)))
					{
						stuckCars.add(vehicles.get(stuckCarIndex));
					}   	    		  			
				}
				if(stuck(c.x + c.getSize(),c.y))
				{
					//if the stucking car at the right is not in the list, add it to list
					if(!stuckCars.contains(vehicles.get(stuckCarIndex)))
					{
						stuckCars.add(vehicles.get(stuckCarIndex));  						
					}  			
				}
			}
			else 
			{               	
				//check if the vertical two sides are stuck
				if(stuck(c.x,c.y-1))
				{   					
					//if the stucking car at the left is not in the list, add it to list
					if(!stuckCars.contains(vehicles.get(stuckCarIndex)))
					{
						stuckCars.add(vehicles.get(stuckCarIndex));
					} 			
				}
				if(stuck(c.x,c.y+c.getSize()))
				{
					//if the stucking car at the right is not in the list, add it to list
					if(!stuckCars.contains(vehicles.get(stuckCarIndex)))
					{
						stuckCars.add(vehicles.get(stuckCarIndex));
					}    			
				}
			}

		}


		// check the number of cars blocking the red cars
		return stuckCars.size();
		
	}



	
	
	
	/**
	 * The function checks whether the current state is in the goal state
	 * @return boolean statement true if it is the goal state and false if it is not
	 */
	public boolean isGoal()
	{


		// check whether it is the goalstate --> the red car is near the door 
		return vehicles.get(0).y+vehicles.get(0).getSize() == gridmax;
	}

	public String toString(String s)
	{
		return moveinformation;
	}


	//Getter


	
	/**
	 * Getter of all vehicles in the state 
	 * @return the arraylist of all vehicles in the state 
	 */
	public ArrayList<Vehicle> getVehicles()
	{
		return vehicles;
	}

	//
	
	/**
	 * Getter for heuristic function cost 
	 * @return the 
	 */
	public int getH()
	{
		return h;
	}

	
	/**
	 * Getter for g , the actual cost 
	 * @return the actual cost 
	 */
	public int getG()
	{
		return g;
	}


	
	/**
	 * Getter for the total cost : f
	 * @return f the total cost
	 */
	public int getF()
	{
		return f;
	}
//
	
	/**
	 * Getter for all the children states 
	 * @return the arraylist of all children states 
	 */
	public ArrayList<State> getChildrenStates()
	{
		return childrenstates;
	}


	
	
	/**
	 * Getter for the move information
	 * @return String that is the move information for each step 
	 */
	public String getInfo()
	{
		return this.moveinformation;
	}
	
	
	
	/**
	 * This method is used for print the location of each car which is commonly used in the tester
	 * 
	 */
	
	public String toString()
	{
		for(int i = 0; i< vehicles.size(); i++)
		{
			System.out.println("Car "+ i + "is on " + "("+ vehicles.get(i).x + ","+ vehicles.get(i).y+")");
		}
		return "";
	}

	
	
	
	
	/**
	 * The copy method that copy each vehicles in the arraylist and create an identical arraylist of the original one
	 * @param vs the original arraylist that need to be copied
	 * @return The new copy of the original arraylist 
	 */
	public ArrayList<Vehicle> copyVehicles(ArrayList<Vehicle> vs)
	{
		ArrayList<Vehicle> newVehicles = new ArrayList();
		for(int i =0; i<vehicles.size();i++)
		{
			newVehicles.add(vehicles.get(i));
		}
		return newVehicles;
	}

	//
	
	/**
	 * THe copy method that copy the state 
	 * @param o the state needs to be copied 
	 * @return the new copy of the state 
	 */
	public State copyState(State o)
	{
		State temp = new State (o.getVehicles(), o.g,o.getInfo(),o.h2);
		return temp;
	}
	
	//
	
	/**
	 * This function checks whether this state is the same as State o
	 * @param o the State needs to be tested
	 * @return boolean, true if they are the same, false if they are not 
	 */
	public boolean equals(State o)
	{
		ArrayList<Vehicle> comparedVehicles = o.getVehicles();
		for (int i =0; i < vehicles.size(); i++)
		{
			if(!(comparedVehicles.get(i)).equals(vehicles.get(i)))
			{
				return false;
			}

		}
		return true;
	}
}
