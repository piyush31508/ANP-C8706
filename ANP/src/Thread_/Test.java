package Thread_;

public class Test {
	public static void main(String[] args) {
		Common c = new Common();

		ThreadA t1 = new ThreadA("Piyush",c);
		ThreadA t2 = new ThreadA("Jatin",c);
		t1.start();
		t2.start();
	}
}
