package model;

public class Game {	
	//Relations
	private Grid grid;
	private Player firstPlayer;
	
	//Constructor 
	public Game(int rows, int columns, int snakesNumber, int laddersNumber,String parts) {			
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);
		createPlayers(firstPlayer,parts,0,parts.length());
	}

	public Grid getGrid() {
		return grid;
	}
	
	public void createPlayers(Player pPlayer,String parts,int i,int p) {
		//* ! O X % $ # + &
		int players = p;
		String symbol;

		if (parts!=null && i<parts.length()) {			
			symbol = Character.toString(parts.charAt(i));
			//System.out.println("Symbol: "+symbol);
			//System.out.println("parts.length()="+parts.length());

			if (players==0) {
				System.out.println("Se han creado todos los jugadores");
			}else {
				if (firstPlayer==null) {
					i+=1;						
					Box box = grid.findBoxWithNumber(grid.getInitial(), 1, false);
					firstPlayer = new Player(symbol, box);
					box.setContent(box.getContent()+firstPlayer.getSymbol());
					box.setPlayer(true);
					players--;
					createPlayers(firstPlayer,parts,i,players);
					
				}else {				
					i+=1;					
					Box box = grid.findBoxWithNumber(grid.getInitial(), 1, false);
					Player player = new Player(symbol,box);
					box.setContent(box.getContent()+player.getSymbol());
					box.setPlayer(true);
					player.setPrevPlayer(pPlayer);
					pPlayer.setNextPlayer(player);
					createPlayers(player,parts,i,--players);
				}
			}
		}			
	}		
	
	
	public void play() {
		//System.out.print("Vamos a jugar!!!!!!!!!!!!!!!!!!");
	
		grid.deleteNumbersNext(grid.getInitial());
		System.out.print(grid.toString());
		movePlayer(firstPlayer);		
		System.out.print(grid.toString());		
	}
	
	public void movePlayer(Player player) {		
		if (player==null) {
						
		}else if (player!=null) {
			//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
			int dice = (int) (Math.random()* ((6+1)-1))+1;
			//int dice = (int) Math.floor(Math.random()* (1-(6+1))+6);
			System.out.println("Valor dado: "+ dice);
			
			//Obtiene la ubicación del player
			Box boxUbicationI = player.getBoxUbication();
			System.out.println("El jugador "+player.getSymbol()+" ha lanzado el dado y obtuvo el puntaje "+ dice);
			//System.out.println("Ubicacion inicial: "+player.getBoxUbication().getBoxNumber()+ " del simbolo: "+player.getSymbol());
			//Busca esa ubicacion inicial del player
			Box findIUbication = grid.findBoxWithNumber(grid.getInitial(),boxUbicationI.getBoxNumber(), false);
			//System.out.println("Numero ubicacion inicial: "+findIUbication.getBoxNumber());
			
			//A esa ubicacion inicial le borra el simbolo del player
			findIUbication.setContent(findIUbication.getContent().replace(player.getSymbol(),""));
			//Calcula la nueva posicion
			int newPosition = (boxUbicationI.getBoxNumber() + dice);
			//Busca la nueva posicion
			Box boxUbicationF = grid.findBoxWithNumber(grid.getInitial(),newPosition, false);
			//System.out.println("Ubicacion final: "+boxUbicationF.getBoxNumber()+ " del simbolo: "+player.getSymbol());
			//Al player le asigna su nueva posicion, posicion final
			player.setBoxUbication(boxUbicationF);
			//A la casilla final le agrega el simbolo del player
			boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());

			movePlayer(player.getNextPlayer());	
			
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
