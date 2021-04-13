package model;

public class Grid {
	private Box initial;
	private int rows;
	private int columns;
	int counterBoxes=1;
	int rowActual;
	Box boxUbication;
	
	public Grid(int rows, int columns) {
		this.rows=rows;
		this.columns=columns;
		createGrid();
	}
	
	private void createGrid() {
		initial= new Box(0,0, String.valueOf(counterBoxes));
		counterBoxes++;
		createRow(0,0,initial);
		
	}

	private void createRow(int i, int j, Box currentFirstRow) {
		//System.out.println("En la fila "+i);
		createColumn(i,j+1,currentFirstRow);
		if(i+1<rows) {
			Box downFirstRow= new Box(i+1,j,String.valueOf(counterBoxes));
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			counterBoxes++;
			createRow(i+1, j, downFirstRow);
		}
		
	}

	private void createColumn(int i, int j, Box prev) {
		if(j<columns) {
			//System.out.println("	Se creo la columna "+j);
			Box current= new Box(i,j,String.valueOf(counterBoxes));
			current.setPrevious(prev);
			prev.setNext(current);
			counterBoxes++;
			createColumn(i, j+1, current);
		}
		
	}

	public Box getInitial() {
		return initial;
	}

	public void setInitial(Box initial) {
		this.initial = initial;
	}

	public int getCounterBoxes() {
		return counterBoxes;
	}

	public void setCounterBoxes(int counterBoxes) {
		this.counterBoxes = counterBoxes;
	}
	
	public String toString() {
		String message;
		message=toStringRow(initial);
		return message;
		
	}

	private String toStringRow(Box row) {
		String message="";
		if(row!=null) {
			message=toStringCol(row)+"\n";
			message+= toStringRow(row.getDown());
		}
		
		return message;
	}

	private String toStringCol(Box current) {
		String message="";
		if(current!=null) {
			message= current.toString();
			message+=toStringCol(current.getNext());
		}
		return message;
	}
	
	public void assignNumbers() {	
		int counterBoxesx=1;
		boxUbication=box1Ubication(initial);
		boxUbication.setContent(String.valueOf(counterBoxesx));
		int rowInicial=boxUbication.getRow()+1;
		rowActual=rowInicial;
		System.out.println("**TIENE ARRIBA?? "+rowActual+" BOX UBICATION PREV: "+boxUbication.getNext().getUp());
		System.out.println("rowInicial: "+rowInicial+" cantidad rows: "+rows);
		int i=1;
		boolean salir=false;
		
	while (salir==false) {	
		if(rowInicial%2==0) {
			if(rowActual%2==0) {//next
				//next
			}
			else {//pevious
				//previous
			}
		}
		else {
			if(rowActual%2==0) {//previous
				System.out.println("**ROW ACTUAL ES PAR "+rowActual+" BOX UBICATION: "+boxUbication);
				if(boxUbication.getUp()!=null) {//miro si cuando está el ultimo elemento de la fila tiene uno arriba de el
					System.out.println("**TIENE ARRIBA"+rowActual+" BOX UBICATION PREV: "+boxUbication.getUp());
					boxUbication=boxUbication.getUp();
					boxUbication.setContent(String.valueOf(counterBoxesx));
					counterBoxesx++;
				}
				if(boxUbication!=null && boxUbication.getRow()==(rowActual-i)) {
					counterBoxesx++;
					changeRowPreviousContent(boxUbication, counterBoxesx, rowActual-i);	
				}
				rowActual--;
				salir=true;
			}
			else {//next
				System.out.println("**ROW ACTUAL ES IMPAR "+rowActual);
				if(boxUbication!=null && boxUbication.getRow()==(rowActual-i)) {
					counterBoxesx++;
					changeRowNextContent(boxUbication, counterBoxesx, rowActual-i);	
				}
				rowActual--;
			}
		}
	}
		
		
		
		
	}
	
	public Box box1Ubication(Box initialP) {
		Box box1=initialP;
		
		if(box1!=null && box1.getDown()!=null) {
				box1=box1.getDown();
				box1=box1Ubication(box1);
		}

		return box1;	
	}
	
	public void changeRowNextContent(Box current, int content, int row) {
		if(current.getRow()==row && current.getNext()!=null) {
			System.out.println("current entro al cilo next "+current);
			current.getNext().setContent(String.valueOf(content));
			boxUbication=current.getNext();
			changeRowNextContent(current.getNext(), content+1, row);
		}
	}
	public void changeRowPreviousContent(Box current, int content, int row) {
		if(current.getRow()==row && current.getPrevious()!=null) {
			System.out.println("current entro al cilo prev "+current);
			current.getPrevious().setContent(String.valueOf(content));
			boxUbication=current.getNext();
			changeRowNextContent(current.getPrevious(), content+1, row);
		}
	}
	
	
}
