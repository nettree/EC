package ec.master.assignment1.model;


public class Children {

	public Individual childA;
	public Individual getChildA() {
		return childA;
	}

	public void setChildA(Individual childA) {
		this.childA = childA;
	}

	public Individual getChildB() {
		return childB;
	}

	public void setChildB(Individual childB) {
		this.childB = childB;
	}

	public Individual childB;
	
	public Children(Individual fChild, Individual sChild){
		this.childA = fChild;
		this.childB = sChild;
	}
}
