package a04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 * Author(s): Ethan Darling, Gerald Brady
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A04 8 Puzzle
 * 
 ******************************************************************************/

public class Solver {
	private Board initial;
    private MinPQ<SearchNode> minPQ;
    private Board goalBoard;
    private Stack<SearchNode> boardStack;
	
    /**
     * Creates a board solver using an implementation of the A* algorithm.
     *  
     * @param initial
     */
    public Solver(Board initial){
		if (!initial.isSolvable()) {
			throw new IllegalArgumentException("Board provided is not solvable.");
		}
		if (initial.equals(null)) {
			throw new NullPointerException("Board provided cannot be null.");
		}
		
		this.initial = initial;
		this.minPQ = new MinPQ<>();
		this.boardStack = new Stack<>();
		this.goalBoard = initial.getGoalBoard();
		minPQ.insert(new SearchNode(initial, 1, null));
		
		while (!minPQ.min().board.equals(goalBoard)) { 
			boardStack.push(minPQ.min());
			minPQ.delMin();
			
			Board tempBoard = boardStack.peek().board;
			for (Board el : tempBoard.neighbors()) {
				
				SearchNode temp = boardStack.peek();
				
				if (boardStack.peek().previousNode == null) {
					minPQ.insert(new SearchNode(el, 1 + boardStack.peek().move,
							boardStack.peek()));
				}
				else if (!boardStack.peek().previousNode.equals(temp) 
						&& boardStack.peek().previousNode != null) {
					minPQ.insert(new SearchNode(el, 1 + boardStack.peek().move,
							boardStack.peek()));
				}
				else if (boardStack.peek().previousNode.equals(temp)) {
					continue;
				}
			}
		}		
    }
    
    /**
     * Minimum number of moves to solve initial board
     * 
     * @return
     */
    public int moves(){
    	if (!initial.isSolvable()) {
    		return -1;
    	}
		return boardStack.peek().move;
    	
    }
    
    /**
     * Sequence of boards in a shortest solution
     * 
     * @return
     */
    public Iterable<Board> solution(){
    	if (!initial.isSolvable()) {
    		return null;
    		
    	}
    	
    	Stack<Board> solStack = new Stack<>();
    	SearchNode current = minPQ.min();
    	
    	while(current.previousNode != null) {
    		solStack.push(current.board);
    		current = current.previousNode;
    		
    	}
    	
    	solStack.push(initial);
		return solStack; 
		
    }
    
    // ===================== Additional Classes =====================
    private class SearchNode implements Comparable<SearchNode> {
    	private Board board;
    	private int move;
        private int priority;
        private SearchNode previousNode;
        
        public SearchNode(Board board, int moves, SearchNode previousNode) {
        	this.board = board;
        	this.move = moves;
        	this.previousNode = previousNode;
        	priority = move + board.manhattan();
        	
        }
    	
		@Override
		public int compareTo(SearchNode other) {
			if (this.priority < other.priority) {
				return -1;
			}
			
			if (this.priority > other.priority) {
				return 1;
			}
			
			return 0;
			
		}	
    }
        
    //============ Test Client ============//
	public static void main(String[] args) {
		String file = "src/res/puzzle4x4-19.txt";
		
		/*
		 * Unit Testing from assignment instructions
		 */
	    In in = new In(file);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    
	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }

	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	}
}
