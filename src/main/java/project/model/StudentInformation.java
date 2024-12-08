package project.model;

public class StudentInformation {
	private String className;
	private int id;
	private String fullName;
	private String email;
	private String dateOfBirth;
	private String address;
	private String phoneNumber;
	private String studentStatus;

	public StudentInformation(String className, int id, String fullName, String email, String dateOfBirth,
	                          String address, String phoneNumber, String studentStatus) {
		this.className = className;
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.studentStatus = studentStatus;
	}

	public String getClassName() {
		return className;
	}

	public int getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getStudentStatus() {
		return studentStatus;
	}
}
