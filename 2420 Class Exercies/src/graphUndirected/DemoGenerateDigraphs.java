package graphUndirected;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DigraphGenerator;
import edu.princeton.cs.algs4.StdOut;

public class DemoGenerateDigraphs {

	public static void main(String[] args) {
		Digraph G = DigraphGenerator.simple(9, 15);
		StdOut.println(G);
		
		G = DigraphGenerator.binaryTree(10);
		StdOut.println(G);
		
		G = DigraphGenerator.rootedOutDAG(8, 13);
		StdOut.println(G);
		
		G = DigraphGenerator.rootedInDAG(8, 13);
		StdOut.println(G);
		
	}

}
