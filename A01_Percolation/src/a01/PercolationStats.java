package a01;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Represents a analysis model for a Percolation object by creating
 * a grid of size N. Tests are run T times and statistics for mean,
 * standard deviation, and low/high endpoint of 95% confidence interval.
 * @author cris.cooper and Gerald Brady
 *
 */

public class PercolationStats {

	private double gridSize;
	private double[] dataArray;

	/**
	 * Performs T independent experiments on an N-by-N grid.
	 * @param N - Grid size to create.
	 * @param T - Number of tests that should be run on a grid of N size.
	 */
	public PercolationStats(int N, int T) {
		if (N < 0 || T < 0) {
			throw new java.lang.IllegalArgumentException("N and T cannot be less than 0");
		}
		
		//set up fields for this test run.
		this.gridSize = N * N;
		this.dataArray = new double[T];

		//create new Percolation objects T time.
		for (int i = 0; i < T; i++) {
			double sitesOpened = 0;
			Percolation percolation = new Percolation(N);

			//run current test until the given system percolates.
			while (!percolation.percolates()) {
				int row = StdRandom.uniform(N);
				int col = StdRandom.uniform(N);

				if (!percolation.isOpen(row, col)) {
					percolation.open(row, col);
					sitesOpened++;
				}
			}

			//Save test results in an array so they can be analyzed.
			this.dataArray[i] = sitesOpened;

		}

	}

	/**
	 * Samples mean of percolation threshold.
	 * @return - returns mean based on the dataArray.
	 */
	public double mean() {
		return StdStats.mean(dataArray) / gridSize;
	}

	/**
	 * Samples standard deviation of the percolation threshold.
	 * @return - Returns standard deviation based on the dataArray.
	 */
	public double stddev() {
		return StdStats.stddev(dataArray) / gridSize;
	}

	/**
	 * Provides low end-point of the 95% confidence interval.
	 * @return - Low end point.
	 */
	public double confidenceLow() {
		return mean() - ((1.96 * stddev()) / Math.sqrt(meanArraySum()));

	}

	/**
	 * Provides high end-point of the 95% confidence interval.
	 * @return - High end point
	 */
	public double confidenceHigh() {
		
		return mean() + ((1.96 * stddev()) / Math.sqrt(meanArraySum()));

	}
	
	/**
	 * Method for summing the elements of the dataArray so they can
	 * be processed by the low/high confidence methods.
	 * @return - Returns sum of dataArray elements.
	 */
	private double meanArraySum() {
		double sum = 0;
		for(double el : dataArray) {
			sum = sum += el;
		}
		return sum;
	}

}
