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
	
	public String toString() {
		return this.content;
	}
	
	
}
