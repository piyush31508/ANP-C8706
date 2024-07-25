package July24;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		
		
		ArrayList<Emp> list = new ArrayList<>();
		
		list.add(new Emp(101,"Piyush",50000));
		list.add(new Emp(103,"Abhijeet",50000));
		list.add(new Emp(102,"Samay",50000));
		list.add(new Emp(104,"Karan",50000));
		
	
		
	System.out.println("Sorting by ID: ");
		
		Collections.sort(list, new IdComparator());
		
		for(Emp e: list) {
			System.out.println(e);
		}
		
	}
}
