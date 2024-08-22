package calculator;

import java.util.*;

public class cal {
	double sum(double a,double b) {
		return a+b;
	}
	double min(double a,double b) {
		return a-b;
	}
	double mul(double a,double b) {
		return a*b;
	}
	double div(double a,double b) {
		return a/b;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cal ob = new cal();
		while(true) {
			System.out.println("Enter 1 for Addition\nEnter 2 for Substratcion \nEnter 3 for Multiplication\nEnter 4 Division");
			int choice = sc.nextInt();
			double n1,n2,res = 0;
			int f=0;
			System.out.println("Enter 2 numbers");
			n1=sc.nextDouble();
			n2=sc.nextDouble();
			switch(choice) {
			case 1:
				res=ob.sum(n1, n2);
				break;
			case 2:
				res=ob.min(n1, n2);
				break;
			case 3:
				res=ob.mul(n1, n2);
				break;
			case 4:
				res=ob.div(n1, n2);
				break;
			default :
				f=1;
				System.out.println("Wrong Choice");
			}
			if(f==0)
				System.out.println(res+" = result");
			System.out.println("Do You want to continue \nEnter 1 for yes and 2 for no");
			int c = sc.nextInt();
			if(c!=1)
				break;
		}
		System.out.println("You are out of the arithmetic block now thank you for the visit");
	}
}
