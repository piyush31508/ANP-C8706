package Thread_;

public class th2 extends th1{
	public void run(){
		for(int i=0;i<=10;i++)
			System.out.println("th2 is running "+i);
	}
	public static void main(String[] args) {
		th1 t1 = new th1();

		th2 t2 = new th2();
		
		t1.start();
		t2.start();
	}
}
