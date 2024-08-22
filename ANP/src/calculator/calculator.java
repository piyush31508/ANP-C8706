package calculator;

import java.util.*;

public class calculator {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("Enter 1 for Add\nEnter 2 for subs and so on for doing arithmetic calculations");
		int choice = sc.nextInt();
		int n1,n2,res;
		System.out.println("Enter 2 numbers");
		n1=sc.nextInt();
		n2=sc.nextInt();

		switch(choice) {
		case 1:
			res=n1+n2;
			System.out.println(res);
			break;
		case 2:
			res=n1-n2;
			System.out.println(res);

			break;
		case 3:
			res=n1*n2;
			System.out.println(res);

			break;
		case 4:
			res=n1/n2;
			System.out.println(res);

			break;
		default:
			System.out.println("Wrong choice");
		}
		System.out.println("Do You want to continue \nEnter 1 for yes and 2 for no");
		int c = sc.nextInt();
		if(c!=1)
			break;
		}	
		System.out.println("You are out of the arithmetic block now thank you for the visit");
	}
}
