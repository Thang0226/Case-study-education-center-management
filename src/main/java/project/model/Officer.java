package project.model;

public class Officer {
	private int id;
	private int userID;

	public Officer() {
	}

	public Officer(int id, int userID) {
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
