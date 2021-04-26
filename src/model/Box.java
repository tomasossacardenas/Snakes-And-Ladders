package model;

public class Box {
	//Attributes	
	private int row;
	private int column;
	private int boxNumber;
	private String content;
	private boolean player;
	
	//Relations
	private Box previous;
	private Box next;
	private Box up;
	private Box down;
	private Snake snake;
	private Ladder ladder;
	
	
	//Constructor
	/**
	 * <b>Name: </b>Box<br>
	 * This method is the constructor of a Box.<br>
	 * <b>Pos: </b> The box was created successfully.<br>
	 * @param row int. Row where the box is.
	 * @param column int. Column where the box is.
	 * @param content String. Content of the Box.
	 */
	public Box(int row, int column, String content) {
		this.row=row;
		this.column=column;
		this.content=content;
		player=false;
		this.snake=null;
		this.ladder=null;
		
		this.boxNumber=0;
	}
	
	public void setPlayer(boolean player) {
		this.player = player;
	}
	public boolean getPlayer() {
		return player;
	}
	
	public int getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}
	
	public Ladder getLadder() {
		return ladder;
	}
	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Box getNext() {
		return next;
	}
	public void setNext(Box next) {
		this.next = next;
	}
	public Box getPrevious() {
		return previous;
	}
	public void setPrevious(Box previous) {
		this.previous = previous;
	}
	public Box getUp() {
		return up;
	}
	public void setUp(Box up) {
		this.up = up;
	}
	public Box getDown() {
		return down;
	}
	public void setDown(Box down) {
		this.down = down;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	public Snake getSnake() {
		return snake;
	}
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	
	/**
	* <b>Name: </b>toString<br>
	* This method prints the content of the Box<br>
	*<b>Pos:</b> The content of the Box was shown<br>
	*@return the content of the Box.
	*/
	public String toString() {
		return "["+this.content+"]";
		
	}
	
}
