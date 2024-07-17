package ANP;

public class Device {
	String brand,model;
	boolean power;
	Device(String b, String m){
		model=m;
		brand=b;
		power=false;
	}
	public void dinfo() {
		System.out.println("The device brand is "+brand+"\nand model is "+model);
	}
	public void on() {
		if(!power) {
			power=true;
			System.out.println("Device is on");
		}
		else {
			System.out.println("Device is already on");

		}
	}
	public void off() {
		if(power) {
			power=false;
			System.out.println("Device is off");
		}
		else {
			System.out.println("Device is already off");

		}

}
}
