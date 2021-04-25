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
	
	public String toString() {
		return this.content;
	}
}
