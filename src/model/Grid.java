package model;

public class Grid {
	
	private Snake firstSnake;
	private Ladder firstLadder;
	private Box initial;
	private int rows;
	private int columns;
	private int snakesNumber;
	int counterBoxes=1;
	int rowActual;
	Box boxUbication;
	
	public Grid(int rows, int columns, int snakesNumber, int laddersNumber) {
		this.rows=rows;
		this.columns=columns;
		this.firstSnake=null;
		this.setSnakesNumber(snakesNumber);
		
		createGrid();
		assignNumbers(box1Ubication(initial), 1, rows);
		System.out.println(toString());
		
		createSnakes(snakesNumber, firstSnake);
		assignSnakes(firstSnake);
		
		createLadders(laddersNumber, firstLadder);
		assignLadders(firstLadder);
		
	}
	
	public void createLadders(int number, Ladder ladder) {
		if(number==0) {//me creo todas o no hay serpientes
			//System.out.println("------------------NO HAY MAS SERPIENTES");
		}
		else {
			if(ladder==null) {//Voy a crear la primera snake
				//System.out.println("	Se creo la primera Ladder con el contenido "+number);
				firstLadder=new Ladder();
				//firstSnake=snake;
				firstLadder.setContent(String.valueOf(number));
				//System.out.println("Ladder ***__"+firstLadder.toString());
				//System.out.println("Ladder INICIAL***__"+firstLadder.toString());
				createLadders(--number, firstLadder);
			}
			else {// si no es la primera iteracion
				//System.out.println("	Se creo una nueva Ladder:"+number);
				Ladder nextLadder= new Ladder();
				ladder.setNext(nextLadder);
				nextLadder.setContent(String.valueOf(number));
				//System.out.println("Ladder ***__"+nextLadder.toString());
				//System.out.println("Ladder INICIAL***__"+firstLadder.toString());
				createLadders(--number, nextLadder);
			}
		}
	}
	
	public void assignLadders(Ladder actualLadder) {
		if(actualLadder==firstLadder) {
			//System.out.println("ENTRO A ACTUAL==FIRST");
			chooseInitialBoxForLadder(firstLadder);// le asignó le box inicial
			chooseFinalBoxForLadder(firstLadder);
			if(firstLadder.getNext()!=null) {
				//System.out.println("____________________EN UNA NUEVA Ladder_______________");
				assignLadders(firstLadder.getNext());
			}
		}
		else {
		chooseInitialBoxForLadder(actualLadder);// le asignó le box inicial
		chooseFinalBoxForLadder(actualLadder);
		if(actualLadder.getNext()!=null) {
			//System.out.println("____________________EN UNA NUEVA Ladder______________");
			assignLadders(actualLadder.getNext());
		}
		}
		
	}
	
	public void createSnakes(int number, Snake snake) {
		if(number==0) {//me creo todas o no hay serpientes
			//System.out.println("------------------NO HAY MAS SERPIENTES");
		}
		else {
			if(snake==null) {//Voy a crear la primera snake
				//System.out.println("	Se creo la primera Serpiente con el contenido "+number);
				firstSnake=new Snake();
				//firstSnake=snake;
				char content= (char) ('A' + number-1 );
				firstSnake.setContent(String.valueOf(content));
				//System.out.println("SERPIENTE ***__"+firstSnake.toString());
				//System.out.println("SERPIENTE INICIAL***__"+firstSnake.toString());
				createSnakes(--number, firstSnake);
			}
			else {// si no es la primera iteracion
				//System.out.println("	Se creo una nueva Serpiente:"+number);
				Snake nextSnake= new Snake();
				snake.setNext(nextSnake);
				char content= (char) ('A' + number-1);
				nextSnake.setContent(String.valueOf(content));
				//System.out.println("SERPIENTE ***__"+nextSnake.toString());
				//System.out.println("SERPIENTE INICIAL***__"+firstSnake.toString());
				createSnakes(--number, nextSnake);
			}
		}
	}
	
	public void chooseInitialBoxForLadder(Ladder actualLadder) {// con este metodo asigno si o si la casilla inicial de la serpiente
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnI=(int) (Math.random() * (columns + 1 - 1)) + 1;
		int filaI=(int) (Math.random() * (((rows) + 1) - 2)) + 2;
		//System.out.println("Numeros aleatorios Iniciales Ladder("+filaI+","+columnI+")");
		
		Box boxInicial=findBoxCoordenates(initial,filaI, columnI, false);
		//System.out.println("PIRNT BOX INITIAL"+ boxInicial);
		
		if(boxInicial.getSnake()==null && boxInicial.getLadder()==null) {//si la casilla en (filaI, columnI) no tiene serpientes
			//System.out.println("	**Se eligio un inicio para la ladder ("+filaI+","+columnI+") content:"+actualLadder.getContent());
			boxInicial.setLadder(actualLadder);
			boxInicial.setContent(boxInicial.getContent()+" "+actualLadder.getContent());
			actualLadder.setStart(boxInicial);
		}
		else {
			chooseInitialBoxForLadder(actualLadder);
		}
	}
	
	public void chooseFinalBoxForLadder(Ladder actualLadder) {
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnF=(int) (Math.random() * (columns + 1 - 1)) + 1;
		//System.out.println("ACTUAL SNAKE START ("+(actualSnake.getStart().getRow()+1)+","+(actualSnake.getStart().getColumn()+1)+")");
		
		int filaF=(int) (Math.random() * ( (1 + 1) - (actualLadder.getStart().getRow()+1-1))) + (actualLadder.getStart().getRow()+1-1);
		//System.out.println("Numeros aleatorios Finales("+filaF+","+columnF+")");
		
		Box boxFinal=findBoxCoordenates(initial,filaF, columnF, false);
		//System.out.println("PIRNT BOX FINAL"+ boxFinal);
		
		if(boxFinal.getSnake()==null && boxFinal.getLadder()==null) {//si la casilla en (filaI, columnI) no tiene serpientes
			//System.out.println("	**Se eligio un final para la Ladder ("+filaF+","+columnF+")");
			boxFinal.setLadder(actualLadder);
			boxFinal.setContent(boxFinal.getContent()+" "+actualLadder.getContent());
			actualLadder.setEnd(boxFinal);
		}
		else {
			chooseFinalBoxForLadder(actualLadder);
		}
	}
	
	public void assignSnakes(Snake actualSnake) {
		if(actualSnake==firstSnake) {
			//System.out.println("ENTRO A ACTUAL==FIRST");
			chooseInitialBoxForSnake(firstSnake);// le asignó le box inicial
			chooseFinalBoxForSnake(firstSnake);
			if(firstSnake.getNext()!=null) {
				//System.out.println("____________________EN UNA NUEVA SNAKE_______________");
				assignSnakes(firstSnake.getNext());
			}
		}
		else {
		chooseInitialBoxForSnake(actualSnake);// le asignó le box inicial
		chooseFinalBoxForSnake(actualSnake);
		if(actualSnake.getNext()!=null) {
			//System.out.println("____________________EN UNA NUEVA SNAKE_______________");
			assignSnakes(actualSnake.getNext());
		}
		}
		
	}
	
	public void chooseInitialBoxForSnake(Snake actualSnake) {// con este metodo asigno si o si la casilla inicial de la serpiente
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnI=(int) (Math.random() * (columns + 1 - 1)) + 1;
		int filaI=(int) (Math.random() * (((rows-1) + 1) - 1)) + 1;
		//System.out.println("Numeros aleatorios Iniciales("+filaI+","+columnI+")");
		
		Box boxInicial=findBoxCoordenates(initial,filaI, columnI, false);
		//System.out.println("PIRNT BOX INITIAL"+ boxInicial);
		
		if(boxInicial.getSnake()==null) {//si la casilla en (filaI, columnI) no tiene serpientes
			//System.out.println("	**Se eligio un inicio para la snake ("+filaI+","+columnI+") content:"+actualSnake.getContent());
			boxInicial.setSnake(actualSnake);
			boxInicial.setContent(boxInicial.getContent()+" "+actualSnake.getContent());
			actualSnake.setStart(boxInicial);
		}
		else {
			chooseInitialBoxForSnake(actualSnake);
		}
	}
	
	public void chooseFinalBoxForSnake(Snake actualSnake) {
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnF=(int) (Math.random() * (columns + 1 - 1)) + 1;
		//System.out.println("ACTUAL SNAKE START ("+(actualSnake.getStart().getRow()+1)+","+(actualSnake.getStart().getColumn()+1)+")");
		
		int filaF=(int) (Math.random() * ( (rows + 1) - (actualSnake.getStart().getRow()+2))) + (actualSnake.getStart().getRow()+2);
		//System.out.println("Numeros aleatorios Finales("+filaF+","+columnF+")");
		
		Box boxFinal=findBoxCoordenates(initial,filaF, columnF, false);
		//System.out.println("PIRNT BOX FINAL"+ boxFinal);
		
		if(boxFinal.getSnake()==null) {//si la casilla en (filaI, columnI) no tiene serpientes
			//System.out.println("	**Se eligio un final para la snake ("+filaF+","+columnF+")");
			boxFinal.setSnake(actualSnake);
			boxFinal.setContent(boxFinal.getContent()+" "+actualSnake.getContent());
			actualSnake.setEnd(boxFinal);
		}
		else {
			chooseFinalBoxForSnake(actualSnake);
		}
	}
	

	private void createGrid() {
		initial= new Box(0,0, String.valueOf(counterBoxes));
		counterBoxes++;
		createRow(0,0,initial);
		
	}

	private void createRow(int i, int j, Box currentFirstRow) {
		//System.out.println("en createRow con la fila "+i);
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
			//System.out.println("   en createCol con la columna "+j);
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
		current.setBoxNumber(content);
		//System.out.println("Cambie Contenido"+current);
		
		if(rows%2==0) {//Si la cantidad de filas es par ,pares hacia derecha impares hacia izquierda, se termina en 0,0
			if(row==1 && current.getColumn()==0) {//aqui estara en 0,0 CASO BASE
				current.setContent(String.valueOf(content));
				current.setBoxNumber(content);
			}
			else {
				if(row%2==0) {//Si la fila actual es par, hacia izquierda
					//System.out.println("ESTAMOS EN FILA PAR "+row);
					if(current.getNext()!=null) {//Si no es el ultimo de la fila impar
						//System.out.println("**Cambie el de ahora a"+current);
						current=current.getNext();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row+")");
						assignNumbers(current, ++content,row);
					}
					else {//si es el ultimo de la fila impar
						if(current.getUp()!=null) {//si no está en la row 0
							//System.out.println("**Cambie el de ahora a"+current);
							current=current.getUp();
							//System.out.println("Realizo metodo otra vez con el de arriba ("+current+","+(content++)+","+row+")");
							assignNumbers(current, ++content,row-1);
						}
					}
				}
				else {// Si la fila actual es impar, hacia derecha
					//
					//System.out.println("ESTAMOS EN FILA IMPAR "+row);
					if(current.getPrevious()!=null) {//Si no es el ultimo de la fila par
						current=current.getPrevious();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row);
						assignNumbers(current, ++content,row);
					}
					else {//si es el ultimo de la fila par
						if(current.getUp()!=null) {//si no está en la row 0
							current=current.getUp();
							assignNumbers(current, ++content,row-1);
						}
					}
					
				}
			}
		}
		else {// si la cantidad de filas es impar, impares hacia derecha, pares hacia izquierda, se termina en 0,columns-1
			if(row==1 && current.getColumn()==columns-1) {//aqui estara en 0,columns CASO BASE
				//System.out.println("LLEGAMOS AL FINAL EN "+current);
				current.setContent(String.valueOf(content));
				current.setBoxNumber(content);
			}
			else {
				if(row%2==0) {//Si la fila actual es par, hacia izquierda
					//System.out.println("ESTAMOS EN FILA PAR "+row);
					if(current.getPrevious()!=null) {//Si no es el ultimo de la fila par
						current=current.getPrevious();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row);
						assignNumbers(current, ++content,row);
					}
					else {//si es el ultimo de la fila par
						if(current.getUp()!=null) {//si no está en la row 0
							current=current.getUp();
							assignNumbers(current, ++content,row-1);
						}
					}
				}
				else {// Si la fila actual es impar, hacia derecha
					//System.out.println("ESTAMOS EN FILA IMPAR "+row);
					if(current.getNext()!=null) {//Si no es el ultimo de la fila impar
						//System.out.println("**Cambie el de ahora a"+current);
						current=current.getNext();
						//System.out.println("Realizo metodo otra vez con ("+current+","+(content++)+","+row+")");
						assignNumbers(current, ++content,row);
					}
					else {//si es el ultimo de la fila impar
						if(current.getUp()!=null) {//si no está en la row 0
							//System.out.println("**Cambie el de ahora a"+current);
							current=current.getUp();
							//System.out.println("Realizo metodo otra vez con el de arriba ("+current+","+(content++)+","+row+")");
							assignNumbers(current, ++content,row-1);
						}
					}
				}
			}
		}
		
	}
	
	
	
	
	
	public Box findBoxCoordenates(Box boxActual, int row, int column, boolean salir) {//INICIAL : (initial, rowx, columnx, false)
		if(salir==false) {
			//System.out.println("BUSCANDO EN COORDENADAS ("+row+","+column+") a partir de "+boxActual);
			if(boxActual.getRow()+1==row) {
				if(boxActual.getColumn()+1==column) {
					//System.out.println("BOX ENCONTRADO EN LA POSICION ("+row+","+column+") es "+boxActual);
					salir=true;
					return findBoxCoordenates(boxActual, row, column, salir);
				}
				else {// if boxActual.getColumn()+1<column
					return findBoxCoordenates(boxActual.getNext(), row,column, salir);
				}
			}
			else { // if boxActual.getRow()+1<row
				return findBoxCoordenates(boxActual.getDown(), row,column, salir);
			}
		}
		//System.out.println("BOX RETORNADO EN ("+row+","+column+") es "+boxActual);
		return boxActual;
	}
	
	public void addSnakeToBox(Snake snake, Box box) {
		
	}
	
	public Box box1Ubication(Box initialP) {
		Box box1=initialP;
		
		if(box1!=null && box1.getDown()!=null) {
				box1=box1.getDown();
				box1=box1Ubication(box1);
		}

		return box1;	
	}

	public Snake getFirstSnake() {
		return firstSnake;
	}

	public void setFirstSnake(Snake firstSnake) {
		this.firstSnake = firstSnake;
	}

	public int getSnakesNumber() {
		return snakesNumber;
	}

	public void setSnakesNumber(int snakesNumber) {
		this.snakesNumber = snakesNumber;
	}
	
	public void showSnakes(Snake snake) {
		System.out.println("	SERPIENTE "+snake.toString());
		if(snake.getNext()!=null) {
			showSnakes(snake.getNext());
		}
	}
	
	
	
}
