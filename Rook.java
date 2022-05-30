package a9;

import java.util.ArrayList;

public class Rook extends ChessPiece{
	
	public Rook(ChessBoard board, Color color){
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color == Color.BLACK) {
			return "\u265C";
		}
		else {
			return "\u2656";
		}
	}
	
	private ArrayList<String> trackLine(int xMove, int yMove){
		ArrayList<String> moves = new ArrayList<String>();
		while(this.row + xMove >= 0 && this.row + xMove <= 7 && this.column + yMove >= 0 && this.column + yMove <= 7) {
			this.row += xMove;
			this.column += yMove;
			try {
				ChessPiece test = this.board.getPiece(this.getPosition());
				if(test == null) {
					moves.add(this.getPosition());
				}
				if(test != null && test.getColor() != this.color) {
					moves.add(this.getPosition());
					return moves;
				}
				if(test != null && test.getColor() == this.color) {
					return moves;
				}
			}catch (IllegalPositionException e) {
				return moves;
			}
		}
		return moves;
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		String startingPosition = this.getPosition();
		int[][] directions = {{1,0}, {-1,0}, {0,-1}, {0,1}};
		for(int i = 0; i < 4; i++) {
			ArrayList<String> temp = this.trackLine(directions[i][0], directions[i][1]);
			if(temp.size() > 0) {
				for(String move : temp) {
					moves.add(move);
				}
			}
			try {
				this.setPosition(startingPosition);
			}catch (IllegalPositionException e) {}
		}
		return moves;
	}
}
