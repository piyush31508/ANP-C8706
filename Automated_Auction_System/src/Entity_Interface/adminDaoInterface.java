package Entity_Interface;

import java.sql.SQLException;

import Entity.dispute;
import Entity.user;

public interface adminDaoInterface {
	void registerUser(user user) throws SQLException;
    user getUserById(int userId) throws SQLException;
    dispute getDisputeReport(int disputeId) throws SQLException;
    
}
