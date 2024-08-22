package Model;

import java.sql.Date;

public class Loan {
    @Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", customerId=" + customerId + ", loanAmount=" + loanAmount + ", loanType="
				+ loanType + ", interestRate=" + interestRate + ", loanTerm=" + loanTerm + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", status=" + status + "]";
	}

	private long loanId;
    private long customerId;
    private double loanAmount;
    private String loanType;
    private double interestRate;
    private int loanTerm; // in months
    private Date startDate;
    private Date endDate;
    private String status;

    // Constructor
    public Loan(long loanId, long customerId, double loanAmount, String loanType, double interestRate, int loanTerm, Date startDate, Date endDate, String status) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long l) {
        this.loanId = l;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Loan(){
    	
    }
}
