package Thread_;

public class ThreadA extends Thread{
	String name;
	Common c;
	public void run() {
		c.func(name);
	}
	public ThreadA(String name,Common c){
		this.c=c;
		this.name=name;
	}

}
