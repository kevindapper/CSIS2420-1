package graphUndirected;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DfsVsBfs {
	private static int source = 1; // vertex used to find path 
	
	public static void main(String[] args){
		String file = "src/graphUndirected/GraphExercise06.txt";
		In in = new In(file);
		Graph g = new Graph(in);
		DepthFirstPaths dfp = new DepthFirstPaths(g, source);
		BreadthFirstPaths bfp = new BreadthFirstPaths(g, source);
				
		printAdjacencyList(g);
		printPaths(g, dfp, bfp);
		
	}

	private static void printPaths(Graph g, DepthFirstPaths dfp, BreadthFirstPaths bfp) {
		StdOut.println("Paths DFS:           Shortest Paths BFS:");
		StdOut.println("---------------      -------------------");
		StringBuilder sbDepth = new StringBuilder();
		StringBuilder sbBreadth = new StringBuilder();
		String link = "";
		for (int v = 0; v < g.V(); v++){
			// DFS path
			//TODO this set does not display correctly
			for (int el : dfp.pathTo(v)){
				sbDepth.append(link + el);
				link = "->";
			}link = "";
			
			// BFS path
			for (int el : bfp.pathTo(v)){
				sbBreadth.append(link + el);
				link = "->";
			}
			
			StdOut.printf("%-20s %s", sbDepth, sbBreadth);
			StdOut.println();
			sbDepth.setLength(0);
			sbBreadth.setLength(0);
			link = "";
		}
	}

	private static void printAdjacencyList(Graph g) {
		StdOut.println("Adjacency List:");
		StdOut.println("---------------");
		StringBuilder sb = new StringBuilder();
		String link = "";
		for (int v = 0; v < g.V(); v++){
			sb.append(v + ": ");
			for (int el : g.adj(v)){
				sb.append(link + el);
				link = "->";
			}
			sb.append(System.getProperty("line.separator"));
			link = "";
			
		}StdOut.println(sb);
	}
}
