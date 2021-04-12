package model;

public class Box {
	Box previous;
	Box next;
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
	

}
