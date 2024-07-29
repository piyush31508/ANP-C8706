package Thread_;

public class Common {
	public static synchronized void func(String name) {
		System.out.print("Welcome");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name);
	}

}
