package project.model;

public class User {
	private int id;
	private String email;
	private String password;
	private String re_pass;
	private String phone;
	private String fullName;
	private String dateOfBirth;
	private String address;
	private String identity;
	private int roleID;

	public User() {}

	public User(String email, String password, String phone, String fullName, String dateOfBirth, String address, String identity, int roleID) {
		this.email = email;
		this.password = password;
		this.re_pass = password;
		this.phone = phone;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.identity = identity;
		this.roleID = roleID;
	}

	public User(int id, String email, String password, String phone, String fullName,
				String dateOfBirth, String address, String identity, int roleID) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.re_pass = password;
		this.phone = phone;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.identity = identity;
		this.roleID = roleID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRe_pass() {
		return re_pass;
	}

	public void setRe_pass(String re_pass) {
		this.re_pass = re_pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
}
