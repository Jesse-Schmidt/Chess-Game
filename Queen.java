package a9;

import java.util.ArrayList;

public class Queen extends ChessPiece{
	
	public Queen(ChessBoard board, Color color){
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color == Color.BLACK) {
			return "\u265B";
		}
		else {
			return "\u2655";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		return new ArrayList<String>();
	}
}
