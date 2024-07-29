package Thread_;

public class get extends Thread{
	public void run(){  
		for(int i=0;i<=10;i++)
		System.out.println("thread is running "+i);  
		}  
	public static void main(String[] args) {
		get ob = new get();
		int p=ob.getPriority();
		System.out.println(p);
		ob.setPriority(8);
		p=ob.getPriority();
		System.out.println(p);
		p=currentThread().getPriority();
		System.out.println(p+" "+currentThread().getName());
	}
}
