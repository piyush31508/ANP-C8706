package Lab_4;

import java.util.Scanner;
import java.util.TreeSet;


public class TreeSetExample {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a list of strings: ");
        String s = sc.nextLine()+" ";
        int i;
        char ch;
        String w="";
        TreeSet<String> list = new TreeSet<>();
        for(i=0;i<s.length();i++) {
        	ch=s.charAt(i);
    	if(ch!=' ') {
    		w=w+ch;
    	}
    	else
    	{
    		list.add(w);
    		w="";
    	}
        }
        System.out.println(list);
    }
}