package Model;

public class customer {
	private long customerId;
	long phoneNumber;
	private String customerName, email, address,password;
	public customer(long customerId, long phoneNumber, String customerName, String email, String address, String password) {
		super();
		this.customerId = customerId;
		this.phoneNumber = phoneNumber;
		this.customerName = customerName;
		this.email = email;
		this.address = address;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "customer [customerId=" + customerId + ", phoneNumber=" + phoneNumber + ", customerName=" + customerName
				+ ", email=" + email + ", address=" + address + "]";
	}
	
	
}
