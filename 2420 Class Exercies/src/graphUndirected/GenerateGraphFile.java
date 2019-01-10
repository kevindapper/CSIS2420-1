package graphUndirected;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;

public class GenerateGraphFile {

	public static void main(String[] args) {
		Graph g = GraphGenerator.simple(9, 0.3);

		System.out.println(g.V()); // Cap V is not a good naming convention to use.
		System.out.println(g.E());
		
		for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v))
            	if (w > v)
            		System.out.println(v + " " + w);
		}
		
	}

}
