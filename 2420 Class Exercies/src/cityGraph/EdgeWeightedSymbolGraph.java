package cityGraph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedSymbolGraph {
	   private ST<String, Integer> st;  // string -> index
	    private String[] keys;           // index  -> string
	    private EdgeWeightedGraph graph;             // the underlying graph
	    private Edge edge;
	    /**  
	     * Initializes a graph from a file using the specified delimiter.
	     * Each line in the file contains
	     * the name of a vertex, followed by a list of the names
	     * of the vertices adjacent to that vertex, separated by the delimiter.
	     * @param filename the name of the file
	     * @param delimiter the delimiter between fields
	     */
	    public EdgeWeightedSymbolGraph(String filename) {
	        st = new ST<String, Integer>();
	        
			In in = new In(filename);
			while(!in.isEmpty()) {
				String[] a = in.readLine().split(",");
				for(int i = 0; i < a.length - 1; i++) {
					if(!st.contains(a[i])) {
						st.put(a[i], st.size());
					}					
				}
			}
			
	        keys = new String[st.size()];
	        for (String name : st.keys()) {
	            keys[st.get(name)] = name;
	        }

	        graph = new EdgeWeightedGraph(st.size());
	        in = new In(filename);
	        while (in.hasNextLine()) {
	            String[] a = in.readLine().split(",");
	            int v = st.get(a[0]);
				Double weight = Double.parseDouble(a[2]);
	            for (int i = 1; i < a.length - 1; i++) {
	                int w = st.get(a[i]);
	                edge = new Edge(v, w, weight);
	                graph.addEdge(edge);
	            }
	        }
	    }

	    /**
	     * Does the graph contain the vertex named {@code s}?
	     * @param s the name of a vertex
	     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
	     */
	    public boolean contains(String s) {
	        return st.contains(s);
	    }

	    /**
	     * Returns the integer associated with the vertex named {@code s}.
	     * @param s the name of a vertex
	     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
	     * @deprecated Replaced by {@link #indexOf(String)}.
	     */
	    @Deprecated
	    public int index(String s) {
	        return st.get(s);
	    }


	    /**
	     * Returns the integer associated with the vertex named {@code s}.
	     * @param s the name of a vertex
	     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
	     */
	    public int indexOf(String s) {
	        return st.get(s);
	    }

	    /**
	     * Returns the name of the vertex associated with the integer {@code v}.
	     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
	     * @return the name of the vertex associated with the integer {@code v}
	     * @throws IllegalArgumentException unless {@code 0 <= v < V}
	     * @deprecated Replaced by {@link #nameOf(int)}.
	     */
	    @Deprecated
	    public String name(int v) {
	        validateVertex(v);
	        return keys[v];
	    }

	    /**
	     * Returns the name of the vertex associated with the integer {@code v}.
	     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
	     * @throws IllegalArgumentException unless {@code 0 <= v < V}
	     * @return the name of the vertex associated with the integer {@code v}
	     */
	    public String nameOf(int v) {
	        validateVertex(v);
	        return keys[v];
	    }

	    /**
	     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
	     * not to mutate the graph.
	     * @return the graph associated with the symbol graph
	     * @deprecated Replaced by {@link #graph()}.
	     */
	    @Deprecated
	    public EdgeWeightedGraph G() {
	        return graph;
	    }

	    /**
	     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
	     * not to mutate the graph.
	     * @return the graph associated with the symbol graph
	     */
	    public EdgeWeightedGraph graph() {
	        return graph;
	    }

	    // throw an IllegalArgumentException unless {@code 0 <= v < V}
	    private void validateVertex(int v) {
	        int V = graph.V();
	        if (v < 0 || v >= V)
	            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	    }


	    /**
	     * Unit tests the {@code SymbolGraph} data type.
	     *
	     * @param args the command-line arguments
	     */
	    public static void main(String[] args) {
	        String filename  = "src/minSpanningTree/CityConnections.csv";
	        EdgeWeightedSymbolGraph sg = new EdgeWeightedSymbolGraph(filename);
	        EdgeWeightedGraph graph = sg.graph();
	        while (StdIn.hasNextLine()) {
	            String source = StdIn.readLine();
	            if (sg.contains(source)) {
	                int s = sg.indexOf(source);
	                for (Edge e: graph.adj(s)) {
	                    StdOut.println("   " + sg.nameOf(e.either()));
	                }
	            }
	            else {
	                StdOut.println("input not contain '" + source + "'");
	            }
	        }
	    }
	}
