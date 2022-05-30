package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

public class QueenTest {
	private ChessBoard board;
	private Queen blackQueen;
	private Queen whiteQueen;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		blackQueen = new Queen(board, Color.BLACK);
		whiteQueen = new Queen(board, Color.WHITE);
	}

	@Test
	public void testConstruction() {
		assertEquals(blackQueen.getColor(), Color.BLACK);
		assertEquals(whiteQueen.getColor(), Color.WHITE);
	}
	
	@Test
	public void testLegalPosition() {
		try {
			blackQueen.setPosition("e1");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackQueen.row, 0);
		assertEquals(blackQueen.column, 4);
		assertEquals(blackQueen.getPosition(), "e1");
		
		try {
			blackQueen.setPosition("d8");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackQueen.row, 7);
		assertEquals(blackQueen.column, 3);
		assertEquals(blackQueen.getPosition(), "d8");
	}
	
	@Test
	public void testIllegalPositionNumber() {
		int count = 0;
		try {
			whiteQueen.setPosition("e9");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteQueen.setPosition("e0");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPositionLetter() {
		int count = 0;
		try {
			whiteQueen.setPosition("z4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteQueen.setPosition("i5");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPosition() {
		try {
			whiteQueen.setPosition("j10");
		}catch (IllegalPositionException e) {
			return;
		}
		fail("exception not thrown");
	}
	
	@Test
	public void testIllegalPositionExtraCharacters() {
		int count = 0;
		try {
			whiteQueen.setPosition(".a4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteQueen.setPosition(".4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteQueen.setPosition("a.");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals(whiteQueen.toString(), "\u2655");
		assertEquals(blackQueen.toString(), "\u265B");
	}
	
	@Test
	public void testLegalMoves() {
		assertNotEquals(whiteQueen.legalMoves(), null);
		assertEquals(whiteQueen.legalMoves().size(), 0);
	}

}
