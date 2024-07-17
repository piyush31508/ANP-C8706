package ANP;

public class Smartphone extends Device{
	
	     String OS;
	     String Cammera;
		
	    Smartphone(String brand, String model, String OS, String Cammera) {
			super(brand, model);
			this.OS = OS;
			this.Cammera = Cammera;
		}
	    
	    public void display() {
	        dinfo();
	        System.out.println("Operating System: " + OS);
	        System.out.println("Camera Resolution: " + Cammera);
	    }

	  public void pic() {
		  System.out.println("Taking a picture with resolution "+Cammera);
	  }
	    public static void main(String[] args) {
	    	
	    	Device d=new Device("Brand","model");
	    	d.on();
	    	d.dinfo();
	    	d.off();

	    	Smartphone s = new Smartphone("Samsung","S24 Ultra","UI 7.5", "48 MP");
	    	s.display();
	    	s.pic();
	    	s.on();
	    	s.off();
	    	
			
		}
	}

