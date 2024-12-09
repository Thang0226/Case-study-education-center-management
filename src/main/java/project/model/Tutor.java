package project.model;

public class Tutor {
	private int id;
	private int userID;
	private int classCount;
	private int studentCount;


	public Tutor(int id, int userID) {
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
