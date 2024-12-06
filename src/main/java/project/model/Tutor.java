package project.model;

public class Tutor {
	private int id;
	private int userID;
	private int classCount;
	private int studentCount;

	public Tutor(int id, int userID, int classCount, int studentCount) {
		this.id = id;
		this.userID = userID;
		this.classCount = classCount;
		this.studentCount = studentCount;
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

	public int getClassCount() {
		return classCount;
	}

	public void setClassCount(int classCount) {
		this.classCount = classCount;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
}
