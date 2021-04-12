package model;

public class Grid {
	private Box initial, end;
	int counterBoxes;
	
	public Grid() {
		this.initial=null;
		this.end=null;
	}
	
	public void addBox() {// AÑADIR AL FINAL
		if(initial==null && end==null) {
			initial=end= new Box(String.valueOf(counterBoxes));
			counterBoxes--;
		}
		else {
			end=new Box(String.valueOf(counterBoxes), end, null);//CONTENIDO, EL BOX QUE ESTABA DE ULTIMO ANTES, NULL PORQUE ESTE NUEVO BOX SERÁ EL ULTIMO
			end.getPrevious().setNext(end); //AL QUE ANTES ESTABA DE ULTIMO LE ASIGNO NEXT DEL NUEVO QUE ESTÁ DE ULTIMO
			counterBoxes--;
		}
	}

	public Box getInitial() {
		return initial;
	}

	public void setInitial(Box initial) {
		this.initial = initial;
	}

	public Box getEnd() {
		return end;
	}

	public void setEnd(Box end) {
		this.end = end;
	}

	public int getCounterBoxes() {
		return counterBoxes;
	}

	public void setCounterBoxes(int counterBoxes) {
		this.counterBoxes = counterBoxes;
	}
	
}
