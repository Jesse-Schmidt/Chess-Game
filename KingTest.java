package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

import java.util.*;

public class KingTest {
	private ChessBoard board;
	private King blackKing;
	private King whiteKing;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		board.initialize();
		blackKing = new King(board, Color.BLACK);
		whiteKing = new King(board, Color.WHITE);
	}

	@Test
	public void testConstruction() {
		assertEquals(blackKing.getColor(), Color.BLACK);
		assertEquals(whiteKing.getColor(), Color.WHITE);
	}
	
	@Test
	public void testLegalPosition() {
		try {
			blackKing.setPosition("e1");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackKing.row, 0);
		assertEquals(blackKing.column, 4);
		assertEquals(blackKing.getPosition(), "e1");
		
		try {
			blackKing.setPosition("d8");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackKing.row, 7);
		assertEquals(blackKing.column, 3);
		assertEquals(blackKing.getPosition(), "d8");
	}
	
	@Test
	public void testIllegalPositionNumber() {
		int count = 0;
		try {
			whiteKing.setPosition("e9");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKing.setPosition("e0");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPositionLetter() {
		int count = 0;
		try {
			whiteKing.setPosition("z4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKing.setPosition("i5");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPosition() {
		try {
			whiteKing.setPosition("j10");
		}catch (IllegalPositionException e) {
			return;
		}
		fail("exception not thrown");
	}
	
	@Test
	public void testIllegalPositionExtraCharacters() {
		int count = 0;
		try {
			whiteKing.setPosition(".a4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKing.setPosition(".4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteKing.setPosition("a.");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals(whiteKing.toString(), "\u2654");
		assertEquals(blackKing.toString(), "\u265a");
	}
	
	@Test
	public void testLegalMoves() {
		try {
			blackKing.setPosition("e8");
			ArrayList<String> moves = blackKing.legalMoves();
			assertEquals(moves.size(), 0);
			
			blackKing.setPosition("e7");
			moves = blackKing.legalMoves();
			assertEquals(moves.size(), 3);
			
			blackKing.setPosition("b4");
			moves = blackKing.legalMoves();
			assertEquals(moves.size(), 8);
			
			blackKing.setPosition("d2");
			moves = blackKing.legalMoves();
			assertEquals(moves.size(), 8);
			
			blackKing.setPosition("h1");
			moves = blackKing.legalMoves();
			assertEquals(moves.size(), 3);
			
			whiteKing.setPosition("e1");
			moves = whiteKing.legalMoves();
			assertEquals(moves.size(), 0);
			
			whiteKing.setPosition("e2");
			moves = whiteKing.legalMoves();
			assertEquals(moves.size(), 3);
			
			whiteKing.setPosition("b5");
			moves = whiteKing.legalMoves();
			assertEquals(moves.size(), 8);
			
			whiteKing.setPosition("d7");
			moves = whiteKing.legalMoves();
			assertEquals(moves.size(), 8);
			
			whiteKing.setPosition("h8");
			moves = whiteKing.legalMoves();
			assertEquals(moves.size(), 3);
			
		}catch(IllegalPositionException e) {
			fail("Exception Thrown");
		}
	}

}
