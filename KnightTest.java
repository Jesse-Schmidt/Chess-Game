package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

public class KnightTest {
	private ChessBoard board;
	private Knight blackKnight;
	private Knight whiteKnight;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		blackKnight = new Knight(board, Color.BLACK);
		whiteKnight = new Knight(board, Color.WHITE);
	}

	@Test
	public void testConstruction() {
		assertEquals(blackKnight.getColor(), Color.BLACK);
		assertEquals(whiteKnight.getColor(), Color.WHITE);
	}
	
	@Test
	public void testLegalPosition() {
		try {
			blackKnight.setPosition("e1");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackKnight.row, 0);
		assertEquals(blackKnight.column, 4);
		assertEquals(blackKnight.getPosition(), "e1");
		
		try {
			blackKnight.setPosition("d8");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackKnight.row, 7);
		assertEquals(blackKnight.column, 3);
		assertEquals(blackKnight.getPosition(), "d8");
	}
	
	@Test
	public void testIllegalPositionNumber() {
		int count = 0;
		try {
			whiteKnight.setPosition("e9");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKnight.setPosition("e0");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPositionLetter() {
		int count = 0;
		try {
			whiteKnight.setPosition("z4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKnight.setPosition("i5");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPosition() {
		try {
			whiteKnight.setPosition("j10");
		}catch (IllegalPositionException e) {
			return;
		}
		fail("exception not thrown");
	}
	
	@Test
	public void testIllegalPositionExtraCharacters() {
		int count = 0;
		try {
			whiteKnight.setPosition(".a4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKnight.setPosition(".4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKnight.setPosition("a.");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals(whiteKnight.toString(), "\u2658");
		assertEquals(blackKnight.toString(), "\u265E");
	}
	
	@Test
	public void testLegalMoves() {
		assertNotEquals(whiteKnight.legalMoves(), null);
		assertEquals(whiteKnight.legalMoves().size(), 0);
	}

}
