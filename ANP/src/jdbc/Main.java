package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Main {
	public static void main(String[] args) throws SQLException{
		//Driver Loaded
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//connection  (url/dbname, user-name, password)
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajp","root","2003@Piyush");
		
		
		//create query, stmt, Prepstmt
		String q = " SELECT * FROM employee";
		
		Statement stmt = con.createStatement();
		
		/*
		 executeQuery for projection
		 executeUpdate when we need to update records in a table
		 
		 */
		ResultSet set = stmt.executeQuery(q); 
		
		
		//Process the data
		int id,salary;
		String name, city, state;
		while(set.next()) {
			id=set.getInt("empID_id");
			salary = set.getInt("emp_salary");
			name=set.getString("emp_name");
			city = set.getString("emp_city");
			System.out.println(id+" "+name+" "+salary+" "+city);
			
		}
		
		con.close();//closing connection resource
	}
}
