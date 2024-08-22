package Model;

public class accountant {
	private int adminId;
	private String adminName, adminpassword;
	public accountant(int adminId, String adminName, String adminpassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminpassword = adminpassword;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	@Override
	public String toString() {
		return "accountant [adminId=" + adminId + ", adminName=" + adminName + ", adminpassword=" + adminpassword + "]";
	}
	
}
