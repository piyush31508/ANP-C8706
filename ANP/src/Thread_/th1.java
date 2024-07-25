package Thread_;

public class th1 extends Thread{
	public void run(){  
		for(int i=0;i<=10;i++)
		System.out.println("th1 is running "+i);  
		}  
	public void ra() {
		for(int i=0;i<=10;i++)
		System.out.println("Hello"+i);
	}
	public static void main(String args[]){  
		th1 t1=new th1(); 
		t1.start();  
		t1.ra();
		}  
}
