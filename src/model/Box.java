package model;

public class Box {
	Box previous;
	Box next;
	Box up;
	Box down;
	private int row;
	private int column;
	private String content;
	
	public Box(String content, Box previous, Box next) {
		this.setContent(content);
		this.previous=previous;
		this.setNext(next);
	}
	public Box(String content) {
		this.setContent(content);
		previous=null;
		this.next=null;
	}
	public Box(int row, int column, String content) {
		this.row=row;
		this.column=column;
		this.content=content;
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
	
	public String toString() {
		return "["+this.content+"]";
		
	}
}
