package Entity_Interface;

import java.sql.SQLException;

import Entity.user;

public interface userDaoInterface {
	 void registerUser(user user) throws SQLException;
	 user getUserById(int userId) throws SQLException;
}
