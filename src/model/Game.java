package model;

public class Game {
	private Grid grid;
	
	public Game() {
		grid=new Grid();
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
