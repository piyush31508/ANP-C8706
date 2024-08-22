package Entity_interface_implementation;

import Entity_Interface.userDaoInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.user;
import Connection.singleton;

public class userDaoImplementation implements userDaoInterface{

	Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
	@Override
	public void registerUser(user user) throws SQLException {
		// TODO Auto-generated method stub
		try {
			 con = singleton.getsingleton().getConnection();
			 String sql = "insert into users(username, user_password, user_role) values (?,?,?)";
			 stmt = con.prepareStatement(sql);
			 stmt.setString(1, user.getUser_name());
			 stmt.setString(2, user.getUser_password());
			 stmt.setString(3, user.getRole());
		}
		finally {
			if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	@Override
	public user getUserById(int userId) throws SQLException {
		// TODO Auto-generated method stub
		try {
			 con = singleton.getsingleton().getConnection();
			 String sql = "select * from users where user_id = ?";
			 stmt = con.prepareStatement(sql);
			 stmt.setInt(1, userId);
			 rs = stmt.executeQuery();
			 while(rs.next()) {
				 System.out.print(rs.getString(1)+" ");
				 for(int i=2;i<=4;i++) {
					 System.out.print(rs.getString(i));
				 }
				 System.out.println();
			 }
		}
		finally {
			if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
           if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return null;
	}

}
