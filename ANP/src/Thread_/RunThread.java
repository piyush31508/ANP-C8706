package Thread_;

public class RunThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<=20;i++) {
			String tname=Thread.currentThread().getName();
			System.out.println(tname+" "+i);
		}
	}
	public static void main(String[] args) {
		RunThread ob = new RunThread();
		
		Thread t1 = new Thread(ob);
		Thread t2 = new Thread(ob);
		
		
		t1.setName("Piyush");
		t2.setName("Jatin");

		t1.start();
		t2.start();
	}

}
