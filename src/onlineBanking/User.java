package onlineBanking;
import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fname;
	private String lname;
	private String email;
	private String username;
	private String password;
	private String phone;
	private String dob;
	private String balance;
	
	public User() {
		this.fname = "";
		this.lname = "";
		this.email = "";
		this.username = "";
		this.password = "";
		this.phone = "";
		this.dob = "";
		this.balance = "";
	}
	
	public User(String fname, String lname, String email, String phone, String username, String password, String dob, String balance) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.dob = dob;
		this.balance = balance;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}

}
