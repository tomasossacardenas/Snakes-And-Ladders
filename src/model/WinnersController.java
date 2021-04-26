package model;

public class WinnersController {
	//Relations
	private Player firstWinner;//la root del arbol binario
	
	public WinnersController() {
		
	}
	
	/**
	 * <b>Name: </b>addWinner<br>
	 * This method add a winner to the binary search tree.<br>
	 * <b>Pre: </b> A player must win the game to be add.<br>
	 * <b>Pos: </b>The player was added successfully.
	 * @param current Player. Actual player of the binary search tree that is being compared.
	 * @param newWinner Player. Player winner of the game that will be added to the binary search tree.
	 */
	public void addWinner(Player current, Player newWinner) {
		if(current==null) {
			firstWinner=newWinner;
			//System.out.println("SE CREO EL PRIMER WINNER");
		}
		else {
			if(newWinner.getMovements()<=current.getMovements()) {// si es menor que el current
				if(current.getLeft()==null) {//si aún no hay left entonces lo asigna
					current.setLeft(newWinner);
				}
				else {//si hay left entonces recursividad pero partiendo del left
					addWinner(current.getLeft(), newWinner);
				}
			}
			else {//si es mayor que el current
				if(current.getRight()==null) {//si aún no hay right entonces lo asigna
					current.setRight(newWinner);
				}
				else {//si hay right entonces recursividad pero partiendo del right
					addWinner(current.getRight(), newWinner);
				}
			}
		}
		
	}
	
	/**
	 * <b>Name: </b>showWinners<br>
	 * This method go over every element of the tree in a inorder way (METODO INORDER) to show all the winners.<br>
	 * <b>Pos: </b>The winners were shown successfully.
	 * @param root Player. Every element of the tree.
	 */
	public void showWinners(Player root) {//METODO INORDER
		if(root!=null) {
			showWinners(root.getRight());
			System.out.println(root.getNickName()+" con "+root.getMovements()+" movimientos"+" y su simbolo es: "+root.getSymbol());
			showWinners(root.getLeft());
		}
	}
	
	public Player getFirstWinner() {
		return firstWinner;
	}

	public void setFirstWinner(Player firstWinner) {
		this.firstWinner = firstWinner;
	}

}
