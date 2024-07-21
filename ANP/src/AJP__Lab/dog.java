package AJP__Lab;

public class dog extends animal{
	void sound() {
		System.out.println("Dog is barking");
	}
	public static void main(String[] args) {
		animal ob = new dog();
		ob.sound();
	}
}
