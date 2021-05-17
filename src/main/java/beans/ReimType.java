package beans;

public class ReimType {
	private int id;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if (type.toLowerCase() == "travel") {
			this.type = "travel";
			this.id = 1;
		} else if (type.toLowerCase() == "food") {
			this.type = "food";
			this.id = 2;
		} else if (type.toLowerCase() == "lodging") {
			this.type = "lodging";
			this.id = 3;
		} else {
			this.type = "other";
			this.id = 4;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		if (id == 1) {
			this.type = "travel";
		}else if (id == 2) {
			this.type = "food";
		} else if (id == 3) {
			this.type = "lodging";
		} else if (id == 4) {
			this.type = "other";
		} else {
			System.out.println("Invalid input. ReimbursementType Id must be 1, 2, 3, or 4.");
		}
	}

}
