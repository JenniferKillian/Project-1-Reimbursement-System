package beans;

public class UserType {
	
	private int id;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		if (role == "Employee") {
			this.id = 1;
		}
		if (role == "Finance Manager") {
			this.id = 2;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		if (id == 1) {
			this.role = "Employee";
		}
		if (id == 2) {
			this.role = "Finance Manager";
		}
	}

}
