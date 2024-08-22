package crud.utility;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class crud {
	
	Connection c = null;
	PreparedStatement ppst = null;
	ResultSet rs = null;
	
	public Connection  doSimple() throws SQLException {
		c  = ConnectionFactory.getConnectionFactory().getConnection() ;
		return c;
	}
	
	// crud  - create , read   , update  , delete 
	public void addStudent(Student s ) {
		
		try {
			c = doSimple();
			ppst  = c.prepareStatement("insert into student values(?, ? ,?)") ;
			ppst.setInt(1, s.getStudent_id());
			ppst.setString(2, s.getStudent_name()) ;
			ppst.setInt(3,  s.getStudent_marks()) ;
			
			int ans  = ppst.executeUpdate() ; // one row mai data  insert huva hai 
			
			if (ans != 0) {
				System.out.println("data got inserted succesfully ");
			}else {
				System.out.println("some thing went wrong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ppst!=null)
				try {
					ppst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(c!=null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		}
	public void update(Student s) {
		try {
			c = doSimple();
			ppst = c.prepareStatement("UPDATE student set smarks = ? where sid = ?");
			ppst.setInt(1, s.getStudent_marks());
			ppst.setInt(2, s.getStudent_id());
			int ans = ppst.executeUpdate();
			if (ans != 0) {
				System.out.println("data changed succesfully ");
			}else {
				System.out.println("some thing went wrong");
			}
		}
		catch(Exception e) {
		System.out.println(e);}
		finally {
			if(ppst!=null)
				try {
					ppst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(c!=null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		}
	
	public void delete(Student s) {
		try {
			c = doSimple();
			ppst = c.prepareStatement("delete from student where sid = ?");
			ppst.setInt(1, s.getStudent_id());
			int ans = ppst.executeUpdate();
			if (ans != 0) {
				System.out.println("data got deleted succesfully ");
			}else {
				System.out.println("some thing went wrong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAllData() {
		try {
			c=doSimple();
			ppst=c.prepareStatement("Select * from student");
			rs=ppst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getData(Student s) {
		try {
			c=doSimple();
			ppst=c.prepareStatement("select * from student where sid  = ?");
			ppst.setInt(1,s.getStudent_id());
			rs = ppst.executeQuery();
			while(rs.next())
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		crud s  = new crud ()  ;
		
		s.update(new Student(1 , "Piyush",500));
		s.delete(new Student(343 , "Sherya",200));
		s.addStudent(new Student(2, "Samay",450));
		s.getData(new Student(1,"Piyush",500));
		System.out.println("whole table");
		s.getAllData();
	}
}
