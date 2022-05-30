package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

import java.util.*;

public class PawnTest {
	private ChessBoard board;
	private Pawn blackPawn;
	private Pawn whitePawn;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		board.initialize();
		blackPawn = new Pawn(board, Color.BLACK);
		whitePawn = new Pawn(board, Color.WHITE);
	}

	@Test
	public void testConstruction() {
		assertEquals(blackPawn.getColor(), Color.BLACK);
		assertEquals(whitePawn.getColor(), Color.WHITE);
	}
	
	@Test
	public void testLegalPosition() {
		try {
			blackPawn.setPosition("e1");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackPawn.row, 0);
		assertEquals(blackPawn.column, 4);
		assertEquals(blackPawn.getPosition(), "e1");
		
		try {
			blackPawn.setPosition("d8");
		}
		catch(IllegalPositionException e) {
			fail("exception thrown");
		}
		assertEquals(blackPawn.row, 7);
		assertEquals(blackPawn.column, 3);
		assertEquals(blackPawn.getPosition(), "d8");
	}
	
	@Test
	public void testIllegalPositionNumber() {
		int count = 0;
		try {
			whitePawn.setPosition("e9");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whitePawn.setPosition("e0");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPositionLetter() {
		int count = 0;
		try {
			whitePawn.setPosition("z4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whitePawn.setPosition("i5");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testIllegalPosition() {
		try {
			whitePawn.setPosition("j10");
		}catch (IllegalPositionException e) {
			return;
		}
		fail("exception not thrown");
	}
	
	@Test
	public void testIllegalPositionExtraCharacters() {
		int count = 0;
		try {
			whitePawn.setPosition(".a4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whitePawn.setPosition(".4");
		}catch (IllegalPositionException e) {
			count++;
		}
		try {
			whitePawn.setPosition("a.");
		}catch (IllegalPositionException e) {
			count++;
		}
		assertEquals(count, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals(whitePawn.toString(), "\u2659");
		assertEquals(blackPawn.toString(), "\u265F");
	}
	
	@Test
	public void testLegalMoves() {
		try {
			blackPawn.setPosition("a7");
			ArrayList<String> moves = blackPawn.legalMoves();
			assertEquals(moves.size(), 2);
			
			blackPawn.setPosition("h7");
			moves = blackPawn.legalMoves();
			assertEquals(moves.size(), 2);
			
			blackPawn.setPosition("e3");
			moves = blackPawn.legalMoves();
			assertEquals(moves.size(), 2);
			
			blackPawn.setPosition("d8");
			moves = blackPawn.legalMoves();
			assertEquals(moves.size(), 0);
			
			blackPawn.setPosition("c5");
			moves = blackPawn.legalMoves();
			assertEquals(moves.size(), 1);
			
			whitePawn.setPosition("a2");
			moves = whitePawn.legalMoves();
			assertEquals(moves.size(), 2);
			
			whitePawn.setPosition("h2");
			moves = whitePawn.legalMoves();
			assertEquals(moves.size(), 2);
			
			whitePawn.setPosition("e6");
			moves = whitePawn.legalMoves();
			assertEquals(moves.size(), 2);
			
			whitePawn.setPosition("d1");
			moves = whitePawn.legalMoves();
			assertEquals(moves.size(), 0);
			
			whitePawn.setPosition("c4");
			moves = whitePawn.legalMoves();
			assertEquals(moves.size(), 1);
		}catch(IllegalPositionException e) {
			fail("Exception Thrown");
		}
	}

}
