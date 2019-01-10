package a04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class BoardTest_Student {

	private final Board board1 = new Board(new int[][]{
			{0}});
	private final Board board2 = new Board(new int[][]{
			{2,0},
			{1,3}});
	private final Board board3 = new Board(new int[][]{
			{8, 1, 3},
			{4, 0, 2},
			{7, 6, 5}});
	private final Board board4 = new Board(new int[][]{
			{ 1,  2,  3,  4},
			{ 5,  6,  7,  8},
			{ 9, 10, 11,  0},
			{13, 14, 15, 12}});
	private final Board goalBoard = new Board(new int[][]{
			{ 1,  2,  3,  4},
			{ 5,  6,  7,  8},
			{ 9, 10, 11, 12},
			{13, 14, 15,  0}});
	
	@Test
	public void testBoardImmutable() {
		int[][] boardArray = {{1,2,3},{4,5,6},{7,8,0}};
		Board immutableBoard = new Board(boardArray);
		boardArray[2][2] = 9; // should not affect the board
		assertEquals("3\n 1  2  3 \n 4  5  6 \n 7  8  0 \n", immutableBoard.toString());
	}

	@Test
	public void testSize() {
		assertEquals(3, board3.size());
		assertEquals(4, goalBoard.size());
		assertEquals(2, board2.size());
		assertEquals(1, board1.size());
	}

	@Test
	public void testHamming() {
		assertEquals(5, board3.hamming());
		assertEquals(0, goalBoard.hamming());
		assertEquals(3, board2.hamming());
		assertEquals(0, board1.hamming());
		Board offByOneBoard = new Board(new int[][]{{1,2,3},{4,5,6},{7,0,8}});
		assertEquals(1, offByOneBoard.hamming());
	}

	@Test
	public void testManhattan() {
		assertEquals(10, board3.manhattan());
		assertEquals(0, goalBoard.manhattan());
		assertEquals(3, board2.manhattan());
		assertEquals(0, board1.manhattan());
		Board offByOneBoard = new Board(new int[][]{{1,2,3},{4,5,6},{7,0,8}});
		assertEquals(1, offByOneBoard.manhattan());
	}

	@Test
	public void testIsGoal() {
		assertEquals(false, board3.isGoal());
		assertEquals(true, goalBoard.isGoal());
		assertEquals(false, board2.isGoal());
		assertEquals(true, board1.isGoal());
	}

	@Test
	public void testIsSolvable() {
		assertEquals(true, board3.isSolvable());
		assertEquals(true, goalBoard.isSolvable());
		assertEquals(true, board2.isSolvable());
		assertEquals(true, board1.isSolvable());
		Board notSolvable = new Board(new int[][]{{1,2,3},{4,5,6},{8,7,0}});
		assertEquals(false, notSolvable.isSolvable());
	}

	@Test
	public void testEquals() {
		assertEquals(false, board3.equals(goalBoard));
		assertEquals(false, goalBoard.equals(board3));
		Board equalsTestBoard = new Board(new int[][]{{8, 1, 3},{4, 0, 2},{7, 6, 5}});
		assertEquals(true, board3.equals(equalsTestBoard));
		assertEquals(true, equalsTestBoard.equals(board3));
		assertEquals(true, board1.equals(new Board(new int[][]{{0}})));
	}
	
	@Test
	public void testNeighborsBoard3() {
		List<Board> expectedNeighbors = new ArrayList<>();
		Collections.addAll(expectedNeighbors, 
				new Board(new int[][]{{8, 1, 3},{4, 2, 0},{7, 6, 5}}),
				new Board(new int[][]{{8, 1, 3},{0, 4, 2},{7, 6, 5}}),
				new Board(new int[][]{{8, 1, 3},{4, 6, 2},{7, 0, 5}}),
				new Board(new int[][]{{8, 0, 3},{4, 1, 2},{7, 6, 5}}));		
		Iterable<Board> actualNeighbors = board3.neighbors();
		
		// check length
		assertEquals(expectedNeighbors.size(), length(actualNeighbors));		

		// check neighbors individually because they can be listed in arbitrary order
		for (Board b : actualNeighbors) {
			if (expectedNeighbors.contains(b))
				expectedNeighbors.remove(b);
			else
				fail("Fail.\nGiven:\n" + actualNeighbors.toString() + 
						"Expected:\n" + boardsToString(actualNeighbors));
		}
	}

	@Test
	public void testNeighborsBoard4() {
		List<Board> expectedNeighbors = new ArrayList<>();
		Collections.addAll(expectedNeighbors, 
				new Board(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}}),
				new Board(new int[][]{{1,2,3,4},{5,6,7,0},{9,10,11,8},{13,14,15,12}}),
				new Board(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,0,11},{13,14,15,12}}));
		Iterable<Board> actualNeighbors = board4.neighbors();
		
		// check length
		assertEquals(expectedNeighbors.size(), length(actualNeighbors));		

		// check neighbors individually because they can be listed in arbitrary order
		for (Board b : actualNeighbors) {
			if (expectedNeighbors.contains(b))
				expectedNeighbors.remove(b);
			else
				fail("Fail.\nGiven:\n" + actualNeighbors.toString() + 
						"Expected:\n" + boardsToString(actualNeighbors));
		}
	}
	
	@Test
	public void testNeighborsBoard2() {
		List<Board> expectedNeighbors = new ArrayList<>();
		Collections.addAll(expectedNeighbors, 
				new Board(new int[][]{{0,2},{1,3}}),
				new Board(new int[][]{{2,3},{1,0}}));
		Iterable<Board> actualNeighbors = board2.neighbors();
		
		// check length
		assertEquals(expectedNeighbors.size(), length(actualNeighbors));		

		// check neighbors individually because they can be listed in arbitrary order
		for (Board b : actualNeighbors) {
			if (expectedNeighbors.contains(b))
				expectedNeighbors.remove(b);
			else
				fail("Fail.\nGiven:\n" + actualNeighbors.toString() + 
						"Expected:\n" + boardsToString(actualNeighbors));
		}
	}

	@Test
	public void testNeighborsBoard1() {
		Iterable<Board> actualNeighbors = board1.neighbors();
		assertEquals(0, length(actualNeighbors));	
		assertEquals("[]", boardsToString(actualNeighbors));	
	}
	
	@Test
	public void testNeighborsBoard0() {
		Iterable<Board> actualNeighbors = goalBoard.neighbors();
		assertEquals(0, length(actualNeighbors));	
		assertEquals("[]", boardsToString(actualNeighbors));
	}

	private int length(Iterable<Board> boards) {
		int count = 0;
		for (Board b : boards)
			count++;
		
		return count;
	}

	@Test
	public void testToString() {
		assertEquals("3\n 8  1  3 \n 4  0  2 \n 7  6  5 \n", board3.toString());
		assertEquals("4\n 1  2  3  4 \n 5  6  7  8 \n 9 10 11 12 \n13 14 15  0 \n", goalBoard.toString());
		assertEquals("2\n 2  0 \n 1  3 \n", board2.toString());
		assertEquals("1\n 0 \n", board1.toString());
	}
	
	private String boardsToString(Iterable<Board> boards) {
		StringBuilder sb = new StringBuilder("[");
		for (Board b : boards) {
			sb.append(b).append(", ");
		}
		if (sb.length() >= 2) sb.replace(sb.length() - 2, sb.length(), "]");
		else sb.append("]");
		
		return sb.toString();
	}

}