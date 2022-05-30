package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

import java.util.*;

public class RookTest {
	private ChessBoard board;
	private Rook blackRook;
	private Rook whiteRook;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		blackRook = new Rook(board, Color.BLACK);
		whiteRook = new Rook(board, Color.WHITE);
	}

	@Test
	public void testConstruction() {
		assertEquals(blackRook.getColor(), Color.BLACK);
		assertEquals(whiteRook.getColor(), Color.WHITE);
	}
	
	@Test
	public void testLegalPosition() {
		try {
			blackRook.setPosition("e1");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackRook.row, 0);
		assertEquals(blackRook.column, 4);
		assertEquals(blackRook.getPosition(), "e1");
		
		try {
			blackRook.setPosition("d8");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackRook.row, 7);
		assertEquals(blackRook.column, 3);
		assertEquals(blackRook.getPosition(), "d8");
	}
	
	@Test
	public void testIllegalPositionNumber() {
		int count = 0;
		try {
			whiteRook.setPosition("e9");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteRook.setPosition("e0");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPositionLetter() {
		int count = 0;
		try {
			whiteRook.setPosition("z4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteRook.setPosition("i5");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPosition() {
		try {
			whiteRook.setPosition("j10");
		}catch (IllegalPositionException e) {
			return;
		}
		fail("exception not thrown");
	}
	
	@Test
	public void testIllegalPositionExtraCharacters() {
		int count = 0;
		try {
			whiteRook.setPosition(".a4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteRook.setPosition(".4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whiteRook.setPosition("a.");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals(whiteRook.toString(), "\u2656");
		assertEquals(blackRook.toString(), "\u265C");
	}
	
	public void testLegalMoves() {
		try {
			blackRook.setPosition("a8");
			ArrayList<String> moves = blackRook.legalMoves();
			assertEquals(moves.size(), 0);
			
			blackRook.setPosition("d7");
			moves = blackRook.legalMoves();
			assertEquals(moves.size(), 5);
			
			blackRook.setPosition("g4");
			moves = blackRook.legalMoves();
			assertEquals(moves.size(), 11);
			
			blackRook.setPosition("h1");
			moves = blackRook.legalMoves();
			assertEquals(moves.size(), 2);
			
			whiteRook.setPosition("a1");
			moves = whiteRook.legalMoves();
			assertEquals(moves.size(), 0);
			
			whiteRook.setPosition("d2");
			moves = whiteRook.legalMoves();
			assertEquals(moves.size(), 5);
			
			whiteRook.setPosition("g5");
			moves = whiteRook.legalMoves();
			assertEquals(moves.size(), 11);
			
			whiteRook.setPosition("h8");
			moves = whiteRook.legalMoves();
			assertEquals(moves.size(), 2);
			
		}catch(IllegalPositionException e) {
			fail("Exception Thrown");
		}
	}

}
