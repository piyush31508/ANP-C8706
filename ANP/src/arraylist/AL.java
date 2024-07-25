package arraylist;

import java.util.ArrayList;
import java.util.Collections;

public class AL {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(5);
		list.add(4);
		list.add(2);
		list.add(14);
		
		System.out.println(list);
		
		int get = list.get(2);//2 nd index
		System.out.println(get);
		
		list.add(3,10);//3rd index 10 (first we need to pass index then value
		System.out.println(list);
		
		list.remove(4);//4th index element will be deleted
		System.out.println(list);
		
		int l= list.size();
		for(int i=0;i<l;i++)
		{
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
		
		Collections.sort(list);
		
		System.out.println(list);
		
	}
	
}
