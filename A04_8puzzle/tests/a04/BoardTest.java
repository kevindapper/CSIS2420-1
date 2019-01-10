package a04;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	int[][] testArray1 = new int[][] { { 1, 2, 3 }, { 7, 0, 6 }, { 5, 4, 8 }, };
	int[][] testArray2 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }, };
	int[][] testArray3 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 }, };
	private int[][] boardArray3 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
	private Board board3;

	@Before   //  use setUP
	public void setUp() throws Exception {
		//board3 = new Board(new int [3][3]);
		board3 = new Board(boardArray3);

	}

	@Test
	public void testBoard() {
		Board b = new Board(new int[][] {{1, 2, 3},{4, 5, 6},{7, 0, 8}});
		assertEquals(3, b.size());
		assertEquals(" 3\n 1  2  3 \n 4  5  6 \n 7  0  8 \n",b.toString());
	}
	
	@Test
	public void testBoardImmutable() {
		int [][] boardArray = new int[][] {{1, 2, 3},{4, 5, 6},{7, 0, 8}};
		Board b = new Board(boardArray);
		
		boardArray[0][0] = 9; // changing the boardArray should not affect Board b
		assertEquals(3, b.size());
		assertEquals(" 3\n 1  2  3 \n 4  5  6 \n 7  0  8 \n",b.toString());

	}
	
	@Test
	public void goalBoardTrue() {
		Board board2 = new Board(testArray2);
		assertTrue(board2.isGoal());
	}
	
	@Test
	public void goalBoardFalse() {
		Board board1 = new Board(testArray1);
		assertFalse(board1.isGoal());
	}
		
	public void hamming() {
		Board board5 = new Board(testArray1);
		assertTrue(board5.hamming() == 4);
	}
	
	@Test
	public void hamming5() {
		int expected = 5;
		int actual = board3.hamming();
		assertTrue(expected == actual);
	}

	@Test
	public void manhattan() {
		Board board6 = new Board(testArray1);
		assertTrue(board6.manhattan() == 6);
	}
	
	@Test
	public void manhattan5() {
//		int expected = 10;
//		int actual = board3.manhattan();
//		assertTrue(expected == actual);
		assertTrue(10 == board3.manhattan());
	}

	@Test
	public void testIsGoal() {
		assertFalse(board3.isGoal());
	}

	@Test
	public void isSolvableTrue() {
		Board board3 = new Board(testArray1);
		assertTrue(board3.isSolvable());
	}
	
	@Test
	public void isSolvableFalse() {
		Board board4 = new Board(testArray3);
		assertFalse(board4.isSolvable());
	}	

	@Test
	public void testEqualsObject() {
		Board testBoard1 = new Board (testArray1);
		Board testBoard2 = new Board (testArray1);
		
		assertTrue(testBoard1.equals(testBoard2));
    }

	@Test
	public void testNeighbors() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testToString() {
		assertEquals(" 3\n 8  1  3 \n 4  0  2 \n 7  6  5 \n",board3.toString());

	}

}


