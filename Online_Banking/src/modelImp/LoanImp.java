package modelImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Loan;
import connection.singleton;
import modelInt.LoanDao;

public class LoanImp implements LoanDao {
    
    private Connection getConnection() throws SQLException {
        return singleton.getsingleton().getConnection();
    }
    
    @Override
    public void addLoan(Loan loan) throws SQLException {
        String sql = "INSERT INTO loan (customerId, loanAmount, loanType, interestRate, loanTerm, startDate, endDate, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, loan.getCustomerId());
            stmt.setDouble(2, loan.getLoanAmount());
            stmt.setString(3, loan.getLoanType());
            stmt.setDouble(4, loan.getInterestRate());
            stmt.setInt(5, loan.getLoanTerm());
            stmt.setDate(6, new java.sql.Date(loan.getStartDate().getTime()));
            stmt.setDate(7, new java.sql.Date(loan.getEndDate().getTime()));
            stmt.setString(8, loan.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        loan.setLoanId(generatedKeys.getLong(1)); // Set the auto-generated loanId
                    } else {
                        throw new SQLException("Creating loan failed, no ID obtained.");
                    }
                }
            }
        }
    }

    @Override
    public Loan getLoanById(long loanId) throws SQLException {
        String sql = "SELECT * FROM loan WHERE loanId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, loanId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLoan(rs);
                } else {
                    return null;
                }
            }
        }
    }

    private Loan mapResultSetToLoan(ResultSet rs) throws SQLException {
        Loan loan = new Loan();
        loan.setLoanId(rs.getLong("loanId"));
        loan.setCustomerId(rs.getLong("customerId"));
        loan.setLoanAmount(rs.getDouble("loanAmount"));
        loan.setLoanType(rs.getString("loanType"));
        loan.setInterestRate(rs.getDouble("interestRate"));
        loan.setLoanTerm(rs.getInt("loanTerm"));
        loan.setStartDate(rs.getDate("startDate"));
        loan.setEndDate(rs.getDate("endDate"));
        loan.setStatus(rs.getString("status"));
        return loan;
    }

    @Override
    public List<Loan> getLoansByCustomerId(long customerId) throws SQLException {
        String sql = "SELECT * FROM loan WHERE customerId = ?";
        List<Loan> loans = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    loans.add(mapResultSetToLoan(rs));
                }
            }
        }
        return loans;
    }

    public void updateLoan(Loan loan) throws SQLException {
        String sql = "UPDATE loan SET loanAmount = ?, loanType = ?, interestRate = ?, loanTerm = ?, startDate = ?, endDate = ?, status = ? " +
                     "WHERE loanId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setDouble(1, loan.getLoanAmount());
            stmt.setString(2, loan.getLoanType());
            stmt.setDouble(3, loan.getInterestRate());
            stmt.setInt(4, loan.getLoanTerm());
            stmt.setDate(5, loan.getStartDate());
            stmt.setDate(6, loan.getEndDate());
            stmt.setString(7, loan.getStatus());
            stmt.setLong(8, loan.getLoanId());
            stmt.executeUpdate();
        }
    }
    
    public List<Loan> getAllLoans() throws SQLException {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loan";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Loan loan = new Loan(
                    resultSet.getLong("loanId"),
                    resultSet.getLong("customerId"),
                    resultSet.getDouble("loanAmount"),
                    resultSet.getString("loanType"),
                    resultSet.getDouble("interestRate"),
                    resultSet.getInt("loanTerm"),
                    resultSet.getDate("startDate"),
                    resultSet.getDate("endDate"),
                    resultSet.getString("status")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return loans;
    }

    public List<Loan> getLoansByStatus(String status) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loan WHERE status = ?";

        try (Connection connection =getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, status);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Loan loan = new Loan(
                        resultSet.getLong("loanId"),
                        resultSet.getLong("customerId"),
                        resultSet.getDouble("loanAmount"),
                        resultSet.getString("loanType"),
                        resultSet.getDouble("interestRate"),
                        resultSet.getInt("loanTerm"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate"),
                        resultSet.getString("status")
                    );
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return loans;
    }
    public boolean updateLoanStatus(long loanId, String newStatus) throws SQLException {
        String query = "UPDATE loan SET status = ? WHERE loanId = ?";

        try (Connection connection =getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newStatus);
            preparedStatement.setLong(2, loanId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    

    
}
