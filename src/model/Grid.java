package model;

public class Grid{
	//Attributes
	private String positionsGrid;
	private int rows;
	private int columns;
	private int snakesNumber;
	private int counterBoxes=1;
	private int rowActual;
	
	//Relations
	private Snake firstSnake;
	private Ladder firstLadder;
	private Box initial;
	private Box biggestBox;
	private Box boxUbication;
	
	//Constructor
	/**
	 * <b>Name: </b>Grid<br>
	 * This method is the constructor of the Grid.<br>
	 * <b>Pos: </b>The grid was created successfully.
	 * @param rows int. Amount of rows. rows!=0.
	 * @param columns int. Amount of columns. columns!=0.
	 * @param snakesNumber int. Amount of snakes. snakesNumber!=0.
	 * @param laddersNumber int. Amount of ladder. laddersNumber!=0.	
	 */
	public Grid(int rows, int columns, int snakesNumber, int laddersNumber) {
		this.rows=rows;
		this.columns=columns;
		this.firstSnake=null;
		this.firstLadder=null;
		this.setSnakesNumber(snakesNumber);
		
		createGrid();
		assignNumbers(box1Ubication(initial), 1, rows);
        System.out.println(getPositionsGrid());
		biggestBox = findBiggestBox();
		//System.out.println(toString());
		
		createSnakes(snakesNumber, firstSnake);
		assignSnakes(firstSnake);
		
		createLadders(laddersNumber, firstLadder);
		assignLadders(firstLadder);
		
		setPositionsGrid(this.toString());
		
	}
	
	public void setBiggestBox(Box biggestBox) {
		this.biggestBox = biggestBox;
	}
	
	public Box getBiggestBox() {
		return biggestBox;
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
	public String getPositionsGrid() {
		return positionsGrid;
	}

	public void setPositionsGrid(String positionsGrid) {
		this.positionsGrid = positionsGrid;
	}
	
	
	/**
	* <b>Name: </b>createGrid<br>
	* This method creates a grid. <br>
	*<b>Pre:</b> Method createRow(int, int, Box) must be already created<br>
	*<b>Pos:</b> The grid has been created correctly<br>
	*/
	private void createGrid() {
		initial= new Box(0,0, String.valueOf(counterBoxes));
		counterBoxes++;
		createRow(0,0,initial);
		
	}
	
	/**
	* <b>Name: </b>createRow<br>
	* This method creates a new Row. <br>
	*<b>Pre:</b> Method createColumn must be already created<br>
	*<b>Pos:</b> The row has been created correctly<br>
	*@param i int. It's the integer number of the row.
	*@param j int. It's the integer number of the column number.
	*@param currentFirstRow Box. It's the Box of the firstBox of the row i.
	*/
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
	
	/**
	* <b>Name: </b>createCol<br>
	* This method creates a new Column. <br>
	*<b>Pos:</b> The column has been created correctly<br>
	*@param i int. It's the integer number of the row.
	*@param j int. It's the integer number of the column.
	*@param prev Box. It's the Box previous to the new Box.
	*@param rowPrev Box. It's the Box previous in the upRow of the new Box.
	*/
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
	
	/**
	* <b>Name: </b>createLadders<br>
	* This method creates the ladders. <br>
	*<b>Pos:</b> All ladders has been created<br>
	*@param number int. It's the integer number of ladders remaining to create.
	*@param ladder Ladders. It's the firstLadder, could be null if there has not been created a ladder yet.
	*/
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
	
	/**
	* <b>Name: </b>createSnakes<br>
	* This method creates the Snakes. <br>
	*<b>Pos:</b> All snakes has been created<br>
	*@param number int. It's the integer number of snakes remaining to create.
	*@param snake is Snake.It's the firstSnake, could be null if there has not been created a snake yet.
	*/
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
	
	/**
	* <b>Name: </b>assignLadders<br>
	* This method assigns the start and final of all Ladders. <br>
	*<b>Pos:</b> All ladders has been assigned in the grid with a star and an ending<br>
	*@param actualLadder Ladder.It's the ladder to be worked on, actualLadder!=null.
	*/
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

	/**
	* <b>Name: </b>chooseInitialBoxForLadder<br>
	* This method choose where the ladder is going to start. <br>
	*<b>Pos:</b> The ladder has a box where to start<br>
	*@param actualLadder Ladder. It's the ladder to assign a start (row, column), actualLadder!=null.
	*/
	public void chooseInitialBoxForLadder(Ladder actualLadder) {// con este metodo asigno si o si la casilla inicial de la serpiente
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnI=(int) (Math.random() * (columns + 1 - 1)) + 1;
		int filaI=(int) (Math.random() * (((rows) + 1) - 2)) + 2;
		//System.out.println("Numeros aleatorios Iniciales Ladder("+filaI+","+columnI+")");
		
		//Validacion para que ninguna escalera termine en la ultima casilla
		if (columnI==biggestBox.getColumn()+1 && filaI==biggestBox.getRow()+1) {
			chooseInitialBoxForLadder(actualLadder);
		}else {
			Box boxInicial=findBoxCoordenates(initial,filaI, columnI, false);
			//System.out.println("PIRNT BOX INITIAL"+ boxInicial);
			
			//Validación para que ninguna escalera quede en la casilla 1
			if (boxInicial.getBoxNumber()==1) {
				chooseInitialBoxForLadder(actualLadder);
			}else {
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
		}
			
	}
	
	/**
	* <b>Name: </b>chooseInitialBoxForLadder<br>
	* This method choose where the ladder is going to end. <br>
	*<b>Pos:</b> The ladder has a box where to end<br>
	*@param actualLadder Ladder. It's the ladder to assign a end (row, column), actualLadder!=null.
	*/
	public void chooseFinalBoxForLadder(Ladder actualLadder) {
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnF=(int) (Math.random() * (columns + 1 - 1)) + 1;
		//System.out.println("ACTUAL SNAKE START ("+(actualSnake.getStart().getRow()+1)+","+(actualSnake.getStart().getColumn()+1)+")");
		
		int filaF=(int) (Math.random() * ( (1 + 1) - (actualLadder.getStart().getRow()+1-1))) + (actualLadder.getStart().getRow()+1-1);
		//System.out.println("Numeros aleatorios Finales("+filaF+","+columnF+")");
		
		//Validacion para que ninguna escalera inicie en la ultima casilla
		if (columnF==biggestBox.getColumn()+1 && filaF==biggestBox.getRow()+1) {
			chooseFinalBoxForLadder(actualLadder);
		}else {
			Box boxFinal=findBoxCoordenates(initial,filaF, columnF, false);
			//System.out.println("PIRNT BOX FINAL"+ boxFinal);
			
			//Validación para que ninguna escalera quede en la casilla 1
			if (boxFinal.getBoxNumber()==1) {
				chooseInitialBoxForLadder(actualLadder);
			}else {
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
		}
			
	}
	
	/**
	* <b>Name: </b>assignSnakes<br>
	* This method assigns the start and final of all snakes. <br>
	*<b>Pos:</b> All snakes has been assigned in the grid with a start and an ending<br>
	*@param actualSnake Snake. It's the snake to be worked on, actualSnake!=null.
	*/
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
	
	/**
	* <b>Name: </b>chooseInitialBoxForSnake<br>
	* This method choose where the snake is going to start. <br>
	*<b>Pos:</b> The snake has a box where to start<br>
	*@param actualSnake Snake. It's the snake to assign a start (row, column), actualSnake!=null.
	*/
	public void chooseInitialBoxForSnake(Snake actualSnake) {// con este metodo asigno si o si la casilla inicial de la serpiente
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnI=(int) (Math.random() * (columns + 1 - 1)) + 1;
		int filaI=(int) (Math.random() * (((rows-1) + 1) - 1)) + 1;
		//System.out.println("Numeros aleatorios Iniciales("+filaI+","+columnI+")");
		
		//Validación para que ninguna serpiente inicie en la ultima casilla
		if (columnI==biggestBox.getColumn()+1 && filaI==biggestBox.getRow()+1) {
			chooseInitialBoxForSnake(actualSnake);
		}else {
			Box boxInicial=findBoxCoordenates(initial,filaI, columnI, false);
			//System.out.println("PIRNT BOX INITIAL"+ boxInicial);
			
			if (boxInicial.getBoxNumber()==1) {
				chooseInitialBoxForSnake(actualSnake);
			}else {
				if(boxInicial.getSnake()==null && boxInicial.getLadder()==null) {//si la casilla en (filaI, columnI) no tiene serpientes y no tiene escaleras en esa casilla
					//System.out.println("	**Se eligio un inicio para la snake ("+filaI+","+columnI+") content:"+actualSnake.getContent());
					boxInicial.setSnake(actualSnake);
					boxInicial.setContent(boxInicial.getContent()+" "+actualSnake.getContent());
					actualSnake.setStart(boxInicial);
				}
				else {
					chooseInitialBoxForSnake(actualSnake);
				}
			}
			
			
		}
		
		
	}
	
	/**
	* <b>Name: </b>chooseFinalBoxForSnake<br>
	* This method choose where the snake is going to end. <br>
	*<b>Pos:</b> The snake has a box where to end<br>
	*@param actualSnake Snake. It's the snake to assign a end (row, column), actualSnake!=null.
	*/
	public void chooseFinalBoxForSnake(Snake actualSnake) {
		//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
		int columnF=(int) (Math.random() * (columns + 1 - 1)) + 1;
		//System.out.println("ACTUAL SNAKE START ("+(actualSnake.getStart().getRow()+1)+","+(actualSnake.getStart().getColumn()+1)+")");
		
		int filaF=(int) (Math.random() * ( (rows + 1) - (actualSnake.getStart().getRow()+2))) + (actualSnake.getStart().getRow()+2);
		//System.out.println("Numeros aleatorios Finales("+filaF+","+columnF+")");
		
		//Validacion para que ninguna serpiente termine en la ultima casilla
		if (columnF == biggestBox.getColumn()+1 && filaF == biggestBox.getRow()+1) {
			chooseFinalBoxForSnake(actualSnake);
		}else {
			Box boxFinal=findBoxCoordenates(initial,filaF, columnF, false);
			//System.out.println("PIRNT BOX FINAL"+ boxFinal);
			//Validación para que la serpiente no termine en la casilla 1
			if (boxFinal.getBoxNumber()==1) {
				chooseFinalBoxForSnake(actualSnake);
			}else {
				if(boxFinal.getSnake()==null && boxFinal.getSnake()==null) {//si la casilla en (filaI, columnI) no tiene serpientes y escaleras
					//System.out.println("	**Se eligio un final para la snake ("+filaF+","+columnF+")");
					boxFinal.setSnake(actualSnake);
					boxFinal.setContent(boxFinal.getContent()+" "+actualSnake.getContent());
					actualSnake.setEnd(boxFinal);
				}
				else {
					chooseFinalBoxForSnake(actualSnake);
				}
			}
		}		
	}
	
	/**
	* <b>Name: </b>assignNumbers<br>
	* This method assigns the numbers of the boxes in the correct order. <br>
	*<b>Pos:</b> All Boxes have the correct number of box they are<br>
	*@param current Box. It's the Box that is going to be assigned a number, current!=null.
	*@param content int. It's the Integer with the number that is going to have the box.
	*@param row int. It's the number of the row which the box is ubicated.
	*/
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
	
	/**
	* <b>Name: </b>findBoxCoordenates<br>
	* This method finds a box depending on an (x,y) coordenate. <br>
	*<b>Pos:</b> The box has been found<br>
	*@param boxActual Box. It's the box with the recursion is on, it starts with the initial Box, boxActual!=null.
	*@param row int. It's the integer of the number of the coordenate x.
	*@param column int. It's the integer number of the coordenate y.
	*@param salir boolean. It's the boolean that indicates if the box has been found.
	*@return boxActual Box. It's the box in the coordenate(row, column).
	*/
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
		
	//Este método busca el primer elemento la última fila
	/**
	* <b>Name: </b>box1Ubication<br>
	* This method finds the first element in the last row <br>
	*<b>Pos:</b> The first box of the last row has been found<br>
	*@param initialP Box. It's the initialBox.
	*@return box1 Box. It's the box ubicated in the last row and in the first column.
	*/
	public Box box1Ubication(Box initialP) {
		Box box1=initialP;
		
		if(box1!=null && box1.getDown()!=null) {
				box1=box1.getDown();
				box1=box1Ubication(box1);
		}

		return box1;	
	}		
	
	/**
	* <b>Name: </b>findBoxWithNumber<br>
	* This method finds a box with the attribute boxNumber <br>
	*<b>Pos:</b> The first box of the last row has been found<br>
	* @param box Box. It's the initialBox.
	* @param n int. It's the number of the box looked for.
	* @param salir boolean. It's true when find the box, but while doesn't find the box, it will be false.
	* @return box Box. It's the box with the number n.
	*/	
	public Box findBoxWithNumber(Box box, int n,boolean salir) {			
		if (salir==false && box!=null) {
			if (box.getBoxNumber()==n) {
				salir=true;				
			}else {
				if (box.getNext()!=null) {
					return findBoxWithNumber(box.getNext(),n,salir);					
				}else if (box.getNext()==null){					
					return findBoxWithNumberPrev(box.getDown(),n,salir);					
				}				
			}
		}
		return box;		
	}
	
	/**
	* <b>Name: </b>findBoxWithNumberPrev<br>
	* This method finds a box with the attribute boxNumber <br>
	*<b>Pos:</b> The first box of the last row has been found<br>
	*@param box Box. It's the initialBox.
	*@param n int. It's the number of the box looked for.
	*@return box Box. It's the box with the number n.
	*/	
	private Box findBoxWithNumberPrev(Box box, int n, boolean salir) {
		
		if (salir==false && box!=null) {
			if (box.getBoxNumber()==n) {
				salir=true;				
			}else {
				if (box.getPrevious()!=null) {
					return findBoxWithNumberPrev(box.getPrevious(),n,salir);
				}else if (box.getPrevious()==null) {
					return findBoxWithNumber(box.getDown(),n,salir);
				}
			}
		}
		return box;		
	}

	/**
	* <b>Name: </b>deleteNumbersNext<br>
	* This method deletes the number of the box in the content<br>
	*<b>Pos:</b>T he Boxes content doesnt have its numbers<br>
	*@param actualBox Box. It's the box to be cleaned the content.
	*/	
	public void deleteNumbersNext(Box actualBox) {	
		if (actualBox==null) {
			
		}else {
			if (actualBox!=null && actualBox.getNext()!=null) {
				if (actualBox.getBoxNumber()==1) {
					actualBox.setContent(actualBox.getContent().replace("1"," "));
					deleteNumbersNext(actualBox.getNext());	
				}else {
					if (actualBox.getSnake()!=null) {
						actualBox.setContent(actualBox.getSnake().getContent());
						deleteNumbersNext(actualBox.getNext());
					}else if (actualBox.getLadder()!=null) {
						actualBox.setContent(actualBox.getLadder().getContent());
						deleteNumbersNext(actualBox.getNext());				
					}else {
						actualBox.setContent(" ");
						deleteNumbersNext(actualBox.getNext());				
					}			
					
				}
			
			}else if (actualBox.getNext()==null) {			
				if (actualBox.getSnake()!=null) {
					actualBox.setContent(actualBox.getSnake().getContent());				
				}else if (actualBox.getLadder()!=null) {
					actualBox.setContent(actualBox.getLadder().getContent());							
				}else {
					actualBox.setContent(" ");							
				}
				deleteNumbersPrev(actualBox.getDown());	
				//actualBox = actualBox.getPrevious();
			}	
		}			
	}
	
	/**
	* <b>Name: </b>deleteNumbersPrev<br>
	* This method deletes the number of the box in the content<br>
	*<b>Pos:</b> The Boxes content doesn't have its numbers<br>
	*@param actualbox Box. It's the box to be cleaned the content.
	*/		
	private void deleteNumbersPrev(Box actualBox) {
		if (actualBox==null) {
			
		}else {
			if (actualBox!=null && actualBox.getPrevious()!=null) {
				if (actualBox.getBoxNumber()==1) {					;
					actualBox.setContent(actualBox.getContent().replace("1"," "));
					deleteNumbersPrev(actualBox.getPrevious());
				}else {
					if (actualBox.getSnake()!=null) {
						actualBox.setContent(actualBox.getSnake().getContent());
						deleteNumbersPrev(actualBox.getPrevious());
					}else if (actualBox.getLadder()!=null) {
						actualBox.setContent(actualBox.getLadder().getContent());
						deleteNumbersPrev(actualBox.getPrevious());				
					}else {
						actualBox.setContent(" ");
						deleteNumbersPrev(actualBox.getPrevious());				
					}
				}
			
				
			}else if (actualBox.getPrevious()==null) {
				if (actualBox.getSnake()!=null) {
					actualBox.setContent(actualBox.getSnake().getContent());				
				}else if (actualBox.getLadder()!=null) {
					actualBox.setContent(actualBox.getLadder().getContent());							
				}else {
					actualBox.setContent(" ");							
				}
				deleteNumbersNext(actualBox.getDown());
			}
		}
		
	}
		
	
	/**
	* <b>Name: </b>findBiggestBox<br>
	* This method finds the box with the biggest number<br>
	*<b>Pos:</b> The Boxes with the biggest number has been found<br>
	*@return biggestBox Box. It's the biggestBox in the grid.
	*/		
	
	public Box findBiggestBox() {
		Box big = findBoxCoordenates(initial,1,columns, false);

		if (initial.getBoxNumber() > big.getBoxNumber()) {
			biggestBox = initial;
		}else {
			biggestBox = big;
		}
		return biggestBox;
	}
		
	/**
	* <b>Name: </b>toString<br>
	* This method prints the grid<br>
	*<b>Pos:</b> The grid has been converted to a string<br>
	*@return message, the grid in a string
	*/		
	public String toString() {
		String message;
		message=toStringRow(initial);
		return message;
		
	}

	/**
	* <b>Name: </b>toStringRow<br>
	* This method prints the row<br>
	*<b>Pos:</b>The row has been converted to a string<br>
	*@return message, the row in a string
	*/	
	private String toStringRow(Box row) {
		String message="";
		if(row!=null) {
			message=toStringCol(row)+"\n";
			message+= toStringRow(row.getDown());
		}
		
		return message;
	}
	
	/**
	* <b>Name: </b>toStringCol<br>
	* This method prints the column<br>
	*<b>Pos:</b>The column has been converted to a string<br>
	*@return message, the column in a string
	*/	
	private String toStringCol(Box current) {
		String message="";
		if(current!=null) {
			message= current.toString();
			message+=toStringCol(current.getNext());
		}
		return message;
	}

	
	
	
	
}
