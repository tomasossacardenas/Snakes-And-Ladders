package model;

public class Box {
	//Attributes	
	private int row;
	private int column;
	private int boxNumber;
	private String content;
	
	//Relations
	private Box previous;
	private Box next;
	private Box up;
	private Box down;
	private Snake snake;
	private Ladder ladder;
	
	//Constructor #1
	public Box(int row, int column) {
		this.row=row;
		this.column=column;
		this.content="";
		this.snake=null;
		this.ladder=null;
		this.boxNumber=0;
	}
	//Constructor #2
	public Box(int row, int column, String content) {
		this.row=row;
		this.column=column;
		this.content=content;
		this.snake=null;
		this.ladder=null;
		this.boxNumber=0;
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
	
	public String toString() {
		return "["+this.content+"]";
		
	}
	
}
