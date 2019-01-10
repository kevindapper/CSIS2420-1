package priorityQueue;

import edu.princeton.cs.algs4.StdOut;

public class DemoQP {

    public static void main(String[] args) {
        //demoPQ_Part1();
        demoPQ_Part2();
    }

	private static void demoPQ_Part2() {
		for (int i = 0; i < 10; i++)
			System.out.println(new Mail());
		
	}

	@SuppressWarnings("unused")
	private static void demoPQ_Part1() {
		Mail[] inputData = {
        		new Mail(MailType.LETTER, MailPriority.REGULAR, "Joe"),
        		new Mail(MailType.LETTER, MailPriority.REGULAR, "Rod"),
        		new Mail(MailType.PACKAGE, MailPriority.EXPRESS, "Sue"),
        		new Mail(MailType.LETTER, MailPriority.REGULAR, "Tim"),
        		null,
        		null,
        		new Mail(MailType.LETTER, MailPriority.REGULAR, "Ann"),
        		new Mail(MailType.LETTER, MailPriority.OVER_NIGHT, "Tom"),
        		new Mail(MailType.LETTER, MailPriority.EXPRESS, "Ben"),
        		null,
        		new Mail(MailType.PACKAGE, MailPriority.REGULAR, "Jay")
        };
        
    	MaxPQ<Mail> pq = new MaxPQ<>();
    	
    	for(Mail item : inputData){
            if (item != null) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.println("Delevered " + pq.delMax());
        }
    	StdOut.println();    	
    	
    	StdOut.println(pq.size() + " items left to deliver:");
    	StdOut.println("------------------------");
        for(Mail el : pq)
        	StdOut.println(el + " "); // for a PQ it is expensive to printout a toString.  It sends in out in order.
	}

}
