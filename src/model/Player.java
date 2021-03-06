package model;

public class Player {
	//Attributes
	private String nickname;
	private String symbol;
	private int movements;
	private double puntaje;
	private String rivalsSymbols;
	
	//Relation
	private Game game;
	
	private Box boxUbication;
	private Player nextPlayer;
	private Player left;
	private Player right;
	

	/**
	 * <b>Name: </b>Player<br>
	 * This method is the constructor of a Player.<br>
	 * <b>Pos: </b> The Player was created successfully.<br>
	 * @param s String. Symbol of the player.
	 * @param box Box. Box where the player is.
	 */
	public Player(String s, Box box) {
		nickname="";
		symbol = s;
		movements = 0;
		boxUbication = box;
		setPuntaje(0);
		setRivalsSymbols("");
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
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public String getRivalsSymbols() {
		return rivalsSymbols;
	}
	public void setRivalsSymbols(String rivalsSymbols) {
		this.rivalsSymbols = rivalsSymbols;
	}
	public double getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}
	
	
}