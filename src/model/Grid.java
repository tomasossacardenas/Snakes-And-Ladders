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
		System.out.println("en createRow con la fila "+i);
		createCol(i,j+1,currentFirstRow,currentFirstRow.getUp());
		if(i+1<rows) {
			Box downFirstRow = new Box(i+1,j, String.valueOf(counterBoxes));
			counterBoxes++;
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}

	private void createCol(int i, int j, Box prev, Box rowPrev) {
		if(j<columns) {
			System.out.println("   en createCol con la columna "+j);
			Box current = new Box(i, j, String.valueOf(counterBoxes));
			counterBoxes++;
			current.setPrevious(prev);
			prev.setNext(current);
			
			if(rowPrev!=null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			
			createCol(i,j+1,current,rowPrev);
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
	
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getRowActual() {
		return rowActual;
	}

	public Box getBoxUbication() {
		return boxUbication;
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

	
	public void assignNumbers(Box current, int content, int row) {//el primero será box1Ubication(Initial), 1, rows
		current.setContent(String.valueOf(content));
		//System.out.println("Cambie Contenido"+current);
		
		if(rows%2==0) {//Si la cantidad de filas es par ,pares hacia derecha impares hacia izquierda, se termina en 0,0
			if(row==1 && current.getColumn()==0) {//aqui estara en 0,0 CASO BASE
				current.setContent(String.valueOf(content));
			}
			else {
				if(row%2==0) {//Si la fila actual es par, hacia izquierda
					//System.out.println("ESTAMOS EN FILA PAR "+row);
					if(current.getNext()!=null) {//Si no es el ultimo de la fila impar
						//System.out.println("**Cambie el de ahora a"+current);
						current=current.getNext();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row+")");
						assignNumbers(current, content++,row);
					}
					else {//si es el ultimo de la fila impar
						if(current.getUp()!=null) {//si no está en la row 0
							//System.out.println("**Cambie el de ahora a"+current);
							current=current.getUp();
							//System.out.println("Realizo metodo otra vez con el de arriba ("+current+","+(content++)+","+row+")");
							assignNumbers(current, content++,row-1);
						}
					}
				}
				else {// Si la fila actual es impar, hacia derecha
					//
					//System.out.println("ESTAMOS EN FILA IMPAR "+row);
					if(current.getPrevious()!=null) {//Si no es el ultimo de la fila par
						current=current.getPrevious();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row);
						assignNumbers(current, content++,row);
					}
					else {//si es el ultimo de la fila par
						if(current.getUp()!=null) {//si no está en la row 0
							current=current.getUp();
							assignNumbers(current, content+1,row-1);
						}
					}
					
				}
			}
		}
		else {// si la cantidad de filas es impar, impares hacia derecha, pares hacia izquierda, se termina en 0,columns-1
			if(row==1 && current.getColumn()==columns-1) {//aqui estara en 0,columns CASO BASE
				//System.out.println("LLEGAMOS AL FINAL EN "+current);
				current.setContent(String.valueOf(content));
			}
			else {
				if(row%2==0) {//Si la fila actual es par, hacia izquierda
					//System.out.println("ESTAMOS EN FILA PAR "+row);
					if(current.getPrevious()!=null) {//Si no es el ultimo de la fila par
						current=current.getPrevious();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row);
						assignNumbers(current, content++,row);
					}
					else {//si es el ultimo de la fila par
						if(current.getUp()!=null) {//si no está en la row 0
							current=current.getUp();
							assignNumbers(current, content+1,row-1);
						}
					}
				}
				else {// Si la fila actual es impar, hacia derecha
					//System.out.println("ESTAMOS EN FILA IMPAR "+row);
					if(current.getNext()!=null) {//Si no es el ultimo de la fila impar
						//System.out.println("**Cambie el de ahora a"+current);
						current=current.getNext();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row+")");
						assignNumbers(current, content++,row);
					}
					else {//si es el ultimo de la fila impar
						if(current.getUp()!=null) {//si no está en la row 0
							//System.out.println("**Cambie el de ahora a"+current);
							current=current.getUp();
							//System.out.println("Realizo metodo otra vez con el de arriba ("+current+","+(content++)+","+row+")");
							assignNumbers(current, content++,row-1);
						}
					}
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
	
	
	
}
