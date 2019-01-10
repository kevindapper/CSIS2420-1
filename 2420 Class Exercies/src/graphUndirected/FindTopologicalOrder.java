package graphUndirected;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class FindTopologicalOrder {

	public static void main(String[] args) {
		In in = new In("src/graphUndirected/res/diGraph2.txt");
		Digraph G = new Digraph(in);
		
		Topological topological = new Topological(G);
		boolean first = true;
		for (int v : topological.order()){
			if (first) first = false;
			else StdOut.print(" -> ");
			StdOut.print(v);
		}
	}
}
