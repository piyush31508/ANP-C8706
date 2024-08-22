package Entity;

public class seller {
	private int sellerId;
    private String name;
    private String email;
    private String phone;
    private String address;
	public seller(int sellerId, String name, String email, String phone, String address) {
		super();
		this.sellerId = sellerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
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
	@Override
	public String toString() {
		return "seller [sellerId=" + sellerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + "]";
	}
    
}
