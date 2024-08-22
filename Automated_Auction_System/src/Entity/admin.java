package Entity;

public class admin {
	private int a_id;

	public admin(int a_id) {
		super();
		this.a_id = a_id;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	@Override
	public String toString() {
		return "admin [a_id=" + a_id + "]";
	}
	
}
