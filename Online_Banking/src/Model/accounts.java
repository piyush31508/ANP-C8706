package Model;

public class accounts {
    private long accountNumber; 
    private long customerId;
    private String accountType;
    private double balance;

    public accounts(long accountNumber, long customerId, String accountType, double balance) {
        super();
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "accounts [accountNumber=" + accountNumber + ", customerId=" + customerId + ", accountType="
                + accountType + ", balance=" + balance + "]";
    }

    public long getAccountNumber() {  
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) { 
        this.accountNumber = accountNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
