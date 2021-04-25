package model;

public class WinnersController {
	//Relations
	private Player firstWinner;//la root del arbol binario
	
	public WinnersController() {
		
	}
	
	public void addWinner(Player current, Player newWinner) {
		if(current==null) {
			firstWinner=newWinner;
			System.out.println("SE CREO EL PRIMER WINNER");
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
				else {//si hay left entonces recursividad pero partiendo del right
					addWinner(current.getRight(), newWinner);
				}
			}
		}
		
	}
	
	public void showWinners(Player root) {//METODO INORDER
		if(root!=null) {
			System.out.println("LA RAIZ ES "+firstWinner.getNickName()+","+firstWinner.getMovements());
			showWinners(root.getLeft());
			System.out.println(root.getNickName()+","+root.getMovements());
			showWinners(root.getRight());
		}
	}
	
	public Player getFirstWinner() {
		return firstWinner;
	}

	public void setFirstWinner(Player firstWinner) {
		this.firstWinner = firstWinner;
	}

}
