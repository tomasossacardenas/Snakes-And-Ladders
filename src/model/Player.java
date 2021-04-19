package model;

public class Player {
	//Attributes
	private char symbol;
	
	//Relation
	private Player nextPlayer;
	private Player prevPlayer;
	
	public Player(char s) {
		symbol=s;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	public Player getNextPlayer() {
		return nextPlayer;
	}
	
	public void setPrevPlayer(Player prevPlayer) {
		this.prevPlayer = prevPlayer;
	}

	public Player getPrevPlayer() {
		return prevPlayer;
	}
}