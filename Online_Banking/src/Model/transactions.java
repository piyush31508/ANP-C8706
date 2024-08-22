package Model;

import java.time.LocalDateTime;

public class transactions {
    private long transactionId;
    private long accountNumber;
    private String transactionType;  // "Send", "Received", "Deposit", "Withdraw", etc.
    private double amount;
    private LocalDateTime transactionDate;
    private long sourceAccount;
    private long destinationAccount;

    public transactions(long transactionId, long accountNumber, String transactionType, double amount,
                         LocalDateTime transactionDate, long sourceAccount, long destinationAccount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(long sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public long getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(long destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    @Override
    public String toString() {
        return "transactions [transactionId=" + transactionId + ", accountNumber=" + accountNumber
                + ", transactionType=" + transactionType + ", amount=" + amount + ", transactionDate=" + transactionDate
                + ", sourceAccount=" + sourceAccount + ", destinationAccount=" + destinationAccount + "]";
    }
}
