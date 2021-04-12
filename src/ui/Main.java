package ui;

import java.util.Scanner;

import model.Game;

public class Main {
	static Scanner entry = new Scanner(System.in);
	
	static Game game;

	public static void main(String[] args) {
		game=new Game();
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
				
				int casillas= filas*columnas;
				game.getGrid().setCounterBoxes(casillas);
				
				for(int i=1;i<=casillas;i++) {
						game.getGrid().addBox();
				}
				
				break;
			case 2:
				System.out.println("GRID ES:\n"+game.showGrid());
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
