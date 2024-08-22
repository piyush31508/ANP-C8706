package Entity;

public class user {
	private String user_name;
	private String user_password;
	private String role;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public user(String user_name, String user_password, String role) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.role = role;
	}
	@Override
	public String toString() {
		return "user [user_name=" + user_name + ", user_password=" + user_password + ", role=" + role + "]";
	}
	
	
}
