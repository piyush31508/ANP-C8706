package modelInt;
import java.sql.SQLException;
import java.util.List;

import Model.transactions;

public interface customerDao {
	void transferMoney(long fromAccount, long toAccount, double amount) throws SQLException;
	List<transactions> viewTransactionHistory(long a) throws SQLException;
}
