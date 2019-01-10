package a01;

public class PercolationClient {

	public static void main(String[] args) {
		int N = 200;
        int T = 100;
        
        PercolationStats ps = new PercolationStats(N, T);
        
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("Low                     = " + ps.confidenceLow());
        System.out.println("High                    = " + ps.confidenceHigh());
    }

}