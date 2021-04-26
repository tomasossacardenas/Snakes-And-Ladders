package model;

public class Snake {
	//Attributes	
	private String content;

	//Relations
	private Box start;
	private Box end;
	private Snake next;
	private Snake previous;
	
	//Constructor
	/**
	 * <b>Name: </b>Snake<br>
	 * This method is the constructor of a Snake.<br>
	 * <b>Pos: </b> The Snake was created successfully.<br>
	 */
	public Snake() {
		this.start=null;
		this.end=null;
		this.content="";
	}
	
	public Box getStart() {
		return start;
	}
	public void setStart(Box start) {
		this.start = start;
	}
	public Box getEnd() {
		return end;
	}
	public void setEnd(Box end) {
		this.end = end;
	}
	public Snake getNext() {
		return next;
	}
	public void setNext(Snake next) {
		this.next = next;
	}
	public Snake getPrevious() {
		return previous;
	}
	public void setPrevious(Snake previous) {
		this.previous = previous;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	* <b>Name: </b>toString<br>
	* This method prints the content of the snake<br>
	*<b>Pos:</b> The content of the snake was shown<br>
	*@return the content of the snake.
	*/	
	public String toString() {
		return this.content;
	}
	
	
}
