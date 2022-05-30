package a9;

import java.util.ArrayList;

public class Pawn extends ChessPiece{
	
	public Pawn(ChessBoard board, Color color){
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.color == Color.BLACK) {
			return "\u265F";
		}
		else {
			return "\u2659";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		String startingLocation = this.getPosition();
		if(this.color == Color.BLACK) {
			//move forward 1
			for(int columnMove = -1; columnMove <=1; columnMove++) {
				this.row--;
				this.column += columnMove;
				if(this.row >= 0 && this.row <=7 && this.column >= 0 && this.column <=7) {
					try {
						ChessPiece test = this.board.getPiece(this.getPosition());
						if(test == null && columnMove == 0) {
							moves.add(this.getPosition());
							if(this.row == 5) {
								this.row--;
								test = this.board.getPiece(this.getPosition());
								if(test == null) {
									moves.add(this.getPosition());
								}
							}
						}else if(test != null && columnMove !=0 && test.getColor() != this.color) {
							moves.add(this.getPosition());
						}
					}catch (IllegalPositionException e) {}
				}
				try {
					this.setPosition(startingLocation);
				} catch (IllegalPositionException e) {}
			}
		}
		else {
			for(int columnMove = -1; columnMove <=1; columnMove++) {
				this.row++;
				this.column += columnMove;
				if(this.row >= 0 && this.row <=7 && this.column >= 0 && this.column <=7) {
					try {
						ChessPiece test = this.board.getPiece(this.getPosition());
						if(test == null && columnMove == 0) {
							moves.add(this.getPosition());
							if(this.row == 2) {
								this.row++;
								test = this.board.getPiece(this.getPosition());
								if(test == null) {
									moves.add(this.getPosition());
								}
							}
						}else if(test != null && columnMove !=0 && test.getColor() != this.color) {
							moves.add(this.getPosition());
						}
					}catch (IllegalPositionException e) {}
				}
				try {
					this.setPosition(startingLocation);
				} catch (IllegalPositionException e) {}
			}
		}
		return moves;
	}
}
