package model;

public class Game {	
	
	//Relations
	private Grid grid;
	private Player firstPlayer;
	private Player auxPlayer;
	private Player lastPlayer;
	private Player winner;
	private Box biggestBox;
	private Player firstWinner;
	
	//Constructor #1
	/**
	 * <b>Name: </b>Game<br>
	 * This method is the constructor #1 of the class. It is use when the user write the symbols of the players.<br>
	 * <b>Pos: </b> The game was created successfully.
	 * @param rows int. Amount of rows. rows!=0.
	 * @param columns int. Amount of columns. columns!=0.
	 * @param snakesNumber int. Amount of snakes. snakesNumber!=0.
	 * @param laddersNumber int. Amount of ladder. laddersNumber!=0.
	 * @param parts String. Symbols string. parts!="" Y parts!=null.
	 */
	public Game(int rows, int columns, int snakesNumber, int laddersNumber,String parts) {			
		grid=new Grid(rows, columns, snakesNumber, laddersNumber);		
		createPlayers(firstPlayer,parts,0,parts.length());
	}
	//Constructor #2
	/**
	 * <b>Name: </b>Game<br>
	 * This method is the constructor #2 of the class. It is use when the user write the amount of players.<br>
	 * <b>Pos: </b> The game was created successfully.
	 * @param rows int. Amount of rows. rows!=0.
	 * @param columns int. Amount of columns. columns!=0.
	 * @param snakesNumber int. Amount of snakes. snakesNumber!=0.
	 * @param laddersNumber int. Amount of ladder. laddersNumber!=0.
	 * @param numberOfPlayers int. Amount of players. numberOfPlayers higher or equal to 1 and numberOfPlayers lower or equal to 8.
	 */
	
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
	public Player getFirstWinner() {
		return firstWinner;
	}
	public void setFirstWinner(Player firstWinner) {
		this.firstWinner = firstWinner;
	}
	
	/**
	 * <b>Name: </b>createPlayers.<br>
	 * This method create the players with a random symbol because the user wrote the amount of players instead of the symbols of the players.<br>
	 * <b>Pre: </b>The grid must already be created and must have the numbers.<br>
	 * <b>Pre: </b>The user  wrote a number to create the players.
	 * <b>Pos: </b>The players were created successfully.
	 * @param actualPlayer Player. Previous player.<br>
	 * @param numberOfPlayers int. Amount of players.<br>
	 */
	
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
				//player.setNextPlayer(actualPlayer);

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
	
	/**
	 * <b>Name: </b>createPlayers.<br>
	 * This method create the amount of players according to the amount of symbols written by the user,  and each player has one of the symbols written by the user.<br>
	 *	<b>Pre: </b>The grid must already be created and must have the numbers.<br>
	 *<b>Pre: </b>The user  wrote a symbols string to create the players.
	 * <b>Pos: </b>The players were created successfully.
	 * @param pPlayer Player. Previous player.<br>
	 * @param parts String. Word with the symbols.<br>
	 * @param i int. Index of each symbol. Every time that a player is created, its value increases but it has to be less than the amount of symbols.<br>
	 * @param p int. Amount of players. Every time that a player is created, its value decreases.<br>
	 */
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
	
	
	/**
	 * <b>Name: </b>startGame.<br>
	 * This method start the game because delete the numbers of the grid and show the new grid.
	 */
	public void startGame() {	
		grid.deleteNumbersNext(grid.getInitial());
		System.out.print(grid.toString());
		
		biggestBox = grid.findBiggestBox();	
	}
	
	
	/**
	 * <b>Name: </b>movePlayer.<br>
	 * This method move player by player according to the value of the dice. To move the next player, is necessary a line break<br>
	 * <b>Pre: </b> The players must already be created.<br>
	 * <b>Pos: </b> The player was moved successfully.
	 * @param player Player. Next Player.
	 * @param end boolean. Indicate if there is a player in the last box.
	 * @return stop boolean. It will be true when a player gets to the last box and the game will end.
	 */
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

	
	
	/**
	 * <b>Name: </b>simul.<br>
	 * This method is the simulation mode where the game play by itself. This method doesn't need a line break to continue<br>
	 * The simulation model will end when a player gets to the last box.<br>
	 * <b>Pre: </b> The players must already be created.<br>
	 * <b>Pos: </b> The players played successfully.
	 * @param player Player. Next Player.
	 * @param end boolean. Indicate if there is a player in the last box.
	 */
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
