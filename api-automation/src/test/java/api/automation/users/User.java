package api.automation.users;

import java.util.HashMap;
import java.util.Map;

public class User {
	int id;
	String name;
	String username;
	String email;
	HashMap address;
	String phone;
	String website;
	
	HashMap company;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public HashMap getAddress() {
		return address;
	}
	public void setAddress(HashMap address) {
		this.address = address;
	}
	public HashMap getCompany() {
		return company;
	}
	public void setCompany(HashMap company) {
		this.company = company;
	}
	
	
	

}
