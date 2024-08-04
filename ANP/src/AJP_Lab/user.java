package AJP_1_Lab;

public class user {
	String email;
	
	user(String email)throws InvalidEmailException{
		if (!email.contains("@")) {
            throw new InvalidEmailException("\nInvalid email address: " + email+"\nAs it doesn't contains '@'");
        }
        this.email = email;
    }
		
	
	}
