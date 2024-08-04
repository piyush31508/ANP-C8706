package Lab7;

//class declaration
public class print extends Thread {
	//overriding run function
	public void run() {
		for(int i=0;i<=4;i++) {
			System.out.print(i+" ");
		}
	}
	//main function declaration
	public static void main(String[] args) {
		Thread t = new print();//object creation 
		t.start();//thread starting
	}
}
