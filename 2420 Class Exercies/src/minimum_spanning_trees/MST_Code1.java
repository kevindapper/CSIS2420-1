package minimum_spanning_trees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KruskalMST;

public class MST_Code1 {

	public static void main(String[] args) {		
		// create EWG
		In in = new In("src/minimum_spanning_trees/GraphDorm.txt");
		EdgeWeightedGraph internetEWG = new EdgeWeightedGraph(in);
		
		// create Kruskal MST
		KruskalMST internetGraph = new KruskalMST(internetEWG);

		System.out.print("Dorms that need to be connected by fiber: ");
		for (Edge el: internetGraph.edges()){
			// How do I only print the edge and not the weight?
			if (el.either() != 2) {
				int first = el.either();
				System.out.print(first + "-" + el.other(first) + " ");				
			}
		}
		System.out.println();
		
		System.out.print("Dorms that need a router: ");
		for (Edge el: internetGraph.edges()) {
			if (el.either() == 2) {
				System.out.print(el.other(2) + " ");
			}
		}
		System.out.println();		
		
		System.out.printf("Total Cost: $%.2f", internetGraph.weight());

	}


}
