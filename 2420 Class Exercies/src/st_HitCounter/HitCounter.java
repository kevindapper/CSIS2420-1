package st_HitCounter;

import edu.princeton.cs.algs4.ST;

public class HitCounter {

	ST<String,Integer> st = new ST<>();
	
	public void hit(String key) {	
		if (st.contains(key))
			st.put(key, st.get(key)+1);
		else	st.put(key, 1); 
		
	}
	
	public int count(String key){
		if (st.contains(key))
			return st.get(key);
		else return 0;
	}
}