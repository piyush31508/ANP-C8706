package Lab_6;

public class Thread1 extends Thread{
	public void run(){  	
	for(int i=1;i<=20;i++) {	
		String tname=Thread.currentThread().getName();
		System.out.println(tname+" "+i);
		}	
	}  
}