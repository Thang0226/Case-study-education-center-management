package project.model;

public class Student {
	private int id;
	private int userID;
	private int tuitionStatusID;
	private int studentStatusID;
	private int classID;

	public Student(int id, int userID, int tuitionStatusID, int studentStatusID, int classID) {
		this.id = id;
		this.userID = userID;
		this.tuitionStatusID = tuitionStatusID;
		this.studentStatusID = studentStatusID;
		this.classID = classID;
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

	public int getTuitionStatusID() {
		return tuitionStatusID;
	}

	public void setTuitionStatusID(int tuitionStatusID) {
		this.tuitionStatusID = tuitionStatusID;
	}

	public int getStudentStatusID() {
		return studentStatusID;
	}

	public void setStudentStatusID(int studentStatusID) {
		this.studentStatusID = studentStatusID;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}
}
