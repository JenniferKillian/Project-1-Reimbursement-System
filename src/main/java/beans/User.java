package beans;

import java.util.List;



public class User {

	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private UserType userType;
	private List<Reimbursement> reimbs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public List<Reimbursement> getReimbs() {
		return reimbs;
	}
	public void setreimbs(List<Reimbursement> reimbs) {
		this.reimbs = reimbs;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
