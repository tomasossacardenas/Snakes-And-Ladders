package ui;

import java.util.Scanner;

import model.Game;

public class Main {
	
	public static Scanner entry = new Scanner(System.in);
	public final static String SPLIT=" ";	
	public static Game game;

	public static void main(String[] args) {
		showAndChoose();
	}
	
//Method that shows the menu with the three options
	public static void showMenu() {
		System.out.println("Bienvenid@ a Snakes And Ladders, ingrese el numero de la opcion que desea realizar \n"+
							"1. ¡Jugar!\n"+
							"2. Ver Tablero de Posiciones\n"+
							"3. Salir del programa\n");
	}
	
//Method to make process depending the option the user chose
	public static void chooseMenuOption(int optionNumber) {
		int filas=0;
		int columnas=0;
		int serpientes=0;
		int escaleras=0;
		String []parts = null;
		switch(optionNumber) {
			case 1:
				System.out.println("Ingrese la información del juego");
				String info = entry.nextLine() ;
				
				if (info!=null) {
					parts = info.split(SPLIT);
					//n-> filas, m->columnas,s->snakes,e->ladders,p->players
					filas=Integer.parseInt(parts[0]);
					columnas=Integer.parseInt(parts[1]);
					serpientes=Integer.parseInt(parts[2]);
					escaleras=Integer.parseInt(parts[3]);					
				}
				/*
				System.out.println("Cuantas Filas?");
				
				
				System.out.println("Cuantas Columnas?");
				=Integer.parseInt(entry.nextLine());
				
				System.out.println("Cuantas Serpientes?");
				int serpientes=Integer.parseInt(entry.nextLine());
				
				System.out.println("Cuantas Escaleras?");
				int escaleras=Integer.parseInt(entry.nextLine());
				*/
				
				if((serpientes+escaleras)<= (((filas*columnas)-columnas)/2)){//formula para garantizar el maximo de serpientes y escaleras a ser creadas
					try {				
						game= new Game(filas, columnas, serpientes, escaleras,parts);
						System.out.println(game.getGrid().toString());
						int casillas= filas*columnas;
						game.getGrid().setCounterBoxes(casillas);
					}
					catch(StackOverflowError e) {
						System.out.println("Intente otra vez, el programa ha entrado a un bucle infinito");
					}
				}
				else {
					System.out.println("No es posible añadir es cantidad de serpientes y escaleras a una cuadricula de juego de "+filas+"x"+columnas);
				}			
				
				//game.getGrid().showSnakes(game.getGrid().getFirstSnake());
				
				break;
			case 2:
				System.out.println(game.getGrid().toString());
				break;
			case 3:
				System.out.println("Gracias por jugar!");
				System.exit(0);
				//game.getGrid().showSnakes(game.getGrid().getFirstSnake());
				break;
			default:
				System.out.println("Opcion invalida, el numero de la opcion debe ser 1, 2 o 3");
		}
	}
	
	public static void showAndChoose() {
		showMenu();
		try {
			chooseMenuOption(Integer.parseInt(entry.nextLine()));
			showAndChoose();
		}catch(NumberFormatException e) {
			System.out.println("Opcion invalida");
			showAndChoose();
		}
	}

}
