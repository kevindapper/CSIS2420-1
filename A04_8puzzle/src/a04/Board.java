package a04;

/*******************************************************************************
 * Author(s): Ethan Darling, Gerald Brady 
 * Class: CSIS-2420
 * Professor: Margarethe Posch
 * Assignment: A04 8 Puzzle
 ******************************************************************************/

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
	private int[][] blocks;
	private List<Integer> oneDBlocks = new ArrayList<>();
	private int zeroRow;
	private Board goalBoard;

	/**
	 * Initializes a new Board of a 2 Dimensional array using the copy method.
	 * 
	 * @param blocks
	 */
	public Board(int[][] blocks) {
		if (blocks == null)
			throw new NullPointerException();

		this.blocks = copy(blocks);
		twoD2oneD(blocks);

	}

	/**
	 * Returns the size of the specified 2 dimensional array.
	 * 
	 * @return
	 */
	public int size() {
		return blocks.length;
		
	}
	
	/**
	 * Returns the number of blocks out of place.
	 * 
	 * @return
	 */
	public int hamming() {
		int counter = 1;
		int hamming = 0;
		
		for (int r = 0; r < size(); r++) {
			for (int c = 0; c < size(); c++) {
				if (blocks[r][c] == counter++ || blocks[r][c] == 0) {
					continue;
				}
				hamming++;
			}
		}
		return hamming;
		
	}

	/**
	 * Returns a counter that represents the Manhattan Distance
	 * 
	 * @return
	 */
	public int manhattan() {
		int counter = 0;

		for (int r = 0; r < size(); r++) {
			for (int c = 0; c < size(); c++) {
				if (blocks[r][c] != 0 && blocks[r][c] != c + r * size() + 1) {
					counter += manhattanDistance(r, c);
				}
			}
		}
		return counter;
		
	}

	/**
	 * Checks if a particular board is equal to the goal board.
	 * 
	 * @return
	 */
	public boolean isGoal() {
		createGoalBoard();
		return blocksEqual(this.blocks, goalBoard.blocks);
		
	}

	/**
	 * Checks if a board is solvable based on it's inversion count.
	 * 
	 * @return
	 */
	public boolean isSolvable() {
		// get inversion count
		int count = invCount();
		
		// if even
		if (size() % 2 == 0){
			return ((count + zeroRow) % 2 == 1);
		}
		else {
			return (count % 2 == 0);
		}
		
	}

	/**
	 * Does this board equal y?
	 * 
	 **/
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		
		Board other = (Board) y;
		
		for (int r = 0; r < size(); r++)
			for (int c = 0; c < size(); c++)
				if (other.blocks[r][c] != blocks[r][c])
					return false;

		return true;

	}

	/**
	 * Returns the neighbors of a particular blank space in the board.
	 * 
	 * @return
	 */
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<>();
		
		for (int r = 0; r < size(); r++){
			for (int c = 0; c < size(); c++){
				if (blocks[r][c] == 0){
					if (c > 0)
						neighbors.push(checkNeighbors(r, c,  0, -1));// checks neighbor left
					if (c < size() - 1)
						neighbors.push(checkNeighbors(r, c,  0,  1));// checks neighbor right
					if (r > 0)
						neighbors.push(checkNeighbors(r, c, -1,  0));// checks neighbor up
					if (r < size() - 1)
						neighbors.push(checkNeighbors(r, c,  1,  0));// checks neighbor down
				}
			}
		}
		
		return neighbors;

	}

	/**
	 * String representation of this board (in the output format specified
	 * below)
	 * {size}
	 * 	 1  2  3
	 *   4  5  6
	 *   7  8  0
	 **/
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(size() + "\n");
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
		
	}

	// ********** Helper Methods ***********//
	
	/**
	 * Creates a goalBoard and assigns the associated field.
	 */
	private int[][] createGoalBoard() {
		int[][] temp = new int[size()][size()];
		    
	    int counter = 1;
	    while (counter <= size()) {
		    for (int r = 0; r < size(); r++) {
		        for (int c = 0; c < size(); c++) {
		            temp[r][c] = counter;
		            counter++;
		        }
		    }
	    }
	    temp[size()-1][size()-1] = 0;
	    goalBoard = new Board(temp);
	    return temp;
	    
	}
	
	/**
	 * Returns a goalBoard using the createGoalBoard method.
	 * @return
	 */
	public Board getGoalBoard() {
		goalBoard = new Board(createGoalBoard());
		return goalBoard;
	}

	/**
	 * Checks if two Boards or blocks[][] are equal to eachother using 
	 * Arrays.deepEquals
	 * @param blocks1
	 * @param blocks2
	 * @return
	 */
	private boolean blocksEqual(int[][] blocks1, int[][] blocks2) {
		for (int r = 0; r < size(); r++) {
			for (int c = 0; c < size(); c++) {
				if (Arrays.deepEquals(blocks1, blocks2)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	/**
	 * Creates a copy of a given 2D int array. Used to assign the blocks field
	 * int in the constructor.
	 * 
	 * @param b
	 * @return
	 */
	private int[][] copy(int[][] b) {
		int[][] copy = new int[b.length][b.length];
		for (int r = 0; r < b.length; r++) {
			for (int c = 0; c < b.length; c++) {
				copy[r][c] = b[r][c];
			}
		}
		return copy;
		
	}
	
	/**
	 * Converts the 2D block into a single dimensional array.
	 * 
	 * @param blocks2
	 */
	private void twoD2oneD(int[][] blocks2) {
		for (int r = 0; r < size(); r++){
			for (int c = 0; c < size(); c++){
				if (blocks2[r][c] == 0){
					zeroRow = r;
					continue;
				}
				oneDBlocks.add(blocks2[r][c]);
			}
		}
	}
	
	/**
	 * Returns the total inversions contained in the Blocks (using the single
	 * dimensional array).
	 * 
	 * @return The total number of inversions found.
	 */
	private int invCount() {
		int n = oneDBlocks.size(); 
		int count = 0;
			for (int i = 0; i < n - 1; i++) 
		        for (int j = i + 1; j < n; j++)
		            if (oneDBlocks.get(i) > oneDBlocks.get(j))
		            	count++;
		 return count;
		 
	}
	
	/**
	 * Finds the Manhattan Distance of a particular block on the board.
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	private int manhattanDistance(int r, int c) {
		int block = blocks[r][c];

		if (block == 0) {
			return 0;
		} else {
			return Math.abs(r - (block - 1) / size()) // row of the block
					+ Math.abs(c - (block - 1) % size()); // column of the block
		}
		
	}
	
	private Board checkNeighbors(int r, int c, int i, int j) {
		Board blocksT = new Board(blocks);	
		blocksT.blocks[r][c] = blocks[r + i][c + j];
		blocksT.blocks[r + i][c + j] = 0;
		
		return blocksT;
		
	}
	
	// ============ Test Client ============//
	/*
	 * Unit Testing from assignment instructions
	 */
	public static void main(String[] args) {        
		String file = "src/res/puzzle4x4-19.txt";
		
		 //create initial board from file
	    In in = new In(file);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
		
        Board myBoard = new Board(blocks);
        System.out.println(myBoard);
        
        System.out.println("Dimension: " + myBoard.size());
        System.out.println("Manhattan: " + myBoard.manhattan());
        System.out.println("Hamming: " + myBoard.hamming());
        System.out.println("Solvable: " + myBoard.isSolvable());
        System.out.println();
        
        System.out.println("Neighboring Boards");
        System.out.println(myBoard.neighbors());
              
    }
}
