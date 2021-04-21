package model;

public class Game {	
	
	//Relations
	private Grid grid;
	private Player firstPlayer;
	private Player auxPlayer;
	private Player lastPlayer;
	private Player winner;
	private Box biggestBox;
	
	//Constructor 
	public Game(int rows, int columns, int snakesNumber, int laddersNumber,String parts) {			
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);		
		createPlayers(firstPlayer,parts,0,parts.length());
	}
	
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setLastPlayer(Player lastPlayer) {
		this.lastPlayer = lastPlayer;
	}

	public Player getLastPlayer() {
		return lastPlayer;
	}
	
	public Player getAuxPlayer() {
		return auxPlayer;
	}
	
	public Player getWinner() {
		return winner;
	}

	public Grid getGrid() {
		return grid;
	}
	
	//Creo una lista circular de los jugadores
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
					auxPlayer = firstPlayer;
					//lastPlayer = new Player (symbol,box);
					
					firstPlayer.setNextPlayer(lastPlayer);
					//firstPlayer.setPrevPlayer(lastPlayer);
					//lastPlayer.setPrevPlayer(firstPlayer);
					//lastPlayer.setNextPlayer(firstPlayer);
					
					
					box.setContent(box.getContent()+firstPlayer.getSymbol());
					box.setPlayer(true);
					players--;
					createPlayers(firstPlayer,parts,i,players);
					
				}else {				
					i+=1;					
					Box box = grid.findBoxWithNumber(grid.getInitial(), 1, false);
					Player player = new Player(symbol,box);
					//System.out.println("symbol pPlayer: "+pPlayer.getSymbol());
					//System.out.println("symbol player: "+player.getSymbol());
					pPlayer.setNextPlayer(player);
					player.setNextPlayer(pPlayer);

					//player.setNextPlayer(firstPlayer);
					lastPlayer = player;
					lastPlayer.setNextPlayer(firstPlayer);
					//System.out.println("symbol lastPlayer: "+lastPlayer.getSymbol()+" y su next: "+lastPlayer.getNextPlayer().getSymbol());
					
					box.setContent(box.getContent()+player.getSymbol());
					box.setPlayer(true);
					//player.setPrevPlayer(pPlayer);
					//pPlayer.setNextPlayer(player);
					createPlayers(player,parts,i,--players);
				}
			}
		}			
	}		
	
	public void startGame() {		
		
		//System.out.print("Vamos a jugar!!!!!!!!!!!!!!!!!!");
	
		grid.deleteNumbersNext(grid.getInitial());
		System.out.print(grid.toString());
		
		Box big = grid.findBoxCoordenates(grid.getInitial(),1,grid.getColumns(), false);
		
		if (grid.getInitial().getBoxNumber() > big.getBoxNumber()) {
			biggestBox = grid.getInitial();
		}else {
			biggestBox = big;
		}
		System.out.println(">>>>>>Casilla con el valor mas grande: "+biggestBox.getBoxNumber());
		
		//movePlayer(firstPlayer);		
		//System.out.print(grid.toString());		
	}
	
	public boolean movePlayer(Player player,boolean end) {		
		boolean stop=end;
		//System.out.println("Entro al metodo movePlayer");
		
		if (stop==true) {
			
		}else {
			if (player==null) {
				//System.out.println("Player es null");
			}else if (player!=null) {
				//(int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
				int dice = (int) (Math.random()* ((6+1)-1))+1;
				//int dice = (int) Math.floor(Math.random()* (1-(6+1))+6);
				//System.out.println("Valor dado: "+ dice);				

				//Obtiene la ubicación del player
				Box boxUbicationI = player.getBoxUbication();
				System.out.println("El jugador "+player.getSymbol()+" ha lanzado el dado y obtuvo el puntaje "+ dice);
				//System.out.println("Ubicacion inicial: "+player.getBoxUbication().getBoxNumber()+ " del simbolo: "+player.getSymbol());
				//Busca esa ubicacion inicial del player
				Box findIUbication = grid.findBoxWithNumber(grid.getInitial(),boxUbicationI.getBoxNumber(), false);
				//System.out.println("++++++++++Valor del box encontrado: "+findIUbication.getBoxNumber());
				//System.out.println("Numero ubicacion inicial: "+findIUbication.getBoxNumber());

				//A esa ubicacion inicial le borra el simbolo del player
				findIUbication.setContent(findIUbication.getContent().replace(player.getSymbol(),""));
				//System.out.println(" ***Cambio el contenido la ubicacion inicial");
				//Calcula la nueva posicion
				int newPosition = (boxUbicationI.getBoxNumber() + dice);
				
				if (newPosition<=biggestBox.getBoxNumber()) {
					//System.out.println(" ***Calculo la nueva posicion");
					//Busca la nueva posicion
					Box boxUbicationF = grid.findBoxWithNumber(grid.getInitial(),newPosition, false);
					//System.out.println("Ubicacion final: "+boxUbicationF.getBoxNumber()+ " del simbolo: "+player.getSymbol());
					//System.out.println("++++++++++Valor del box encontrado: "+boxUbicationF.getBoxNumber());
					//Al player le asigna su nueva posicion, posicion final
					player.setBoxUbication(boxUbicationF);
					player.setMovements(player.getMovements()+1);
					//System.out.println("Movimientos de "+player.getSymbol()+": "+player.getMovements());
					//A la casilla final le agrega el simbolo del player
					boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());
					
					
					if (boxUbicationF==biggestBox) {
						boxUbicationF.setPlayer(true);
						stop = true;
						winner = player;						
						//return stop;
					}

					auxPlayer = player.getNextPlayer();
					//System.out.println("valor de player: "+player.getSymbol()+ " y su next es: "+player.getNextPlayer().getSymbol());
					//System.out.println("nuevo valor de auxPlayer: "+auxPlayer.getSymbol());
					//movePlayer(player,stop);	
				}else {
					//System.out.println("Debe repetir el ciclo");
					return movePlayer(player,stop);
				}
			}
		}
		
		//System.out.println("valor de stop: "+stop);
		 
	return stop;		
	}


}
