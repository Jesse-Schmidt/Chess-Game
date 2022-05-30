package a9;

import java.util.ArrayList;

import a9.ChessPiece.Color;

class ChessBoard {
	private ChessPiece[][] board;

	//fill the board with null
	public ChessBoard() {
		this.board = new ChessPiece[8][8];
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				this.board[r][c] = null;
			}
		}
	}

	//set up all the chess pieces in the standard locations
	public void initialize() {
		for(int i = 0; i < 8; i++) {
			this.placePiece(new Pawn(this, Color.BLACK), (char)('a'+i)+"7");
		}
		
		for(int i = 0; i < 8; i++) {
			this.placePiece(new Pawn(this, Color.WHITE), (char)('a'+i)+"2");
		}
		
		this.placePiece(new Rook(this, Color.BLACK), "a8");
		this.placePiece(new Rook(this, Color.BLACK), "h8");
		this.placePiece(new Knight(this, Color.BLACK), "b8");
		this.placePiece(new Knight(this, Color.BLACK), "g8");
		this.placePiece(new Bishop(this, Color.BLACK), "c8");
		this.placePiece(new Bishop(this, Color.BLACK), "f8");
		this.placePiece(new King(this, Color.BLACK), "e8");
		this.placePiece(new Queen(this, Color.BLACK), "d8");
		
		this.placePiece(new Rook(this, Color.WHITE), "a1");
		this.placePiece(new Rook(this, Color.WHITE), "h1");
		this.placePiece(new Knight(this, Color.WHITE), "b1");
		this.placePiece(new Knight(this, Color.WHITE), "g1");
		this.placePiece(new Bishop(this, Color.WHITE), "c1");
		this.placePiece(new Bishop(this, Color.WHITE), "f1");
		this.placePiece(new King(this, Color.WHITE), "e1");
		this.placePiece(new Queen(this, Color.WHITE), "d1");
	}

	public ChessPiece getPiece(String position) throws IllegalPositionException {
		int nR;
		int nC;
		
		if(position.length() > 2) {
			throw new IllegalPositionException(position + ": is an invalid position");
		}
		
		try {
			//make sure that location exists on the board
			nR = Integer.parseInt(String.valueOf(position.charAt(1)))-1;
			nC = position.charAt(0)-'a';
			if(nR < 0 || nR > 7 || nC < 0 || nC > 7) {
				throw new IllegalPositionException(position + ": is an invalid position");
			}
		}catch (Exception e) {
			throw new IllegalPositionException(position + ": is an invalid position");
		};
		
		return board[nR][nC];
	}

	public boolean placePiece(ChessPiece piece, String position) {
		ChessPiece current;
		
		try {
			//select the location and check what is on it
			current = this.getPiece(position);
		}catch (IllegalPositionException e) {
			return false;
		}
		
		//if the space is not empty and occupied by the same piece color
		if(current != null && current.getColor() == piece.getColor()) {
			return false;
		}
		
		//check if location exists on the board
		int nR = Integer.parseInt(String.valueOf(position.charAt(1)))-1;
		int nC = position.charAt(0)-'a';
		this.board[nR][nC] = piece;
		
		try{
			piece.setPosition(position);
		}catch(IllegalPositionException e) {
			return false;
		}
		
		return true;
	}

	public void move(String fromPosition, String toPosition) throws IllegalMoveException {
		ChessPiece movingPiece;
		ChessPiece destination;
		
		try {
			movingPiece = this.getPiece(fromPosition);
			destination = this.getPiece(toPosition);
		}catch (IllegalPositionException e) {
			throw new IllegalMoveException("Move is invalid");
		}
		
		if(movingPiece == null) {
			throw new IllegalMoveException("Move is invalid");
		}
		
		//if the space is not empty and occupied by the same piece color
		if(destination != null && destination.getColor() == movingPiece.getColor()) {
			throw new IllegalMoveException("Move is invalid");
		}
		
		ArrayList<String> legalMoves = movingPiece.legalMoves();
		
		if(!legalMoves.contains(toPosition)) {
			throw new IllegalMoveException("Move is invalid");
		}
		
		try {
			movingPiece.setPosition(toPosition);
			int nR = Integer.parseInt(String.valueOf(toPosition.charAt(1)))-1;
			int nC = toPosition.charAt(0)-'a';
			this.board[nR][nC] = movingPiece;
			nR = Integer.parseInt(String.valueOf(fromPosition.charAt(1)))-1;
			nC = fromPosition.charAt(0)-'a';
			this.board[nR][nC] = null;
		}catch(IllegalPositionException e) {
			throw new IllegalMoveException("Move is invalid");
		}
		
	}

	public String toString(){
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i<7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + " \u3000 ";
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}

}
