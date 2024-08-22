package modelInt;
import java.sql.SQLException;
import java.util.List;

import Model.Loan;

public interface LoanDao {
    void addLoan(Loan loan) throws SQLException;
    Loan getLoanById(long loanId) throws SQLException;
    List<Loan> getLoansByCustomerId(long customerId) throws SQLException;
    void updateLoan(Loan loan) throws SQLException;
}
