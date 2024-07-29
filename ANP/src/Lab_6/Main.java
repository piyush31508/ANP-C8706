package Lab_6;

public class Main {
	public void run() {
		for(int i=61;i<=80;i++) {
			String tname=Thread.currentThread().getName();
			System.out.println(tname+" "+i);
		}
	}
	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		
		t1.setName("Thread Number 1");
		t2.setName("Thread Number 2");
	
		t1.start();
		t2.start();
		
		Thread.currentThread().setName("Main Thread");
      
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
            for (int i = 61; i <= 80; i++) {
                String tname = Thread.currentThread().getName();
                System.out.println(tname + " " + i);
                
        }
	}

}
