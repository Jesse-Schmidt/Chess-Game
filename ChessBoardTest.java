package a9;

import static org.junit.Assert.*;
import org.junit.*;

import a9.ChessPiece.Color;

public class ChessBoardTest {
	private ChessBoard board;
	
	@Before
	public void setup() {
		board = new ChessBoard();
		board.initialize();
	}
	
	@Test
	public void testGetPiece() {
		try{
			ChessPiece nullPick = board.getPiece("d4");
			assertEquals(nullPick, null);
			ChessPiece rook = board.getPiece("a8");
			assertEquals(rook.getClass(), new Rook(board, Color.BLACK).getClass());
		}catch(IllegalPositionException e) {
			fail("Exception Thrown");
		}
	}
	
	@Test
	public void testGetPieceIllegalPosition() {
		try {
			ChessPiece rook = board.getPiece("i9");
		}catch(IllegalPositionException e) {
			return;
		}
		fail("Exception not caught");
	}
	
	@Test
	public void testConstruction() {
		board = new ChessBoard();
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				String position = (char)('a' + y) + String.valueOf(x+1);
				try {
					assertEquals(board.getPiece(position), null);
				}catch(IllegalPositionException e) {
					fail("Exception Thrown");
				}
			}
		}
	}
	
	@Test
	public void testInitialize() {
		try {
			for(int x = 0; x < 8; x++) {
				for(int y = 0; y < 8; y++) {
					String position = (char)('a' + y) + String.valueOf(x+1);
					if(x == 7 || x == 6 || x == 0 || x == 1) {
						assertNotEquals(board.getPiece(position), null);
					}else {
						assertEquals(board.getPiece(position), null);
					}
				}
			}
		}catch(IllegalPositionException e) {
			fail("Exception Thrown");
		}
	}

	@Test
	public void testPlacePiece() {
		ChessPiece rook = new Rook(board, Color.BLACK);
		ChessPiece bishop = new Bishop(board, Color.WHITE);
		assertEquals(board.placePiece(rook, "b5"), true);
		assertEquals(board.placePiece(bishop, "h3"), true);
		assertEquals(board.placePiece(rook, "d8"), false);
		assertEquals(board.placePiece(bishop, "h2"), false);
		assertEquals(board.placePiece(rook, "h3"), true);
		assertEquals(board.placePiece(bishop, "b5"), true);
	}
	
	@Test
	public void testMove() {
		ChessPiece blackPawn;
		ChessPiece whitePawn;
		ChessPiece blackBishop;
		ChessPiece whiteBishop;
		try {
			 blackPawn = board.getPiece("d7");
			 whitePawn = board.getPiece("d2");
			 blackBishop = board.getPiece("c8");
			 whiteBishop = board.getPiece("c1");
			 assertEquals(blackPawn.getClass(), new Pawn(board, Color.BLACK).getClass());
			 assertEquals(blackBishop.getClass(), new Bishop(board, Color.BLACK).getClass());
			 assertEquals(whitePawn.getClass(), new Pawn(board, Color.WHITE).getClass());
			 assertEquals(whiteBishop.getClass(), new Bishop(board, Color.WHITE).getClass());
		}catch(IllegalPositionException e) {
			fail("Threw position exception");
		}
		try {
			board.move("d7", "d5");
			board.move("c8", "h3");
			board.move("h3", "g2");
			try {
				assertEquals(board.getPiece("d7"), null);
				assertNotEquals(board.getPiece("d5"), null);
				assertEquals(board.getPiece("g2").getClass(), new Bishop(board, Color.BLACK).getClass());
			}catch(IllegalPositionException e) {}
			
		}catch(IllegalMoveException e) {
			fail("Threw move exception");
		}
	}
	
	@Test
	public void testMoveFail() {
		int count = 0;
		try {
			board.move("d5", "d6");
		}catch(IllegalMoveException e) {
			count++;
		}
		
		try {
			board.move("d7", "a1");
		}catch(IllegalMoveException e) {
			count++;
		}
		
		try {
			board.move("c3", "c2");
		}catch(IllegalMoveException e) {
			count++;
		}
		assertEquals(count, 3);
	}
}
