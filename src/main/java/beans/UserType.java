package beans;

public class UserType {
	
	private int id;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		if (role.toLowerCase() == "employee") {
			this.role = "employee";
			this.id = 1;
		}
		if (role.toLowerCase() == "finance manager" || role.toLowerCase() == "finmanager") {
			this.role = "finance manager";
			this.id = 2;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		if (id == 1) {
			this.role = "employee";
		}
		if (id == 2) {
			this.role = "finance manager";
		}
	}

}
