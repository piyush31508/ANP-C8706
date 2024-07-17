package AJP_Lab;

public class InvalidEmailException extends Exception {
	
	 InvalidEmailException(String message){
		super(message);
	}

}


public class user {
	String email;
	
	user(String email)throws InvalidEmailException{
		if (!email.contains("@")) {
            throw new InvalidEmailException("\nInvalid email address: " + email+"\nAs it doesn't contains '@'");
        }
        this.email = email;
    }
		
	
	}


public class Main {
	
	public static void main(String[] args) {
		try {
			user u1,u2,u3;
			u1 = new user("p3@gmail.com");
			System.out.println("User 1's email : "+u1.email);
			u2 = new user("pc3@gmail.com");
			System.out.println("User 2's email : "+u2.email);
			u3 = new user("p3mail.com");
			System.out.println("User 3's email : "+u3.email);
			
		}
		catch(InvalidEmailException e) {
		    e.printStackTrace();
		}
	}

}
