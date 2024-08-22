package main;
import modelImp.LoanImp;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelImp.accountantImp;
import modelImp.customerImp;
import Model.Loan;
import Model.accounts;
import Model.customer;
import Model.transactions;

public class Main {

	public void verifyPhone(long no) {
		String s =  String.valueOf(no);
		if(s.length()!=10) {
			throw new IllegalArgumentException("Error: Phone number length should be 10 digits");
		}
	}
	public void verifyEmail(String e) {
		if(!((!e.isEmpty() && e.endsWith("@gmail.com") && Character.isLetter(e.charAt(0)) ))) {
			throw new IllegalArgumentException("Error: Invalid Email");
		}
	}
	
	void handleAccountantOperations(Scanner sc, accountantImp accountantDAO, LoanImp loanDAO) throws SQLException {
		    System.out.print("Enter your Admin ID: ");
		    long id = sc.nextLong();
		    System.out.print("Enter your password: ");
		    String password = sc.next();
		    
		    if (!accountantDAO.verifyAccountantLogin(id, password)) {
		        System.out.println("Invalid username or password. Exiting...");
		        return; 
		    }
		while(true) {
	    System.out.println("Accountant Operations:");
	    System.out.println("1. Add Account");
	    System.out.println("2. Edit Account");
	    System.out.println("3. Remove Account");
	    System.out.println("4. View Account Details");
	    System.out.println("5. View All Accounts");
	    System.out.println("6. Deposit Money");
	    System.out.println("7. Withdraw Money");
	    System.out.println("8. Loan Opeartions");
	    System.out.println("9. Add admin/Accountant");
	    System.out.println("10. Log out");
	    System.out.print("Please choose an option: ");
	    int ch = sc.nextInt();
	    sc.nextLine(); // Consume newline
	    switch (ch) {
        case 1:
            System.out.print("Enter customer ID: ");
            long customerId = sc.nextLong();
            sc.nextLine(); // Consume newline
            String accountType = "";

            // Step 2: Check if customer exists
            if (!accountantDAO.doesCustomerExist(customerId)) {
                System.out.println("Customer does not exist. Please provide the following details.");
                
                // Get customer details
                System.out.print("Enter your name: ");
                String name = sc.nextLine();
                System.out.print("Enter your address: ");
                String address = sc.nextLine();
                System.out.print("Enter your phone number: ");
                long phoneNumber = sc.nextLong();
                verifyPhone(phoneNumber);
                System.out.print("Enter your email:");
                String email = sc.next(); 
                verifyEmail(email);
                System.out.println("Create Password");
                String pass = sc.nextLine();
                sc.nextLine(); 

                
                
                // Step 3: Add customer to the database
                customer newCustomer = new customer(0, phoneNumber, name, email, address,password);
                accountantDAO.addCustomer(newCustomer);
                customerId = newCustomer.getCustomerId(); // Retrieve the generated ID from the database
                System.out.println("Customer added successfully with ID: " + customerId);
                System.out.print("Enter account type (Saving/Current): ");
                accountType = sc.nextLine();
            }

            // Step 4: Validate account type
            while (true) {
                if (accountType.equalsIgnoreCase("Saving") || accountType.equalsIgnoreCase("Current")) {
                    break;
                } else {
                    System.out.println("Invalid account type. Please enter 'Saving' or 'Current'.");
                    accountType = sc.nextLine();
                }
            }

            // Step 5: Set initial balance and create account
            double balance = 0;
            accounts account = new accounts(0, customerId, accountType, balance);

            // Step 6: Add account to the database and get the auto-generated account ID
            accountantDAO.addAccount(account);
            long accountNumber = account.getAccountNumber(); // Retrieve the generated account number from the database

            // Step 7: Confirm account creation and display account number
            System.out.println("Account added successfully.");
            System.out.println("Your account number is: " + accountNumber);
            break;

            
        case 2:
        	System.out.print("Enter account number to edit: ");
            accountNumber = sc.nextLong();
            sc.nextLine(); // Consume newline
            accounts currentAccount = accountantDAO.viewAccountByNumber(accountNumber);

            if (currentAccount != null) {
                System.out.println("Current Account Details:");
                System.out.println("Account Number: " + currentAccount.getAccountNumber());
                System.out.println("Customer ID: " + currentAccount.getCustomerId());
                System.out.println("Account Type: " + currentAccount.getAccountType());
                System.out.println("Balance: " + currentAccount.getBalance());

                // Retrieve customer ID to access customer details
                long cid = currentAccount.getCustomerId();
                // Fetch and display current customer details
                customer cust = new customer(0, 0L, null, null, null, null);
                if (cust != null) {
                    System.out.println("Current Customer Details:");
                    System.out.println("Name: " + cust.getCustomerName());
                    System.out.println("Address: " + cust.getAddress());
                    System.out.println("Phone Number: " + cust.getPhoneNumber());
                }

                // Ask for changes
                System.out.print("Do you want to change the account type? (yes/no): ");
                String changeType = sc.nextLine();
                
                String newAccountType = currentAccount.getAccountType();
                if (changeType.equalsIgnoreCase("yes")) {
                    System.out.print("Enter new account type (Saving/Current): ");
                    newAccountType = sc.nextLine();
                    if (!newAccountType.equalsIgnoreCase("Saving") && !newAccountType.equalsIgnoreCase("Current")) {
                        System.out.println("Invalid account type. Please enter 'Saving' or 'Current'.");
                        break;
                    }
                }

                // Ask if user wants to change customer details
                System.out.print("Do you want to change the address? (yes/no): ");
                String changeAddress = sc.nextLine();
                String newAddress = cust.getAddress();
                if (changeAddress.equalsIgnoreCase("yes")) {
                    System.out.print("Enter new address: ");
                    newAddress = sc.nextLine();
                }

                System.out.print("Do you want to change the phone number? (yes/no): ");
                String changePhone = sc.nextLine();
                long newPhone = cust.getPhoneNumber();
                
                if (changePhone.equalsIgnoreCase("yes")) {
                    System.out.print("Enter new phone number: ");
                    newPhone = sc.nextLong();
                    verifyPhone(newPhone);
                    sc.nextLine(); // Consume newline
                }

                System.out.println("Summary of changes:");
                System.out.println("Account Number: " + accountNumber);
                System.out.println("New Account Type: " + newAccountType);
                System.out.println("New Address: " + newAddress);
                System.out.println("New Phone Number: " + newPhone);

                // Confirm changes
                System.out.print("Are you sure you want to apply these changes? (yes/no): ");
                String confirmation = sc.nextLine();
                if (!confirmation.equalsIgnoreCase("yes")) {
                    System.out.println("Account update canceled.");
                    break;
                }

                // Update account and customer details
                try {
                    accounts editAccount = new accounts(accountNumber, (int) cid, newAccountType, currentAccount.getBalance());
                    accountantDAO.editAccount(editAccount);
                    customer c = new customer(cid, newPhone, cust.getCustomerName(), cust.getEmail(), newAddress, cust.getEmail());
                    accountantImp accountantImp = new accountantImp();
					accountantImp.updateCustomer(c);
                    System.out.println("Account and customer details updated successfully.");
                } catch (SQLException e) {
                    System.out.println("An error occurred while updating the account: " + e.getMessage());
                }
            } else {
                System.out.println("Account not found.");
            }
            break;

        case 3:
            System.out.print("Enter account number to remove: ");
            int accountToRemove = sc.nextInt();
            accounts check = accountantDAO.viewAccountByNumber(accountToRemove);
            if (check != null) {
                accountantDAO.removeAccount(accountToRemove);
                System.out.println("Account removed successfully.");
            } else {
                System.out.println("Account not found");
            }
            break;
        case 4:
        	System.out.print("Enter account number to view: ");
        	int accountToView = sc.nextInt();
        	accounts viewAccount = accountantDAO.viewAccountByNumber(accountToView);
        	if (viewAccount != null) {
        	    System.out.println("Account Details:");
        	    System.out.println("Account Number: " + viewAccount.getAccountNumber());
        	    System.out.println("Customer ID: " + viewAccount.getCustomerId());
        	    System.out.println("Account Type: " + viewAccount.getAccountType());
        	    System.out.println("Balance: " + viewAccount.getBalance());

        	    // Fetch and display customer details
        	    long CID = viewAccount.getCustomerId();
        	    
        	    customer viewCustomer = new customer(0, 0L, null, null, null, null);
        	    System.out.println("Customer Details:");
				System.out.println("Name: " + viewCustomer.getCustomerName());
				System.out.println("Address: " + viewCustomer.getAddress());
				System.out.println("Phone Number: " + viewCustomer.getPhoneNumber());
				System.out.println("Email: " + viewCustomer.getEmail());
        	} else {
        	    System.out.println("Account not found");
        	}
        	break;
        case 5:
        	List<accounts> allAccounts = accountantDAO.viewAllAccounts();
            System.out.println("All Accounts:");
            
            int index = 0;
            while (index < allAccounts.size()) {
                accounts acc = allAccounts.get(index);
                System.out.println("Account Number: " + acc.getAccountNumber() +
                                   ", Customer ID: " + acc.getCustomerId() +
                                   ", Account Type: " + acc.getAccountType() +
                                   ", Balance: " + acc.getBalance());
                index++;
            }
            break;
        case 6:
            System.out.print("Enter account number to deposit money: ");
            int accountToDeposit = sc.nextInt();
            System.out.print("Enter amount to deposit: ");
            double depositAmount = sc.nextDouble();
            
            if (depositAmount <= 0) {
                System.out.println("Deposit amount must be greater than zero.");
                break;
            }

            accounts depositAccount = accountantDAO.viewAccountByNumber(accountToDeposit);
            if (depositAccount != null) {
                // Update the balance in the database
                accountantDAO.deposit(accountToDeposit, depositAmount);
                
                // Fetch the updated balance
                depositAccount = accountantDAO.viewAccountByNumber(accountToDeposit);
                System.out.println("Money deposited successfully.");
                System.out.println("Current balance = " + depositAccount.getBalance());
            } else {
                System.out.println("Account not found.");
            }
            break;

        case 7:
        	System.out.print("Enter account number to withdraw money: ");
        	int accountToWithdraw = sc.nextInt();
        	System.out.print("Enter amount to withdraw: ");
        	double withdrawAmount = sc.nextDouble();

        	if (withdrawAmount <= 0) {
        	    System.out.println("Withdrawal amount must be greater than zero.");
        	} else {
        	    try {
        	        // Perform the withdrawal operation
        	        accountantDAO.withdraw(accountToWithdraw, withdrawAmount);
        	    } catch (SQLException e) {
        	        System.out.println("An error occurred during the withdrawal process.");
        	        e.printStackTrace();
        	    }
        	}

            break;
       
            
        case 8:
            System.out.println("Loan Operations:");
            System.out.println("1. View All Loans");
            System.out.println("2. View Loans by Status");
            System.out.println("3. Update Loan Status");
            System.out.print("Please choose an option: ");
            int loanChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (loanChoice) {
                case 1:
                    // View all loans
                    List<Loan> allLoans = loanDAO.getAllLoans();
                    for (Loan loan : allLoans) {
                        System.out.println("Loan ID: " + loan.getLoanId());
                        System.out.println("Customer ID: " + loan.getCustomerId());
                        System.out.println("Loan Amount: " + loan.getLoanAmount());
                        System.out.println("Loan Type: " + loan.getLoanType());
                        System.out.println("Interest Rate: " + loan.getInterestRate());
                        System.out.println("Loan Term: " + loan.getLoanTerm());
                        System.out.println("Start Date: " + loan.getStartDate());
                        System.out.println("End Date: " + loan.getEndDate());
                        System.out.println("Status: " + loan.getStatus());
                        System.out.println("-----------------------------");
                    }
                    break;

                case 2:
                    // View loans by status
                    System.out.print("Enter loan status to filter by: ");
                    String status = sc.next();
                    List<Loan> loansByStatus = loanDAO.getLoansByStatus(status);
                    for (Loan loan : loansByStatus) {
                        System.out.println("Loan ID: " + loan.getLoanId());
                        System.out.println("Customer ID: " + loan.getCustomerId());
                        System.out.println("Loan Amount: " + loan.getLoanAmount());
                        System.out.println("Loan Type: " + loan.getLoanType());
                        System.out.println("Interest Rate: " + loan.getInterestRate());
                        System.out.println("Loan Term: " + loan.getLoanTerm());
                        System.out.println("Start Date: " + loan.getStartDate());
                        System.out.println("End Date: " + loan.getEndDate());
                        System.out.println("Status: " + loan.getStatus());
                        System.out.println("-----------------------------");
                    }
                    break;

                case 3:
                    // Update loan status
                    System.out.print("Enter loan ID to update status: ");
                    long loanId = sc.nextLong();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter new status: ");
                    String newStatus = sc.nextLine();
                    boolean updateStatus = loanDAO.updateLoanStatus(loanId, newStatus);
                    if (updateStatus) {
                        System.out.println("Loan status updated successfully.");
                    } else {
                        System.out.println("Failed to update loan status.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            break;
        case 9:
        	System.out.println("we will generate your admin ID by ourself\nEnter name");
        	String name = sc.next();
        	System.out.println("Enter password ");
        	String pass = sc.next();
        	long aid = accountantDAO.addAccountant(name, pass);
        	System.out.println("Your admin id is "+aid);
        	
        	break;
        case 10:
            System.out.println("Logging out...");
            return; 
        default:
            System.out.println("Invalid choice. Please try again.");
    }
  }
}
    
      void handleCustomerOperations(Scanner sc, customerImp c, accountantImp ac, LoanImp loanDAO) throws SQLException {
    	  
    	  System.out.print("Enter your ID(customer Id): ");
		    long id = sc.nextLong();
		    System.out.print("Enter your password: ");
		    String password = sc.next();
		    
		    if (!c.verifyCustomerLogin(id, password)) {
		        System.out.println("Invalid username or password. Exiting...");
		        return; 
		    }
    	  while(true) {
    	 System.out.println("Customer Operations:");
         System.out.println("1. Transfer money");
         System.out.println("2. check transaction history");
         System.out.println("3. Apply for Loan");
         System.out.println("4. View Loan Details");
         System.out.println("5. Log Out");
         System.out.print("Please choose an option: ");
         int ch = sc.nextInt();
         switch(ch) {
         case 1:	 
        	 System.out.print("Enter your account number: ");
             long sender = sc.nextLong();
             sc.nextLine(); // consume newline
             System.out.print("Enter the recipient's account number: ");
             long reciever = sc.nextLong();
             sc.nextLine(); // consume newline
             if(ac.viewAccountByNumber(sender) != null)
            	 if(ac.viewAccountByNumber(reciever)!=null) {
             System.out.print("Enter the amount to transfer: ");
             double amount = sc.nextDouble();
             sc.nextLine(); // consume newline
             c.transferMoney(sender, reciever, amount);
             System.out.println("Your account balance = "+ac.viewAccountByNumber(sender).getBalance());
             }
            	 else {
            		 System.out.println("Reciever account not found");
            	 }
             else {
        		 System.out.println("sender account not found");
        	 }
             break;
         case 2:
        	 System.out.println("Enter your bank account no, whose transaction history you want to get");
        	 long a = sc.nextLong();
        	 List<transactions> t = new ArrayList<>();
        	 if(ac.viewAccountByNumber(a)!=null) {
        		 t=c.viewTransactionHistory(a);
        		 if (t.isEmpty()) {
        		        System.out.println("No transactions found for this account.");
        		    } else {
        		        System.out.println("Transaction History:");
        		        int i = 0;
        		        while (i < t.size()) {
        		            transactions transaction = t.get(i);
        		            System.out.println("Transaction ID: " + transaction.getTransactionId());
        		            System.out.println("Account Number: " + transaction.getAccountNumber());
        		            System.out.println("Transaction Type: " + transaction.getTransactionType());
        		            System.out.println("Amount: " + transaction.getAmount());
        		            System.out.println("Transaction Date: " + transaction.getTransactionDate());
        		            System.out.println("Source Account: " + transaction.getSourceAccount());
        		            System.out.println("Destination Account: " + transaction.getDestinationAccount());
        		            System.out.println("---------------------------------------");
        		            i++;
        		        }
        		    }
        	 
        	 }
        	 break;
         case 3:
             // Apply for loan
             System.out.print("Enter the loan amount: ");
             double amount = sc.nextDouble();
             sc.nextLine(); // Consume newline
             System.out.print("Enter loan type: ");
             String loanType = sc.nextLine();
             System.out.print("interest rate : 12");
             double interestRate = 12.0;
             sc.nextLine(); // Consume newline
             System.out.print("Enter loan term (in months): ");
             int loanTerm = sc.nextInt();
             sc.nextLine(); // Consume newline
             System.out.print("Enter start date (yyyy-mm-dd): ");
             String startDateStr = sc.nextLine();
             Date startDate = Date.valueOf(startDateStr);
             System.out.print("Enter end date (yyyy-mm-dd): ");
             String endDateStr = sc.nextLine();
             Date endDate = Date.valueOf(endDateStr);

             // Using the constructor with parameters
             Loan loan = new Loan(0, id, amount, loanType, interestRate, loanTerm, startDate, endDate, "Pending");
             try {
                 loanDAO.addLoan(loan);
                 System.out.println("Loan application submitted successfully.");
             } catch (SQLException e) {
                 e.printStackTrace();
                 System.out.println("Error applying for loan.");
             }
             break;
         case 4:
             long viewCustomerId = id;
             sc.nextLine(); // Consume newline
             try {
                 List<Loan> loans = loanDAO.getLoansByCustomerId(viewCustomerId);
                 if (loans.isEmpty()) {
                     System.out.println("No loans found for this customer.");
                 } else {
                     for (Loan l : loans) {
                         System.out.println(l); // Assuming toString() is overridden in Loan
                     }
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
                 System.out.println("Error retrieving loan details.");
             }
             break;
         case 5:
        	 System.out.println("Logging out...");
             return; 
        	 default:
        		 System.out.println("Invalid Choice");
         }
       }
     }
      
	public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        accountantImp ac = new accountantImp(); // Initialize accountantImp instance
        customerImp cDao = new customerImp();
        Main mainInstance = new Main();
        LoanImp loanDAO = new LoanImp();
        while (true) {
            System.out.println("Welcome to Online Banking System");
            System.out.println("1. Accountant Operations");
            System.out.println("2. Customer Operations");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                	mainInstance.handleAccountantOperations(sc, ac, loanDAO);
                    break;
                case 2:
                	mainInstance.handleCustomerOperations(sc, cDao, ac, loanDAO);
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
