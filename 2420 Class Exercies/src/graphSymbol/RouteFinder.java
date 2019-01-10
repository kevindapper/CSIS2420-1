package graphSymbol;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RouteFinder {

	private static SymbolGraph sg;
	private static Graph graph;
	
	public static void main(String[] args) {
		readInFile();		
		
        System.out.println("Done reading src/graphSymbol/routes.txt");
        System.out.println("");

        System.out.print("Destination: ");
        lookUpShortestRoute();
        
	}

	/**
	 * List the shortest paths to all the destinations that can be reached from
	 * the departure airport (must be in file to get destination).
	 */
	private static void lookUpShortestRoute() {
		while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                System.out.println("");
                System.out.println("The following destinations can be reached from " + source + ":\n");
                BreadthFirstPaths shortestPath = new BreadthFirstPaths(graph, s);
                printShortestPath(shortestPath);

            }
            else {
                StdOut.println("Airport could not be found '" + source + "'");
            }
        }
	}

	private static void printShortestPath(BreadthFirstPaths shortestPath) {
		Queue<String> pathQ = new Queue<>();
		String link = "";
		
		for (int v = 0; v < graph.V(); v++){
			for (int d : shortestPath.pathTo(v)){
				pathQ.enqueue(sg.nameOf(d));
			}
			
			System.out.print(sg.nameOf(v) + ": ");
			while (!pathQ.isEmpty()){
				System.out.print(link + pathQ.dequeue());
				link = " ";
			}
			System.out.println("");
			link = "";
		}
	}

	/**
	 *  Reads in a hard coded file named routes.txt and creates a new SymboleGraph and a 
	 *  graph from that file.
	 */
	private static void readInFile() {
		String filename  = "src/graphSymbol/routes.txt";//args[0];
        String delimiter = " "; //args[1];
        sg = new SymbolGraph(filename, delimiter);
        graph = sg.graph();
	}

}