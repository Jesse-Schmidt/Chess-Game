package a9;

import java.util.ArrayList;

public abstract class ChessPiece {
	public enum Color {WHITE, BLACK};
	protected ChessBoard board = null;
	protected int row;
	protected int column;
	protected Color color;
	private String[] letters = {"a","b","c","d","e","f","g","h"};
	
	public ChessPiece(ChessBoard board, Color color) {
		this.board = board;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String getPosition() {
		return this.letters[this.column] + (this.row+1);
	}
	
	public void setPosition(String position) throws IllegalPositionException{
		if(position.length() > 2) {
			throw new IllegalPositionException(position + ": is an invalid position");
		}
		try {
			int newRow = Integer.parseInt(String.valueOf(position.charAt(1)))-1;
			int newColumn = position.charAt(0)-'a';
			if(newRow < 0 || newRow > 7 || newColumn < 0 || newColumn > 7) {
				throw new IllegalPositionException(position + ": is an invalid position");
			}
			else {
				this.row = newRow;
				this.column = newColumn;
			}
		}catch (Exception e) {
			throw new IllegalPositionException(position + ": is an invalid position");
		};
		
	}
	
	abstract public String toString();
	abstract public ArrayList<String> legalMoves();
	
}
