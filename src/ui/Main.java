package ui;

import java.util.Scanner;

public class Main {
	static Scanner entry = new Scanner(System.in);

	public static void main(String[] args) {
		showMenu();
		try {
			chooseMenuOption(Integer.parseInt(entry.nextLine()));
		}catch(NumberFormatException e) {System.out.println("Opcion invalida");}

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
				//REALIZAR TODO LO QUE SERÍA DEL JUEGO
			case 2:
				//MOSTRAR EL TABLERO DE POSICIONES
			case 3:
				//SALIR DEL PROGRAMA	
		}
	}

}
