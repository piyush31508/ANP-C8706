package Lab8;
//class declaration
public class print implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//printing 1 to 10 wait a pause of 0.5 sec
		for(int i=1;i<=10;i++) {
			System.out.print(i+" ");
			try {
				//make thread sleep for 0.5sec
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//main function declaration
	public static void main(String[] args) {
		print obj = new print();//creating an instance or object of a class(print class)
		Thread t = new Thread(obj);//passing print class's object 
		t.start(); //thread starting point
		
	}

}
