package com.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	private int pid;
	private String  pname;
	private double price;
	public Products(int pid, String pname, double price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
	}
	
	public Products() {}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", price=" + price + "]";
	}
	
	
	
}
