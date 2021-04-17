package ui;

import java.util.Scanner;

import model.Game;

public class Main {
	static Scanner entry = new Scanner(System.in);
	
	static Game game;

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
		switch(optionNumber) {
			case 1:
				System.out.println("Cuantas Filas?");
				int filas=Integer.parseInt(entry.nextLine());
				
				System.out.println("Cuantas Columnas?");
				int columnas=Integer.parseInt(entry.nextLine());
				
				System.out.println("Cuantas Serpientes?");
				int serpientes=Integer.parseInt(entry.nextLine());
				
				System.out.println("Cuantas Escaleras?");
				int escaleras=Integer.parseInt(entry.nextLine());
				
				if((serpientes+escaleras)<= (((filas*columnas)-columnas)/2)){//formula para garantizar el maximo de serpientes y escaleras a ser creadas
					try {				
						game= new Game(filas, columnas, serpientes, escaleras);
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
				game.getGrid().showSnakes(game.getGrid().getFirstSnake());
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
