package project.model;

public class Admin {
	private int id;
	private int userID;

	public Admin() {
	}

	public Admin(int id, int userID) {
		this.id = id;
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
