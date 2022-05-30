package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

import java.util.*;

public class BishopTest {
	private ChessBoard board;
	private Bishop blackBishop;
	private Bishop whiteBishop;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		board.initialize();
		blackBishop = new Bishop(board, Color.BLACK);
		whiteBishop = new Bishop(board, Color.WHITE);
	}

	@Test
	public void testConstruction() {
		assertEquals("assert black bishop color is black", blackBishop.getColor(), Color.BLACK);
		assertEquals("assert black bishop color is white", whiteBishop.getColor(), Color.WHITE);
	}
	
	@Test
	public void testLegalPosition() {
		try {
			blackBishop.setPosition("e1");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals("assert that char 1 corresponds to row 0", blackBishop.row, 0);
		assertEquals("assert that char e corresponds to column 4", blackBishop.column, 4);
		assertEquals("assert that bishop location is set to e1", blackBishop.getPosition(), "e1");
		
		try {
			blackBishop.setPosition("d8");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals("assert that char 8 corresponds to row 7", blackBishop.row, 7);
		assertEquals("assert that char d corresponds to column 3", blackBishop.column, 3);
		assertEquals("assert that bishop location is set to d8", blackBishop.getPosition(), "d8");
	}
	
	@Test
	public void testIllegalPositionNumber() {
		int count = 0;
		try {
			whiteBishop.setPosition("e9");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteBishop.setPosition("e0");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals("assert that 2 IllegalPositionExceptions are thrown", count, 2);
	}
	
	@Test
	public void testIllegalPositionLetter() {
		int count = 0;
		try {
			whiteBishop.setPosition("z4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteBishop.setPosition("i5");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteBishop.setPosition("B5");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals("assert that 3 IllegalPositionExceptions are thrown", count, 3);
	}
	
	@Test
	public void testIllegalPosition() {
		try {
			whiteBishop.setPosition("j10");
		}catch (IllegalPositionException e) {
			return;
		}
		fail("exception not thrown");
	}
	
	@Test
	public void testIllegalPositionExtraCharacters() {
		int count = 0;
		try {
			whiteBishop.setPosition(".a4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteBishop.setPosition(".4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteBishop.setPosition("a.");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals("assert that 3 IllegalPositionExceptions are thrown",count, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals("assert that white bishop to string returns \u2657", whiteBishop.toString(), "\u2657");
		assertEquals("assert that black bishop to string returns \u265D",blackBishop.toString(), "\u265D");
	}
	
	@Test
	public void testLegalMoves() {
		try {
			blackBishop.setPosition("c8");
			ArrayList<String> moves = blackBishop.legalMoves();
			assertEquals("assert that a bishop cannot move through same color pieces", moves.size(), 0);
			
			blackBishop.setPosition("d7");
			moves = blackBishop.legalMoves();
			assertEquals("assert that a bishop can move diagonally in all four directions", moves.size(), 7);
			
			blackBishop.setPosition("e4");
			moves = blackBishop.legalMoves();
			assertEquals("assert that a bishop can move diagonally in all four directions", moves.size(), 8);
			
			blackBishop.setPosition("c1");
			moves = blackBishop.legalMoves();
			assertEquals("assert that a bishop can capture opposite color pieces", moves.size(), 2);
			
			blackBishop.setPosition("a3");
			moves = blackBishop.legalMoves();
			assertEquals("assert that a bishop cannot move off the edge of the board", moves.size(), 4);
			
			whiteBishop.setPosition("c1");
			moves = whiteBishop.legalMoves();
			assertEquals("assert that a bishop cannot move through same color pieces",moves.size(), 0);
			
			whiteBishop.setPosition("d2");
			moves = whiteBishop.legalMoves();
			assertEquals("assert that a bishop can move diagonally in all four directions", moves.size(), 7);
			
			whiteBishop.setPosition("e5");
			moves = whiteBishop.legalMoves();
			assertEquals("assert that a bishop can move diagonally in all four directions", moves.size(), 8);
			
			whiteBishop.setPosition("c8");
			moves = whiteBishop.legalMoves();
			assertEquals("assert that a bishop can capture opposite color pieces", moves.size(), 2);
			
			whiteBishop.setPosition("a6");
			moves = whiteBishop.legalMoves();
			assertEquals("assert that a bishop cannot move off the edge of the board", moves.size(), 4);
			
		}catch(IllegalPositionException e) {
			fail("Exception Thrown");
		}
	}

}
