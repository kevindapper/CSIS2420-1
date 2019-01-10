package cityGraph;


import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.KruskalMST;

public class CityGraph {
	public static void main(String[] args) {
        String filename  = "src/cityGraph/CityConnections.csv";
        EdgeWeightedSymbolGraph sg = new EdgeWeightedSymbolGraph(filename);
        EdgeWeightedGraph graph = sg.graph();
        KruskalMST mst = new KruskalMST(graph);
        
        // Weight of mst
        System.out.println("Weight of Spanning Tree: " + mst.weight()+"\n");
        
        //Connected cities in MST
        System.out.println("    MST connections    ");
        System.out.println("-----------------------");
        for (Edge e : mst.edges()) {
        	System.out.printf("%-10s to %s\n", sg.nameOf(e.either()), sg.nameOf(e.other(e.either())));
        }
	}

}