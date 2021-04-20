package model;

public class Player {
	//Attributes
	private String symbol;
	
	
	//Relation
	private Box boxUbication;
	private Player nextPlayer;
	private Player prevPlayer;
	
	public Player(String s, Box box) {
		symbol = s;
		boxUbication = box;
		
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

	public String getSymbol() {
		return symbol;
	}
	

	

	public void setBoxUbication(Box boxUbication) {
		this.boxUbication = boxUbication;
	}
	
	public Box getBoxUbication() {
		return boxUbication;
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