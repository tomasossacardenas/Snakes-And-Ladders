package model;

public class Game {	
	
	//Relations
	private Grid grid;
	private Player firstPlayer;
	private Player auxPlayer;
	private Player lastPlayer;
	private Player winner;
	private Box biggestBox;
	
	//Constructor #1
	public Game(int rows, int columns, int snakesNumber, int laddersNumber,String parts) {			
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);		
		createPlayers(firstPlayer,parts,0,parts.length());
	}
	//Constructor #2
	public Game(int rows, int columns, int snakesNumber, int laddersNumber,int numberOfPlayers) {			
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);		
		createPlayers(firstPlayer,numberOfPlayers);
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
	
	private void createPlayers(Player actualPlayer, int numberOfPlayers) {
		//* ! O X % $ # + &
		String symbols="*!OX%$#+&"; //symbols.charAt(posicion)
		
		if(numberOfPlayers==0) {//Caso base cuando el numero de jugadores a crear ya es cero
			
		}
		else {// aun hay jugadores por crear
			if(firstPlayer==null) {//Si no hay primer jugador
				Box box = grid.findBoxWithNumber(grid.getInitial(), 1, false);
				firstPlayer = new Player(String.valueOf(symbols.charAt(numberOfPlayers)), box);
				
				auxPlayer=firstPlayer;
				firstPlayer.setNextPlayer(lastPlayer);//va a ser null
				
				box.setContent(box.getContent()+firstPlayer.getSymbol());
				box.setPlayer(true);
				//numberOfPlayers--;
				createPlayers(firstPlayer,--numberOfPlayers);
			}
			else {//Si ya hay primer jugador
				Box box = grid.findBoxWithNumber(grid.getInitial(), 1, false);
				Player player = new Player(String.valueOf(symbols.charAt(numberOfPlayers)),box);
				//System.out.println("symbol pPlayer: "+pPlayer.getSymbol());
				//System.out.println("symbol player: "+player.getSymbol());
				actualPlayer.setNextPlayer(player);
				player.setNextPlayer(actualPlayer);

				//player.setNextPlayer(firstPlayer);
				lastPlayer = player;
				lastPlayer.setNextPlayer(firstPlayer);
				//System.out.println("symbol lastPlayer: "+lastPlayer.getSymbol()+" y su next: "+lastPlayer.getNextPlayer().getSymbol());
				
				box.setContent(box.getContent()+player.getSymbol());
				box.setPlayer(true);
				//player.setPrevPlayer(pPlayer);
				//pPlayer.setNextPlayer(player);
				createPlayers(player,--numberOfPlayers);
			}
		}
		
	}
	
	//Creo una lista circular de los jugadores
	public void createPlayers(Player pPlayer,String parts,int i,int p) {
		//* ! O X % $ # + &
		int players = p; //
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
										
					firstPlayer.setNextPlayer(lastPlayer);
										
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
					//player.setNextPlayer(pPlayer);

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
	
		//System.out.println(">>>>>>Casilla con el valor mas grande: "+biggestBox.getBoxNumber());
	
	}
	
	public boolean movePlayer(Player player,boolean end) {		
		boolean stop=end;
			
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

					
					
					//Verificación de si cayó en una snake o ladder
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

	
	
	
	public void simul(Player player,boolean end) {		
		boolean stop=end;
			
		if (stop==true) {
			System.out.println("El modo simulación ha terminado");
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

					
					
					//Verificación de si cayó en una snake o ladder
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
					
					
					
					
					System.out.println(grid.toString());					
					auxPlayer = player.getNextPlayer();		
					
					try {
						Thread.sleep(2*1000);
						simul(auxPlayer,stop);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
				
				}else {
					System.out.println("El valor obtenido al lanzar el dado no le sirve");
					player.setBoxUbication(player.getBoxUbication());
					player.getBoxUbication().setContent(player.getBoxUbication().getContent()+player.getSymbol());
					auxPlayer = player.getNextPlayer();
					
					System.out.println(grid.toString());
					
					try {
						Thread.sleep(2*1000);
						simul(auxPlayer,stop);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
					//return movePlayer(player,stop);
				}
			}
		}		
	}


}
