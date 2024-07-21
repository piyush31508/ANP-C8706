package AJP__Lab;

class monkey extends overriding_ab{

	@Override
	void fav_food() {
		// TODO Auto-generated method stub
		System.out.println("banana");
	}
	public static void main(String[] args) {
		overriding_ab obj = new monkey();
		obj.fav_food();
	}
}
