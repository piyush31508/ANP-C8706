package Entity;

public class buyer {
	 private int buyerId;
     private String name;
     private String email;
     private String phone;	   
     private String address;
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public buyer(int buyerId, String name, String email, String phone, String address) {
		super();
		this.buyerId = buyerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	@Override
	public String toString() {
		return "buyer [buyerId=" + buyerId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address="
				+ address + "]";
	}
     
     
}
