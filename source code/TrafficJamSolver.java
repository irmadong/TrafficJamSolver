
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * This class is the core of our project that is the AI solver of the traffic jam game  
 * @author Jing Dong
 * @author Ran Yan
 *
 */
public class TrafficJamSolver {
	
	//variables
	HashMap<State, State> parent_and_child = new HashMap<State, State>();
	PriorityQueue <State> frontier = new PriorityQueue<State>(1,new PathCostComparator());
	ArrayList<State> visited = new ArrayList<State>();
	ArrayList<State> optimalPath = new ArrayList<State>();
	
	 
	
	
	
	/**
	 * The implementation of AStarSearch 
	 * @param start the start state
	 * @return the Goal state
	 */
	public State AStarSearch(State start)
	{
		
		frontier.add(start);
		
		/* Make sure each visited node has the smallest f cost int based on the current situation  */
		while(!frontier.isEmpty())
		{
			State temp = frontier.poll();
			
			if(!isVisited(temp)) {  // avoid the duplicate in the visited 
			
				if(temp.isGoal())
				{
					
					return temp; //When meet the goal, return the goal
				}
				
				
				visited.add(temp);
				temp.produceChildrenStates();
				
				ArrayList<State> children = temp.getChildrenStates();
				
				
				
				for(State currentState : children)
				{
					
					parent_and_child.put(currentState, temp); // the key as current state and the value as temp
					
						frontier.add(currentState);
					
					
				}
			}
		}
		
		return null;
	}
	
	
	
	
	/**
	 * This function get the optimal path by backtracking from the goial state 
	 * @param family the HashMap whose key is the child and whose value is the child's parent
	 * @param goal the goal state 
	 * @param initial the initial state 
	 */
	public void getOptimalPath(HashMap<State, State>family, State goal, State initial)
	{
		
		
		State temp = goal;
		optimalPath.add(temp.copyState(temp));
		
		
		
		while(family.get(temp)!=null)
		{
			State parent = family.get(temp);
			
			if(parent.equals(initial))
			{
				break;
			}
			optimalPath.add(parent.copyState(parent));
			temp = parent;
			
			
			parent = family.get(temp);
			
			
		}
		optimalPath.add(initial);
		
		
	}
	

	
	/**
	 *  
	 * This function print the optimal path in textual information
	 */
	public void printPath()
	{
		for(int i = optimalPath.size()-1; i>=0; i--)
		{
			System.out.println(optimalPath.get(i).getInfo());
		}
	}
	
	
	
	

	
	/**
	 * This function checks whether the state is visited 
	 * @param o the state needs to be check 
	 * @return boolean, true if it is visited and false if it is not
	 */
	public boolean isVisited(State o)
	{
		for(State v : visited)
		{
			if(v.equals(o)) return true;
		}
		return false;
		//return visited.contains(o);
	}
	
	
	
	
	

}
