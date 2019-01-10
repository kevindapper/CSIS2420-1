package a01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Represents a N-by-N grid of sites that become linked to one another via
 * UnionFind by the opening of specific locations. If a site is connected to
 * another through the same parent and that parent is connected to the top of
 * the grid that site is considered "full". Should a path be found from the top
 * node through to the bottom of the grid we say that the system percolates
 * 
 * @author Gerald Brady and cris.cooper
 *
 */
public class Percolation {
	private boolean[][] grid;
	private WeightedQuickUnionUF unionFind;
	private WeightedQuickUnionUF backWash;
	private int totalOpenGrids = 0;
	private int phantomFloor;
	private int phantomTop = 0;
	private int size = 0;

	/**
	 * Creates N-by-N grid. All sites initially blocked. Sites can be opened
	 * deliberately in one of the visualizer apps or at random through the
	 * PercolationStats class.
	 * @param N - size of the grid.
	 */
	public Percolation(int N) {
		if (N <= 0)
			throw new java.lang.IllegalArgumentException("N must be greater then 0");

		this.size = N;
		this.grid = new boolean[N][N];
		this.phantomFloor = (N * N + 1);
		unionFind = new WeightedQuickUnionUF(N * N + 2);
		backWash = new WeightedQuickUnionUF(N * N + 2);

		// Connects the top row with the phantomTop.
		for (int i = 0; i < N; i++) {
			unionFind.union(gridCell(0, i), phantomTop);
			backWash.union(gridCell(0, i), phantomTop);
			unionFind.union(gridCell(N - 1, i), phantomFloor);
		}

	}

	/**
	 * Opens site (row i, column j) if it is not open already.
	 * @param i - row
	 * @param j - column
	 */
	public void open(int i, int j) {
		if (i < 0 || i > size || j < 0 || j > size)
			throw new java.lang.IllegalArgumentException("Corrdinates must be between 1 and size N");

		if (isOpen(i, j) != true) {
			grid[i][j] = true;
			totalOpenGrids++;
		} else
			return;

		// Checking to connect to adjacent cells.
		connectTop(i, j);
		connectBottom(i, j);
		connectRight(i, j);
		connectLeft(i, j);

	}

	/**
	 * Determines whether the site (row i, column j) is open.
	 * @param i - row
	 * @param j - column
	 * @return - returns true/false
	 */
	public boolean isOpen(int i, int j) {
		return grid[i][j];
	}

	/**
	 * Determines whether the site (row i, column j) is full? A site is
	 * considered "full" if it is both open and a path from it to the top of the
	 * grid can be found.
	 * @param i - row
	 * @param j - column
	 * @return - returns true/false
	 */
	public boolean isFull(int i, int j) {
		if (isOpen(i, j)) {
			if (backWash.connected(gridCell(i, j), phantomTop)) {
				return true;
			}
			return unionFind.connected(gridCell(i, j), phantomTop);
		}
		return false;

	}

	/**
	 * Determines whether the system percolates. The system percolates if a path
	 * from a site in the bottom row can be connected to one in the top row.
	 * @return - returns true/false.
	 */
	public boolean percolates() {
		return unionFind.connected(phantomTop, phantomFloor);
	}

	/**
	 * This method is used by the visualizer.
	 * @return - returns a string indicating the number of open sites in the grid.
	 */
	public String numberOfOpenSites() {
		return totalOpenGrids + " open sites";

	}

	/**
	 * Determines the array value of a grid based on the i, j coordinates.
	 * @param i - i value in the array.
	 * @param j - j value in the array.
	 */
	private int gridCell(int i, int j) {
		return i * size + j + 1;

	}

	/**
	 * Checks to see if there is an open site to the left of the one that was
	 * just opened. If so, a connection is made to that site as well.
	 * @param i - i value in the array.
	 * @param j - j value in the array.
	 */
	private void connectLeft(int i, int j) {
		if (j - 1 >= 0 && isOpen(i, j - 1)) {
			unionFind.union(gridCell(i, j), gridCell(i, j - 1));
			backWash.union(gridCell(i, j), gridCell(i, j - 1));
		}
	}

	/**
	 * Checks to see if there is an open site to the right of the one that was
	 * just opened. If so, a connection is made to that site as well.
	 * @param i - i value in the array.
	 * @param j - j value in the array.
	 */
	private void connectRight(int i, int j) {
		if (j + 1 < size && isOpen(i, j + 1)) {
			unionFind.union(gridCell(i, j), gridCell(i, j + 1));
			backWash.union(gridCell(i, j), gridCell(i, j + 1));
		}
	}

	/**
	 * Checks to see if there is an open site below the one that was just
	 * opened. If so, a connection is made to that site as well.
	 * @param i - i value in the array.
	 * @param j - j value in the array.
	 */
	private void connectBottom(int i, int j) {
		if (i + 1 < size && isOpen(i + 1, j)) {
			unionFind.union(gridCell(i, j), gridCell(i + 1, j));
		}
	}

	/**
	 * Checks to see if there is an open site above the one that was just
	 * opened. If so, a connection is made to that site as well.
	 * @param i - i value in the array.
	 * @param j - j value in the array.
	 */
	private void connectTop(int i, int j) {
		if (i - 1 >= 0 && isOpen(i - 1, j)) {
			unionFind.union(gridCell(i, j), gridCell(i - 1, j));
		}
	}

}