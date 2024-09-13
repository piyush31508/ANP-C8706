package calculator;

import java.util.*;

public class Cal {
	//Sum function which will return sum of 2 numbers
	double sum(double a,double b) {
		return a+b;
	}
	//min function which will return subtraction of 2 numbers
	double min(double a,double b) {
		return a-b;
	}
	//mul function which will return multiplicTION of 2 numbers
	double mul(double a,double b) {
		return a*b;
	}
	//div function which will return divison of 2 numbers
	double div(double a,double b) {
		return a/b;
	}
	//main logic of our program which ask the users about there choice and performing arithmetic operation based on it
	public void calculator() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Enter 1 for Addition\nEnter 2 for Substratcion \nEnter 3 for Multiplication\nEnter 4 Division \nEnter 5 for exit");
			System.out.println("What is your choice?");
			int choice = sc.nextInt();//Taking input from the users as user choice
			double n1=0,n2=0,res = 0;
			if(choice>0 && choice<5) {
			System.out.println("Enter 2 numbers");
			n1=sc.nextDouble();
			n2=sc.nextDouble();
			}
			switch(choice) {
			case 1:
				res=sum(n1, n2);
				break;
			case 2:
				res=min(n1, n2);
				break;
			case 3:
				res=mul(n1, n2);
				break;
			case 4:
				res=div(n1, n2);
				break;
			case 5:
				System.out.println("Exiting the calculator");
				System.exit(0);
			default :
				System.out.println("Wrong Choice");
			}
			System.out.println("Result "+res);
			sc.close();
		}
	}
	
	public static void main(String[] args) {
		//object creation for Cal class
		Cal ob = new Cal();
		//calling calculator function
		ob.calculator();
		
	}
}
