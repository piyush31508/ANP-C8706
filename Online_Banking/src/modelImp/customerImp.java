package modelImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Model.customer;
import Model.transactions;
import connection.singleton;
import modelInt.customerDao;

public class customerImp implements customerDao {
    Connection conn;
    ResultSet rs;
    PreparedStatement stmtWithdraw, stmtDeposit, stmtTransaction, stmt;
    accountantImp ob = new accountantImp();
    public boolean verifyCustomerLogin(long id, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccessful = false;

        try {
            // Establish a connection to the database
            conn = singleton.getsingleton().getConnection();

            // SQL query to check the credentials
            String sql = "SELECT * FROM customer WHERE customerId = ? AND Password = ?";
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

    public  customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customerId = ?";
        customer cust = null;
        try (Connection conn = singleton.getsingleton().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cust = new customer(
                            rs.getLong("customerId"),
                            rs.getLong("phoneNumber"),
                            rs.getString("customerName"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("Password")
                    );
                }
            }
        }
        return cust;
    }

    @Override
    public void transferMoney(long fromAccount, long toAccount, double amount) throws SQLException {
        PreparedStatement stmtWithdraw = null;
        PreparedStatement stmtDeposit = null;

        try {
            conn = singleton.getsingleton().getConnection();
            conn.setAutoCommit(false); // Start transaction

            String sqlWithdraw = "UPDATE accounts SET balance = balance - ? WHERE accountNumber = ? AND balance >= ?";
            String sqlDeposit = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";

            // Withdraw from sender's account
            stmtWithdraw = conn.prepareStatement(sqlWithdraw);
            stmtWithdraw.setDouble(1, amount);
            stmtWithdraw.setLong(2, fromAccount);
            stmtWithdraw.setDouble(3, amount);
            int rowsWithdrawn = stmtWithdraw.executeUpdate();
            
            if (rowsWithdrawn == 0) {
                throw new SQLException("Insufficient funds or account not found for withdrawal.");
            }

            // Deposit into receiver's account
            stmtDeposit = conn.prepareStatement(sqlDeposit);
            stmtDeposit.setDouble(1, amount);
            stmtDeposit.setLong(2, toAccount);
            int rowsDeposited = stmtDeposit.executeUpdate();
            
            if (rowsDeposited == 0) {
                throw new SQLException("Receiver's account not found.");
            }

            // Get the current timestamp
            LocalDateTime timestamp = LocalDateTime.now();

            // Record the 'Transfer' transaction
            recordTransaction(fromAccount, "Transfer", amount, fromAccount, toAccount);

            // Record the 'Receive' transaction
            recordTransaction(toAccount, "Receive", amount, fromAccount, toAccount);

            conn.commit(); // Commit transaction

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction on error
            }
            throw e; // Re-throw the exception
        } finally {
            if (stmtDeposit != null) stmtDeposit.close();
            if (stmtWithdraw != null) stmtWithdraw.close();
            if (conn != null) conn.close();
        }
    }


    @Override
    public List<transactions> viewTransactionHistory(long accountNumber) throws SQLException {
        List<transactions> transactionsList = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE accountNumber = ?";
        try (Connection conn = singleton.getsingleton().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions t = new transactions(
                        rs.getLong("transactionId"),
                        rs.getLong("accountNumber"),
                        rs.getString("transactionType"),
                        rs.getDouble("amount"),
                        rs.getObject("transactionDate", LocalDateTime.class),  // Correct column name
                        rs.getLong("sourceAccount"),
                        rs.getLong("destinationAccount")
                    );
                    transactionsList.add(t);
                }
            }
        }
        return transactionsList;
    }
    public void recordTransaction(long accountNumber, String transactionType, double amount, long sourceAccount, long destinationAccount) throws SQLException {
        String sql = "INSERT INTO transactions (accountNumber, transactionType, amount, transactionDate, sourceAccount, destinationAccount) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";
        try (Connection conn =  singleton.getsingleton().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, accountNumber);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            stmt.setLong(4, sourceAccount);
            stmt.setLong(5, destinationAccount);

            stmt.executeUpdate();
        }
    }
}
