package project.model;

public class Clazz {
	private int id;
	private String name;
	private int tutorID;
	private int subjectID;

	public Clazz(int id, String name, int tutorID, int subjectID) {
		this.id = id;
		this.name = name;
		this.tutorID = tutorID;
		this.subjectID = subjectID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTutorID() {
		return tutorID;
	}

	public void setTutorID(int tutorID) {
		this.tutorID = tutorID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
}
