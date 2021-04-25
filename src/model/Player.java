package model;

public class Player {
	//Attributes
	private String nickname;
	private String symbol;
	private int movements;	
	
	//Relation
	private Box boxUbication;
	private Player nextPlayer;
	private Player left;
	private Player right;
	
	
	public Player(String s, Box box) {
		nickname="";
		symbol = s;
		movements = 0;
		boxUbication = box;
		
	}
	public void setNickName(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickName() {
		return nickname;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

	public String getSymbol() {
		return symbol;
	}
	
	public void setMovements(int movements) {
		this.movements = movements;
	}
	
	public int getMovements() {
		return movements;
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
	public Player getLeft() {
		return left;
	}
	public void setLeft(Player left) {
		this.left = left;
	}
	public Player getRight() {
		return right;
	}
	public void setRight(Player right) {
		this.right = right;
	}
	
	
}