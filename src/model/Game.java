package model;

public class Game {
	//Relations
	private Grid grid;
	private Player firstPlayer;
	
	//Constructor 
	public Game(int rows, int columns, int snakesNumber, int laddersNumber,String []parts) {
		firstPlayer=null;
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);
		createPlayers(firstPlayer,parts[parts.length-1],0,parts[parts.length-1].length());
	}

	public Grid getGrid() {
		return grid;
	}
	
	public void createPlayers(Player pPlayer,String parts,int i,int p) {
		//* ! O X % $ # + &
		int players = p;
		char symbol;

		if (parts!=null && i<parts.length()) {			
			symbol = parts.charAt(i);
			//System.out.println("Symbol: "+symbol);
			//System.out.println("parts.length()="+parts.length());

			if (players==0) {
				System.out.println("Se han creado todos los jugadores");
			}else {
				if (firstPlayer==null) {
					i+=1;
					firstPlayer = new Player(symbol);	
					players--;
					createPlayers(firstPlayer,parts,i,players);
				}else {				
					i+=1;
					Player player = new Player(symbol);
					player.setPrevPlayer(pPlayer);
					pPlayer.setNextPlayer(player);
					createPlayers(player,parts,i,--players);
				}
			}
		}	

		
	}		
	
	
	/*
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
	*/
}
