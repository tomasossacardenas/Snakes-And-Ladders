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
		grid.deleteNumbersNext(grid.getInitial());
		System.out.print(grid.toString());
		
		biggestBox = grid.findBiggestBox();
	
		System.out.println(">>>>>>Casilla con el valor mas grande: "+biggestBox.getBoxNumber());
	
	}
	
	public boolean movePlayer(Player player,boolean end) {		
		boolean stop=end;
			
		if (stop==true) {
			
		}else {
			if (player==null) {
				//System.out.println("Player es null");
			}else if (player!=null) {
				//(int) (Math.random() * (<n�mero_m�ximo + 1> - <n�mero_m�nimo>)) + <numero_m�nimo>;
				int dice = (int) (Math.random()* ((6+1)-1))+1;
				//int dice = (int) Math.floor(Math.random()* (1-(6+1))+6);
				//System.out.println("Valor dado: "+ dice);				

				//Obtiene la ubicaci�n del player
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

					
					
					//Verificaci�n de si cay� en una snake o ladder
					if (boxUbicationF.getSnake()!=null) {
						Box startSnake = null;
						//La serpiente inicia en la casilla mayor
						if (boxUbicationF.getSnake().getStart().getBoxNumber()>boxUbicationF.getSnake().getEnd().getBoxNumber()) {
							startSnake = boxUbicationF.getSnake().getStart();
							
							if (boxUbicationF == startSnake) {
								Box endSnake = startSnake.getSnake().getEnd();
								System.out.println("El jugador "+player.getSymbol()+" se movera a la casilla "+endSnake.getBoxNumber()+" que es el final de una serpiente");
								player.setBoxUbication(endSnake);
								player.setMovements(player.getMovements()+1);
								endSnake.setContent(endSnake.getContent()+player.getSymbol());								
							}else {
								//Al player le asigna su nueva posicion, posicion final
								player.setBoxUbication(boxUbicationF);
								player.setMovements(player.getMovements()+1);
								//System.out.println("Movimientos de "+player.getSymbol()+": "+player.getMovements());
								//A la casilla final le agrega el simbolo del player
								boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());
							}
							
						}else {
							startSnake = boxUbicationF.getSnake().getEnd();
							if (boxUbicationF == startSnake) {
								Box endSnake = startSnake.getSnake().getStart();
								System.out.println("El jugador "+player.getSymbol()+" se movera a la casilla "+endSnake.getBoxNumber()+" que es el final de una serpiente");
								player.setBoxUbication(endSnake);
								player.setMovements(player.getMovements()+1);
								endSnake.setContent(endSnake.getContent()+player.getSymbol());								
							}else {
								//Al player le asigna su nueva posicion, posicion final
								player.setBoxUbication(boxUbicationF);
								player.setMovements(player.getMovements()+1);
								//System.out.println("Movimientos de "+player.getSymbol()+": "+player.getMovements());
								//A la casilla final le agrega el simbolo del player
								boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());
							}
						}			
						
					}else if (boxUbicationF.getLadder()!=null) {
						Box startLadder = null;
						
						if (boxUbicationF.getLadder().getStart().getBoxNumber()<boxUbicationF.getLadder().getEnd().getBoxNumber()) {
							
							startLadder = boxUbicationF.getLadder().getStart();
							
							if (boxUbicationF == startLadder) {
								Box endLadder = startLadder.getLadder().getEnd();
								System.out.println("El jugador "+player.getSymbol()+" se movera a la casilla "+endLadder.getBoxNumber()+" que es el final de una escalera");
								player.setBoxUbication(endLadder);
								player.setMovements(player.getMovements()+1);
								endLadder.setContent(endLadder.getContent()+player.getSymbol());
								
							}else {
								//Al player le asigna su nueva posicion, posicion final
								player.setBoxUbication(boxUbicationF);
								player.setMovements(player.getMovements()+1);
								//System.out.println("Movimientos de "+player.getSymbol()+": "+player.getMovements());
								//A la casilla final le agrega el simbolo del player
								boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());
							}
							
						}else {
							
							startLadder = boxUbicationF.getLadder().getStart();
							
							if (boxUbicationF == startLadder) {
								Box endLadder = startLadder.getLadder().getStart();
								System.out.println("El jugador "+player.getSymbol()+" se movera a la casilla "+endLadder.getBoxNumber()+" que es el final de una escalera");
								player.setBoxUbication(endLadder);
								player.setMovements(player.getMovements()+1);
								endLadder.setContent(endLadder.getContent()+player.getSymbol());
							}else {
								//Al player le asigna su nueva posicion, posicion final
								player.setBoxUbication(boxUbicationF);
								player.setMovements(player.getMovements()+1);
								//System.out.println("Movimientos de "+player.getSymbol()+": "+player.getMovements());
								//A la casilla final le agrega el simbolo del player
								boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());
							}
						}
					}else {
						
						//Al player le asigna su nueva posicion, posicion final
						player.setBoxUbication(boxUbicationF);
						player.setMovements(player.getMovements()+1);
						//System.out.println("Movimientos de "+player.getSymbol()+": "+player.getMovements());
						//A la casilla final le agrega el simbolo del player
						boxUbicationF.setContent(boxUbicationF.getContent()+player.getSymbol());
					}					
					
					if (boxUbicationF==biggestBox) {
						boxUbicationF.setPlayer(true);
						stop = true;
						winner = player;						
						//return stop;
					}

					auxPlayer = player.getNextPlayer();
				
				}else {
					System.out.println("El valor obtenido al lanzar el dado no le sirve");
					player.setBoxUbication(player.getBoxUbication());
					player.getBoxUbication().setContent(player.getBoxUbication().getContent()+player.getSymbol());
					auxPlayer = player.getNextPlayer();
					//return movePlayer(player,stop);
				}
			}
		}	 
	return stop;		
	}


}
