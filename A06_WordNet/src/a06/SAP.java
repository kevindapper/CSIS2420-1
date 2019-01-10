package a06;

/*******************************************************************************
 * Author(s): Caleb Stam, Gerald Brady 
 * Class: CSIS-2420
 * Professor: Margarethe Posch
 * Assignment: A06  Word Net
 ******************************************************************************/

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;

public class SAP {
	private static Digraph graph;
	private int ancestor = -1;
	private int length = -1;
	
	/**
	 * Constructor takes a digraph, does not need to be a DAG
	 * 
	 * @param G
	 */
	public SAP(Digraph G){
		if (G == null)
			throw new NullPointerException("Digraph G can't be null");
		
	    graph = new Digraph(G);
	    
	}

	/**
	 * Checks the digraph to see if it is a directed acyrlic graph (DAG).
	 * Returns true if it is a DAG
	 * 
	 * @return boolean
	 */
	public boolean isDAG(){
		DirectedCycle hasCycle = new DirectedCycle(graph);
		
		return !hasCycle.hasCycle(); 
		
	}

	/**
	 * Checks the digraph to see if it is a rooted DAG.
	 * Returns true if it is a rooted Dag.
	 * 
	 * @return boolean
	 */
	public boolean isRootedDAG(){
		if(!isDAG())
			return false;
		
		// used to find a possible root
		DepthFirstOrder dfo = new DepthFirstOrder(graph);
	    Integer rootV = dfo.post().iterator().next();
		
	    // checking the V to see if they all have a path to the root
	    DepthFirstDirectedPaths dfdp = new DepthFirstDirectedPaths(graph.reverse(), rootV);
	    for(int i = 0; i < graph.V(); i++){
	    	if(!dfdp.hasPathTo(i))
	    		return false;
	    }
	    
	    //all V have a path that goes to the root
	    return true;
		
	}

	/**
	 * Finds the shortest ancestral path between v and w.  This is call the length.
	 * Returns a -1 if there is no such path.
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int length(int v, int w){
		checkNull(v);
		checkNull(w);
				
		Stack<Integer> vStack = new Stack<>();
    	vStack.push(v);
    	Stack<Integer> wStack = new Stack<>();
    	wStack.push(w);
    	
	    lengthAncestor(vStack, wStack);
	    
	    return length;

	}

	/**
	 * Finds the common ancestor of v and w that participates in a shortest ancestral path.
	 * Returns a -1 if there is no such path.
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int ancestor(int v, int w){
		checkNull(v);
		checkNull(w);
		
		Stack<Integer> vStack = new Stack<>();
    	vStack.push(v);
    	Stack<Integer> wStack = new Stack<>();
    	wStack.push(w);
    	
	    lengthAncestor(vStack, wStack);
	    
	    return ancestor;
		
	}

	/**
	 * Finds the shortest ancestral path between any vertex in v and any vertex
	 * in w.  This is call the length.
	 * Returns a -1 if there is no such path.
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		checkNull(v);
		checkNull(w);
		
	    lengthAncestor(v, w);
	    
	    return length;
		
	}

	/**
	 * Finds the common ancestor that participates in the shortest ancestral path.
	 * Returns a -1 if there is no such path.
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		checkNull(v);
		checkNull(w);
				
	    lengthAncestor(v, w);
	    
	    return ancestor;
		
	}
	
	// ******************** Helper Methods ********************
	
	private void checkNull(int v) {
		if (v < 0 || v >= graph.V())
			throw new java.lang.IndexOutOfBoundsException();
	}
	
	private void checkNull(Iterable<Integer> v) {
		for (int w : v) {
			checkNull(w);
		}
	}
	
	private void lengthAncestor(Iterable<Integer> v, Iterable<Integer> w) {
		// used to find paths
		BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(graph, v);
	    BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(graph, w);
	    DepthFirstOrder dfo = new DepthFirstOrder(graph);
	    
	    // setup variables to return -1 if no such path exists, needed for public methods
	    int tempLength;
		
		for (int l : dfo.reversePost()){
			if(vPath.hasPathTo(l) && wPath.hasPathTo(l)){
				tempLength = vPath.distTo(l) + wPath.distTo(l);
				if (tempLength < length || ancestor == -1){
					ancestor = l;
					length = tempLength;
				}
				else break;
			}
		}

	}
	
	// ==================== test client below ====================
	
	public static void main(String[] args) {
		In in = new In("src/res/digraph1.txt"); //(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!in.isEmpty()) {
			int v = 3;
			int w = 1;
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
