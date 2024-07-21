package AJP__Lab;

class InvalidEmailException extends Exception {
    InvalidEmailException(String message) {
        super(message);
    }
}

class User {
    String email;

    User(String email) throws InvalidEmailException {
        if (!email.contains("@")) {
            throw new InvalidEmailException("\nInvalid email address: " + email + "\nAs it doesn't contain '@'");
        }
        this.email = email;
    }
}

class Main {
    public static void main(String[] args) {
        try {
            User u1, u2, u3;
            u1 = new User("p3@gmail.com");
            System.out.println("User 1's email : " + u1.email);
            u2 = new User("pc3@gmail.com");
            System.out.println("User 2's email : " + u2.email);
            u3 = new User("p3mail.com");
            System.out.println("User 3's email : " + u3.email);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        }
    }
}
