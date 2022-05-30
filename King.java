package a9;

import java.util.ArrayList;

public class King extends ChessPiece{
	
	public King(ChessBoard board, Color color){
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color == Color.BLACK) {
			return "\u265A";
		}
		else {
			return "\u2654";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		int[][] directions = {{-1,0},{-1,-1}, {0,-1}, {-1,1}, {1,0}, {1,1}, {0,1}, {1,-1}};
		ArrayList<String> moves = new ArrayList<String>();
		String startingPosition = this.getPosition();
		for(int[] i : directions) {
			this.row += i[0];
			this.column += i[1];
			if(this.row >=0 && this.row <= 7 && this.column >= 0 && this.column <= 7) {
				try {
					ChessPiece test = this.board.getPiece(this.getPosition());
					if(test == null || test.getColor() != this.color) {
						moves.add(this.getPosition());
					}
				}catch (IllegalPositionException e) {}
			}
			try {
				this.setPosition(startingPosition);
			}catch (IllegalPositionException e) {}
		}
		return moves;
	}
}
