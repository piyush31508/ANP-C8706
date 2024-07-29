package Lab_5;

import Thread_.Main;

public class Thread_Sum implements Runnable{
	int sum=0;
	public void run() {
		for(int i=0;i<=100;i++) {
			sum+=i;
		}
	}
	public static void main(String[] args) {
		Main ob = new Main();
		Thread t1= new Thread(ob);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println(ob.sum);
	}

}
