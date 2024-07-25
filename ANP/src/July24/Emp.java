package July24;

public class Emp {
	
	int EmpId;
	String name;
	double salary;
	Emp(int EmpId, String name,double salary)
	{
		super();
		this.EmpId=EmpId;
		this.name=name;
		this.salary=salary;
	}
	@Override
	public String toString() {
		return "Emp [EmpId=" + EmpId + ", name=" + name + ", salary=" + salary + "]";
	}
	
	
}
