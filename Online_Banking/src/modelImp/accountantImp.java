package modelImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connection.singleton;
import Model.accounts;
import Model.customer;
import modelInt.accountDao;

public class accountantImp implements accountDao {
    
    private Connection getConnection() throws SQLException {
        return singleton.getsingleton().getConnection();
    }
    
    public boolean verifyAccountantLogin(Long id, String password) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccessful = false;

        try {
            // Establish a connection to the database
            conn =getConnection();

            // SQL query to check the credentials
            String sql = "SELECT * FROM accountant WHERE adminId = ? AND adminPassword = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setString(2, password);

            // Execute the query
            rs = pstmt.executeQuery();

            // If a record is found, credentials are correct
            if (rs.next()) {
                loginSuccessful = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loginSuccessful;
    }

    @Override
    public void addAccount(accounts account) throws SQLException {
        String sql = "INSERT INTO accounts (customerId, accountType, balance) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, account.getCustomerId());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();

            // Retrieve the generated account number
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setAccountNumber(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }
        }
    }
    
    
    public boolean doesCustomerExist(long customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customer WHERE customerId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void addCustomer(customer customer) {
        String sql = "INSERT INTO customer (customerName, address, phoneNumber, email, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getAddress());
            stmt.setLong(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPassword());
            
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        customer.setCustomerId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void editAccount(accounts account) throws SQLException {
        String sql = "UPDATE accounts SET accountType = ?, balance = ? WHERE accountNumber = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, account.getAccountType());
            stmt.setDouble(2, account.getBalance());
            stmt.setLong(3, account.getAccountNumber());
            stmt.executeUpdate();
        }
    }


    @Override
    public List<accounts> viewAllAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";
        List<accounts> accountsList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                accounts account = new accounts(
                        rs.getLong("accountNumber"),
                        rs.getInt("customerId"),
                        rs.getString("accountType"),
                        rs.getDouble("balance")
                );
                accountsList.add(account);
            }
        }
        return accountsList;
    }


    public  void updateCustomer(customer c) throws SQLException {
        String sql = "UPDATE customer SET customerName = ?, email = ?, address = ?, phoneNumber = ? WHERE customerId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, c.getCustomerName());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getAddress());
            stmt.setLong(4, c.getPhoneNumber());
            stmt.setLong(5, c.getCustomerId());
            stmt.executeUpdate();
        }
    }

	@Override
	public void removeAccount(int accountNumber) throws SQLException {
		// TODO Auto-generated method stub
		 String sql = "DELETE FROM accounts WHERE accountNumber = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setLong(1, accountNumber);
	            stmt.executeUpdate();
	        }
	}

	@Override
	public accounts viewAccountByNumber(long accountNumber) throws SQLException {
		// TODO Auto-generated method stub
		 String sql = "SELECT * FROM accounts WHERE accountNumber = ?";
	        accounts account = null;
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setLong(1, accountNumber);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    account = new accounts(
	                            rs.getLong("accountNumber"),
	                            rs.getInt("customerId"),
	                            rs.getString("accountType"),
	                            rs.getDouble("balance")
	                    );
	                }
	            }
	        }
	        return account;
	}

	@Override
	public void deposit(int accountNumber, double amount) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		try {
		    conn.setAutoCommit(false); // Disable auto-commit
		    String sql = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setDouble(1, amount);
		        stmt.setInt(2, accountNumber);
		        stmt.executeUpdate();
		    }
	        recordTransaction(accountNumber, "Deposit", amount, accountNumber, accountNumber);

		    conn.commit(); // Commit the transaction
		} catch (SQLException e) {
		    conn.rollback(); // Rollback in case of an error
		    throw e;
		} finally {
		    conn.setAutoCommit(true); // Restore auto-commit
		    conn.close(); // Close the connection
		}

	        }
	

	@Override
	public void withdraw(int accountNumber, double amount) throws SQLException {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
	    }

	    Connection conn = null;
	    try {
	        conn = getConnection();
	        conn.setAutoCommit(false); // Begin transaction

	        // Check if there is sufficient balance
	        String checkSql = "SELECT balance FROM accounts WHERE accountNumber = ?";
	        double currentBalance = 0.0;
	        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
	            checkStmt.setInt(1, accountNumber);
	            ResultSet rs = checkStmt.executeQuery();
	            if (rs.next()) {
	                currentBalance = rs.getDouble("balance");
	            } else {
	                throw new SQLException("Account not found.");
	            }
	        }

	        // Check if the account has enough balance for withdrawal
	        if (currentBalance < amount) {
	            throw new SQLException("Insufficient funds for withdrawal.");
	        }

	        // Withdraw money
	        String withdrawSql = "UPDATE accounts SET balance = balance - ? WHERE accountNumber = ?";
	        try (PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSql)) {
	            withdrawStmt.setDouble(1, amount);
	            withdrawStmt.setInt(2, accountNumber);
	            int rowsAffected = withdrawStmt.executeUpdate();

	            if (rowsAffected == 0) {
	                throw new SQLException("Account not found.");
	            }
	        }

	        recordTransaction(accountNumber, "Withdraw", amount, accountNumber, accountNumber);

	        // Commit transaction
	        conn.commit();

	        // Fetch the updated balance immediately after committing the transaction
	        String verifySql = "SELECT balance FROM accounts WHERE accountNumber = ?";
	        double updatedBalance = 0.0;
	        try (PreparedStatement verifyStmt = conn.prepareStatement(verifySql)) {
	            verifyStmt.setInt(1, accountNumber);
	            ResultSet rs = verifyStmt.executeQuery();
	            if (rs.next()) {
	                updatedBalance = rs.getDouble("balance");
	                System.out.println("Money withdrawn successfully.");
	                System.out.println("Updated balance after withdrawal: " + updatedBalance); // Updated log
	            } else {
	                throw new SQLException("Account not found.");
	            }
	        }

	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Rollback transaction on error
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(true); // Restore auto-commit
	                conn.close();
	            } catch (SQLException closeEx) {
	                closeEx.printStackTrace();
	            }
	        }
	    }
	}
	public void recordTransaction(long accountNumber, String transactionType, double amount, long sourceAccount, long destinationAccount) throws SQLException {
		LocalDateTime timestamp = LocalDateTime.now();
	    String sql = "INSERT INTO transactions (accountNumber, transactionType, amount, transactionDate, sourceAccount, destinationAccount) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        System.out.println("Attempting to insert transaction: " + accountNumber + ", " + transactionType + ", " + amount);

	        stmt.setLong(1, accountNumber);
	        stmt.setString(2, transactionType);
	        stmt.setDouble(3, amount);
	        stmt.setLong(4, sourceAccount);
	        stmt.setLong(5, destinationAccount);

	        int rowsInserted = stmt.executeUpdate();
	        System.out.println("Rows inserted: " + rowsInserted);
	    }
	}
	public long addAccountant(String name, String password) throws SQLException {
	    String sql = "INSERT INTO accountant (adminName, adminPassword) VALUES (?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        
	        stmt.setString(1, name);
	        stmt.setString(2, password);
	        stmt.executeUpdate();
	        
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getLong(1); // Return the generated ID
	            } else {
	                throw new SQLException("Creating admin/accountant failed, no ID obtained.");
	            }
	        }
	    }
	}

}




