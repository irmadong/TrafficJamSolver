import java.util.ArrayList;

/**
 * This class the the execution class which only contains the main method 
 * @author Jing Dong
 * @author Ran Yan
 *
 */
public class run {
	public static void main(String args[])
	{	
		
		
		System.out.println("---------------------------------------------");	
		System.out.println("The following is Initial State A");
		System.out.println("");
		//Construct state A
		Vehicle RedA = new Vehicle(5,1,2,"Vertical");
		Vehicle GreyA1 = new Vehicle(2,1,3,"Horizontal");
		Vehicle GreenA1 = new Vehicle(0,2,2,"Vertical");
		Vehicle GreenA2 = new Vehicle(1,3,3,"Horizontal");
		Vehicle BlueA1 = new Vehicle(4,3,2,"Horizontal");
		Vehicle BlueA2 = new Vehicle(3,4,2,"Vertical");
		Vehicle YellowA = new Vehicle(4,4,2,"Horizontal");
		Vehicle GreyA2 = new Vehicle(4,5,2,"Horizontal");
		ArrayList<Vehicle> CarsA = new ArrayList();
		CarsA.add(RedA);
		CarsA.add(GreyA1);
		CarsA.add(GreenA1);
		CarsA.add(GreenA2);
		CarsA.add(BlueA1);
		CarsA.add(BlueA2);
		CarsA.add(YellowA);
		CarsA.add(GreyA2);

		
		
		State initialStateA1 = new State(CarsA, 0, "InitialState",false);
		
		
		TrafficJamSolver A_1 = new TrafficJamSolver();
		State goalA1 = A_1.AStarSearch(initialStateA1);

		A_1.getOptimalPath(A_1.parent_and_child, goalA1, initialStateA1);
		A_1.printPath();
		System.out.println("InitialState A"+ " Visited h1 " + A_1.visited.size());
		System.out.println("The cost of the optimal path is " + (A_1.optimalPath.size()-1));
		System.out.println("-----------------------------------------------------------");
		
		State initialStateA2 = new State(CarsA, 0, "InitialState",true);
		
		
		TrafficJamSolver A_2 = new TrafficJamSolver();
		State goalA2 = A_2.AStarSearch(initialStateA1);

		A_2.getOptimalPath(A_2.parent_and_child, goalA2, initialStateA2);
		A_2.printPath();
		
		System.out.println("InitialState A"+ " Visited h2 " + A_2.visited.size());
		System.out.println("The cost of the optimal path is " + (A_2.optimalPath.size()-1));
				
		
		
	System.out.println("---------------------------------------------");	
	System.out.println("The following is Initial State B");
	System.out.println("");
	
	Vehicle B0 = new Vehicle(4,1,2,"Vertical");
	Vehicle B1 = new Vehicle(4,3,2,"Horizontal");
	Vehicle B2 = new Vehicle(2,3,2,"Horizontal");
	Vehicle B3 = new Vehicle(1,3,3,"Vertical");
	Vehicle B4 = new Vehicle(0,3,3,"Vertical");
	Vehicle B5 = new Vehicle(0,2,2,"Horizontal");
	Vehicle B6 = new Vehicle(2,0,2,"Horizontal");
	Vehicle B7 = new Vehicle(2,4,2,"Vertical");
	Vehicle B8 = new Vehicle(3,4,3,"Horizontal");
	Vehicle B9 = new Vehicle(3,5,3,"Horizontal");
	ArrayList <Vehicle> CarsB = new ArrayList();
	CarsB.add(B0);
	CarsB.add(B1);
	CarsB.add(B2);
	CarsB.add(B3);
	CarsB.add(B4);
	CarsB.add(B5);
	CarsB.add(B6);
	CarsB.add(B7);
	CarsB.add(B8);	
	CarsB.add(B9);
		
	
	
	State initialStateB1 = new State(CarsB, 0, "InitialState",false);
	
	
	TrafficJamSolver B_1 = new TrafficJamSolver();
	State goalB1 = B_1.AStarSearch(initialStateB1);

	B_1.getOptimalPath(B_1.parent_and_child, goalB1, initialStateB1);
	B_1.printPath();
	System.out.println("InitialState  B "+ " Visited h1 " + B_1.visited.size());
	System.out.println("The cost of the optimal path is " + (B_1.optimalPath.size()-1));
	System.out.println("-----------------------------------------------------------");
	
	State initialStateB2 = new State(CarsB, 0, "InitialState",true);
	
	
	TrafficJamSolver B_2 = new TrafficJamSolver();
	State goalB2 = B_2.AStarSearch(initialStateB1);

	B_2.getOptimalPath(B_2.parent_and_child, goalB2, initialStateB2);
	B_2.printPath();
	
	System.out.println("InitialState B "+ " Visited h2 " + B_2.visited.size());
	System.out.println("The cost of the optimal path is " + (B_2.optimalPath.size()-1));
			
	
		
	System.out.println("---------------------------------------------");	
	System.out.println("The following is Initial State C");
	System.out.println("");
		
	Vehicle C0 = new Vehicle(5,2,2,"Vertical");
	Vehicle C1 = new Vehicle(3,1,3,"Horizontal");
	Vehicle C2 = new Vehicle(2,2,3,"Horizontal");
	Vehicle C3 = new Vehicle(1,1,3,"Vertical");
	Vehicle C4 = new Vehicle(0,0,3,"Vertical");
	Vehicle C5 = new Vehicle(2,3,3,"Vertical");
	Vehicle C6 = new Vehicle(3,3,2,"Vertical");
	Vehicle C7 = new Vehicle(3,5,3,"Horizontal");
	Vehicle C8 = new Vehicle(4,4,2,"Horizontal");
	ArrayList<Vehicle> CarsC = new ArrayList();
	CarsC.add(C0);
	CarsC.add(C1);
	CarsC.add(C2);
	CarsC.add(C3);
	CarsC.add(C4);
	CarsC.add(C5);
	CarsC.add(C6);
	CarsC.add(C7);
	CarsC.add(C8);
	
	TrafficJamSolver C_1 = new TrafficJamSolver();
	State initialStateC1 = new State(CarsC, 0, "InitialState",false);
	
	
	State goalC1 = C_1.AStarSearch(initialStateC1);
	
	C_1.getOptimalPath(C_1.parent_and_child, goalC1, initialStateC1);
	C_1.printPath();
	System.out.println("InitialState C "+ " Visited h1 " + C_1.visited.size());
	System.out.println("The cost of the optimal path is " + (C_1.optimalPath.size()-1));
	System.out.println("-----------------------------------------------------------");

	TrafficJamSolver C_2 = new TrafficJamSolver();
	State initialStateC2 = new State (CarsC,0,"InitialState",true);
	
	
	State goalC2 = C_2.AStarSearch(initialStateC2);
	
	C_2.getOptimalPath(C_2.parent_and_child, goalC2, initialStateC2);
	C_2.printPath();
	System.out.println("InitialState C "+ " Visited h2 " + C_2.visited.size());
	System.out.println("The cost of the optimal path is " +(C_2.optimalPath.size()-1));
	System.out.println("-----------------------------------------------------------");

	
	
	
	
	
	}

}
