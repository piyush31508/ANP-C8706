package Thread_;

public class ThreadB extends Thread{
	String name;
	Common c;
	public void run() {
		c.func(name);
	}
	public ThreadB(String name,Common c){
		this.c=c;
		this.name=name;
	}

}
