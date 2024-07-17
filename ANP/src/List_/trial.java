package List_;

import java.util.ArrayList;

import java.util.Iterator;

public class trial {
public static void main(String[] args) {
	
	ArrayList<String> name = new ArrayList<>();
	name.add("Piyush");
	name.add("Karan");
	name.add("Abhijeet");
	name.add("Samay");
	name.add("Anuj");
	name.add("Shivi");
	
	Iterator it = name.iterator();
	while(it.hasNext()) {
		System.out.println(it.next());
	}
	
	ArrayList<String> name2 = new ArrayList<>();
	name2.add("Anwesha");
	name2.add("Atharv");
	name2.add("jatin");
	
	name.addAll(name2);
	
	while(it.hasNext()) {
		System.out.println(it.next());
	}
}
}
