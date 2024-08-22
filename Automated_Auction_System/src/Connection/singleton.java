package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class singleton  {
 
	// singleton class 
	
	
	 String url  = "jdbc:mysql://localhost:3306/auction";
	 String dbname  = "root" ;
	 String dbpass  = "2003@Piyush" ;
		
	private singleton() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		}
	
	
	
	
	private static singleton  singleton = null  ;
	
	
	
	

	public static singleton getsingleton() {
		if ( singleton  ==  null) {
			
			 singleton   = new  singleton() ;
			 
		}
		return singleton ;
	}
	
	
	// method to get connection from this singleton class 
	
	
	
	public Connection getConnection() throws SQLException {
		
		Connection con = DriverManager.getConnection(url, dbname, dbpass) ;
		
		
		return con  ;
		
	}
	
	
	

}
