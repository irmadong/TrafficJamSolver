/*
 *  This class is designed for the comparator of Priority Queue in the rule of ascending cost of the f(n) cost 
 *  which is actual cost + heuristic function cost for each states.
 *  It  means the state with smaller f(n) cost will be extracted first.
 */

import java.util.Comparator;

public class PathCostComparator implements Comparator<State> {
	
	
	/*
	 * The rule for comparator
	 * @parameter s1 the first state 
	 * @parameter s2 the second state
	 */
	
	public int compare(State s1, State s2)
	{
		if(s1.getF()>s2.getF())
		{
			return 1;
		}
		else if (s1.getF() < s2.getF())
		{
			return -1;
		}
		return 0;
	}

}
