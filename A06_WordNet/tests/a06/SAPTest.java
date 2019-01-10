package a06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class SAPTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSAP() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDAG() {
		In in = new In("src/res/digraph1.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(true, sap.isDAG());
	}

	@Test
	public void testIsDagFalse() {
		In in = new In("src/res/digraphNoDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(false, sap.isDAG());
	}

	@Test
	public void testIsRootedDagTrue() {
		In in = new In("src/res/digraph1.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(true, sap.isRootedDAG());
	}

	@Test
	public void testIsRootedDagFalseBecauseGraphisNotaDag() {
		In in = new In("src/res/digraphNoDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(false, sap.isRootedDAG());
	}

	@Test
	public void testIsRootedDagFalseBecauseGraphisANonRootedDag() {
		In in = new In("src/res/digraphNonRootedDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(true, sap.isDAG());
		assertEquals(false, sap.isRootedDAG());
	}

	@Test
	public void testLengthwDigraph1() {
		In in = new In("src/res/digraph1.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(4, sap.length(6, 8));
	}

	@Test
	public void testLengthwDigraph2() {
		In in = new In("src/res/digraph2.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(2, sap.length(1, 5));
	}

	@Test
	public void testLengthwDigraphNoDAG() {
		In in = new In("src/res/digraphNoDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(1, sap.length(0, 9));
	}

	@Test
	public void testAncestorwDigraph1() {
		In in = new In("src/res/digraph1.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(1, sap.ancestor(6, 8));
	}

	@Test
	public void testAncestorwDigraph2() {
		In in = new In("src/res/digraph2.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(0, sap.ancestor(1, 5));
	}

	@Test
	public void testAncestorwDigraphNoDAG() {
		In in = new In("src/res/digraphNoDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		assertEquals(9, sap.ancestor(0, 9));
	}

	@Test
	public void testLengthIterablewDigraph1() {
		In in = new In("src/res/digraph1.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		Queue<Integer> q1 = new Queue<>();
		q1.enqueue(0);
		q1.enqueue(9);
		Queue<Integer> q2 = new Queue<>();
		q2.enqueue(8);
		q2.enqueue(3);
		assertEquals(2, sap.length(q1, q2));
		assertEquals(5, sap.ancestor(q1, q2));
	}

	@Test
	public void testLengthIterablewDigraph2() {
		In in = new In("src/res/digraph2.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		Queue<Integer> q1 = new Queue<>();
		q1.enqueue(2);
		Queue<Integer> q2 = new Queue<>();
		q2.enqueue(5);
		q2.enqueue(0);
		assertEquals(3, sap.length(q1, q2));
		assertEquals(5, sap.ancestor(q1, q2));
	}

	@Test
	public void testLengthwIterableDigraphNoDAG() {
		In in = new In("src/res/digraphNoDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		Queue<Integer> q1 = new Queue<>();
		q1.enqueue(8);
		Queue<Integer> q2 = new Queue<>();
		q2.enqueue(0);
		assertEquals(3, sap.length(q1, q2));
		assertEquals(0, sap.ancestor(q1, q2));
	}

	@Test
	public void testAncestorIterablewDigraph2() {
		In in = new In("src/res/digraph2.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		Queue<Integer> q1 = new Queue<>();
		q1.enqueue(1);
		q1.enqueue(3);
		Queue<Integer> q2 = new Queue<>();
		q2.enqueue(5);
		q2.enqueue(0);
		assertEquals(0, sap.ancestor(q1, q2));
		assertEquals(1, sap.length(q1, q2));
	}

	@Test
	public void testAncestorIterablewDigraphNoDAG() {
		In in = new In("src/res/digraphNoDAG.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		Queue<Integer> q1 = new Queue<>();
		q1.enqueue(2);
		q1.enqueue(0);
		Queue<Integer> q2 = new Queue<>();
		q2.enqueue(8);
		q2.enqueue(11);
		// 0 and 11 have the nearest ancestor (9) at length 2
		assertEquals(9, sap.ancestor(q1, q2));
		assertEquals(2, sap.length(q1, q2));
	}

}
