package Thread_;

public class demon extends Thread{

	public void run() {
		 if(Thread.currentThread().isDaemon()){
			   System.out.println("Demon thread");  
			  }  
		System.out.println("Inside run");
	}
	public static void main(String[] args) {
		demon t = new demon();
		t.setDaemon(true);
		t.start();
	}

}
