package modelInt;

import java.sql.SQLException;
import java.util.List;
import Model.accounts;

public interface accountDao {
	  void addAccount(accounts account) throws SQLException ;
		
	
	 void editAccount(accounts account) throws SQLException;
	 void removeAccount(int accountNumber) throws SQLException;
	 List<accounts> viewAllAccounts()throws SQLException;
	 void deposit(int accountNumber, double amount)throws SQLException;
	 void withdraw(int accountNumber, double amount)throws SQLException;


	accounts viewAccountByNumber(long accountNumber) throws SQLException;


}
