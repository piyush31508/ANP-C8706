package Anudip.BasicCrud;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	private int id;
	
	private String Name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Student(int id, String name) {
		super();
		this.id = id;
		Name = name;
	}
	
	public Student() {
}
}