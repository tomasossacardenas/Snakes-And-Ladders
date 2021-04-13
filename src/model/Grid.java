package model;

public class Grid {
	private Box initial;
	private int rows;
	private int columns;
	int counterBoxes=1;
	int rowActual;
	Box boxUbication;
	Box first;
	Box vertical;
	
	public Grid(int rows, int columns) {
		this.rows=rows;
		this.columns=columns;
		createGrid();
	}
	
	private void createGrid() {
		initial= new Box(0,0, String.valueOf(counterBoxes));
		counterBoxes++;
		boxUbication=initial;
		first=initial;
		vertical=initial;
		createRow(0,0,initial);
		
	}

	private void createRow(int i, int j, Box currentFirstRow) {
		//System.out.println("En la fila "+i);
		if(currentFirstRow.getRow()==0) {//Si es la primera row
			if(currentFirstRow.getNext()==null && currentFirstRow.getDown()==null) {//FALTA PONER QUE ESTE PROCESO SOLO SUCEDE EN LA ROW0
				if(currentFirstRow.getColumn()<columns-1) {
					System.out.println("CURRENT COLUMNA "+currentFirstRow+","+currentFirstRow.getColumn()+" Es menor a columns");
					Box boxNext=new Box(i, j+1, String.valueOf(counterBoxes));//casilla del lado en la misma fila
					currentFirstRow.setNext(boxNext);
					boxNext.setPrevious(currentFirstRow);
					counterBoxes++;
					Box boxDown=new Box(i+1, j, String.valueOf(counterBoxes));//casilla del abajo en la misma columna
					currentFirstRow.setDown(boxDown);
					boxDown.setUp(currentFirstRow);
					counterBoxes++;
					System.out.println("**COLUMNA "+currentFirstRow+",NEXT: "+currentFirstRow.getNext()+",DOWN: "+currentFirstRow.getDown());
					createRow(i, j+1, boxNext);


				}
				else {
					if(currentFirstRow.getRow()<rows-1) {
						System.out.println("CURRENT ROW "+currentFirstRow.getRow()+" Es menor a rows");
						Box boxDown=new Box(i+1, j, String.valueOf(counterBoxes));//casilla del abajo en la misma columna
						currentFirstRow.setDown(boxDown);
						boxDown.setUp(currentFirstRow);
						System.out.println("CURRENT ROW "+currentFirstRow+" Se le añadio abajo a "+currentFirstRow.getDown());
						counterBoxes++;

						if(first.getDown()!=null) {
							System.out.println("UBICACION ABAJO "+boxUbication.getDown());
							first=first.getDown();
							vertical=first;
							createRow(i+1, j, first);
						}
					}
				}
			}
		}
		else {
			System.out.println("***************SALIMOS DE LA FILA 0****************");
			//PASAR DE FIRST=1 A FIRST=3
			//CAMBIAR 3.SETNEXT A 5(BOXUBICATION.GETNEXT.GETDOWN) TODO SIEMPRE Y CUANDO BOXUBICATION.GETNEXT!=NULL que significa que seria el ultimo de la fila
			//CREAR 3.SETDOWN A 11 SIEMPRE Y CUANDO 3(FIRST) NO ESTE EN LA ULTIMA FILA
			//PASAR FIRST DE 3-5 Y BOXUBICATION A BOXUBICATION.GETNEXT
			
			if(first!=null) {
				System.out.println("el que esta abajo de "+first+" no es null");
				if(boxUbication.getNext()!=null) {//CAMBIAR 3.SETNEXT A 5(BOXUBICATION.GETNEXT.GETDOWN) TODO SIEMPRE Y CUANDO BOXUBICATION.GETNEXT!=NULL que significa que seria el ultimo de la fila
					first.setNext(boxUbication.getNext().getDown());
				}
				if(first.getRow()<rows) {
					Box boxDown= new Box(first.getRow()+1,first.getColumn(), String.valueOf(counterBoxes));//nuevo box en una fila mas en la misma columna
					first.setDown(boxDown);
				}
				if(first.getNext()!=null) {
					first=first.getNext();
					boxUbication=boxUbication.getNext();
					createRow(i, j, first);
				}
			}
			
			else {//si first es el ultimo de la fila necesito que se vaya para la siguiente	

			}
		}
			
		/*
			if(currentFirstRow.getColumn()<columns) {
				currentFirstRow.setNext(boxUbication.getNext().getDown());//3-5
				boxUbication.getNext().getDown().setPrevious(currentFirstRow);//5-3
				boxUbication=boxUbication.getNext();
			
			if(first.getDown()==null && first.getRow()<rows-1) {
				Box boxDown= new Box(i, j, String.valueOf(counterBoxes));
				first.setDown(boxDown);
				System.out.println("CURRENT ROW "+currentFirstRow+" Se le añadio al lado "+currentFirstRow.getNext()+ " y abajo "+currentFirstRow.getDown());
			}
			System.out.println("ESTAMOS EN LA ROW "+boxUbication.getRow()+" COLUMNA "+boxUbication.getColumn()+" i="+i+" j="+j);
			
			createRow(i,j+1, boxUbication);
			}
		}
		

			
		
		createColumn(i,j+1,currentFirstRow);
		if(i+1<rows) {
			Box downFirstRow= new Box(i+1,j,String.valueOf(counterBoxes));
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			counterBoxes++;
			createRow(i+1, j, downFirstRow);
		}
		*/
		
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
