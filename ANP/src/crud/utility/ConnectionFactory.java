package crud.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory  {
 
	// siglton class 
	
	
	 String url  = "jdbc:mysql://localhost:3306/ajp";
	 String dbname  = "root" ;
	 String dbpass  = "2003@Piyush" ;
		
	private ConnectionFactory() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		}
	
	
	
	
	private static ConnectionFactory  connectionFactory = null  ;
	
	
	
	

	public static ConnectionFactory getConnectionFactory() {
		if ( connectionFactory  ==  null) {
			
			 connectionFactory   = new  ConnectionFactory() ;
			 
		}
		return connectionFactory ;
	}
	
	
	// method to get connection from this singleton class 
	
	
	
	public Connection getConnection() throws SQLException {
		
		Connection con = DriverManager.getConnection(url, dbname, dbpass) ;
		
		
		return con  ;
		
	}
	
	
	

}
