package AJP__Lab;

public class math {
    double area(double r)
	{
		double a = r*r*3.14;
		return a;
	}

	double area(double h, double b)
	{
		double a;
		a=1/2*b*h;
		return a;
	}
	public static void main(String[] args) {
		math ob = new math();
		double c =ob.area(8);
		System.out.println("area of triangle = "+c);
		
		double t= ob.area(5.8,55.1);
		System.out.println("area of triangle = "+t);
	}
}
