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
				
				game= new Game(filas, columnas);
				
				int casillas= filas*columnas;
				game.getGrid().setCounterBoxes(casillas);
				
				break;
			case 2:
				System.out.println(game.getGrid().toString());
				System.out.println("Esperado: "+game.getGrid().box1Ubication(game.getGrid().getInitial()));
				game.getGrid().assignNumbers(game.getGrid().box1Ubication(game.getGrid().getInitial()), 1, game.getGrid().getRows());
				System.out.println(game.getGrid().toString());
				break;
			case 3:
				//SALIR DEL PROGRAMA
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
