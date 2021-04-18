package model;

public class Game {
	//Relations
	private Grid grid;
	
	//Constructor 
	public Game(int rows, int columns, int snakesNumber, int laddersNumber) {
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);
	}

	public Grid getGrid() {
		return grid;
	}
	
	public String showGrid() {
		String cuadricula="";
		
		if(grid.getInitial()!=null) {
			Box casillaAux=grid.getInitial();
			int counter=1;//CONTADOR PARA SALTO DE LINEA PARA SEPARACION DE FILAS
			
			while(casillaAux!=null) {
				if(counter<5) {
					cuadricula+="["+casillaAux.getContent()+"]";
					casillaAux=casillaAux.getNext();
					counter++;
				}
				else {
					cuadricula+="\n";
					counter=1;
				}
			}
		}
		else {
			cuadricula="[]";
		}
		return cuadricula;
	}
}
