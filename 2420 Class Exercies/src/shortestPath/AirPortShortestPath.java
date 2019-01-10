package shortestPath;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;

public class AirPortShortestPath {

	public static void main(String[] args) {
		String filename  = "src/shortestPath/airports.txt";
		String delimiter = ",";
		SymbolEdgeWeightedDigraph sg = new SymbolEdgeWeightedDigraph(filename, delimiter);
		DijkstraSP sp = new DijkstraSP(sg.digraph(), 0);
		
		for (DirectedEdge el : sp.pathTo(sg.indexOf("End"))){
//			String start = keys[el.from()];
//			String end = keys[el.to()];
//			
			//System.out.printf("%s to %s\n", start, end);
			
		}
		//wt not working
		System.out.println("Total miles: " + sp.distTo(sg.indexOf("End")));
	}

}
