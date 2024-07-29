package Thread_;

public class Main implements Runnable{

	public int sum=0;
	@Override 
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<=100;i++) {
			sum+=i;
		}
	}
	public static void main(String[] args) {
		Main ob = new Main();
		Thread t1= new Thread(ob);
		t1.start();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(ob.sum);
	}

}
