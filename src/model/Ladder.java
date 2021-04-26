package model;

public class Ladder {
	//Attributes
	private String content;

	//Relations
	private Box start;
	private Box end;
	private Ladder next;
	private Ladder previous;
	
	//Constructor
	/**
	 * <b>Name: </b>Ladder<br>
	 * This method is the constructor of a Ladder.<br>
	 * <b>Pos: </b> The Ladder was created successfully.<br>
	 */
	public Ladder() {
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
	public Ladder getNext() {
		return next;
	}
	public void setNext(Ladder next) {
		this.next = next;
	}
	public Ladder getPrevious() {
		return previous;
	}
	public void setPrevious(Ladder previous) {
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
	* This method prints the content of the ladder<br>
	*<b>Pos:</b> The content of the ladder was shown<br>
	*@return the content of the ladder.
	*/	
	public String toString() {
		return this.content;
	}
}
