package project.DAO;

import project.model.Student;
import project.model.DTO.StudentInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String SELECT_BY_USER_ID = "select * from student where user_id=?";
	private static final String UPDATE_BY_USER_ID = "call update_student_by_user_id(?,?,?,?)";

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public List<StudentInformation> findStudentByStatus(String statusName) {
		return null;
	}

	@Override
	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call list_students()}")
		) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int userID = rs.getInt("user_id");
				int tuitionStatusID = rs.getInt("tuition_status_id");
				int studentStatusID = rs.getInt("student_status_id");
				int classID = rs.getInt("class_id");
				Student student = new Student(id, userID, tuitionStatusID, studentStatusID, classID);
				students.add(student);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}

	@Override
	public List<StudentInformation> findAllStudents() {
		List<StudentInformation> informationList = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_all_student_information()}")
		) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("full_name");
				String email = rs.getString("email");
				String birthDate = rs.getString("birth_date");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phone_number");
				String className = rs.getString("class");
				String tuitionStatus = rs.getString("tuition_status");
				String studentStatus = rs.getString("student_status");
				StudentInformation infor = new StudentInformation(className, id, fullName, email, birthDate,
						address, phoneNumber, tuitionStatus, studentStatus);
				informationList.add(infor);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return informationList;
	}

	@Override
	public List<StudentInformation> findStudentByClass(String className) {
		List<StudentInformation> studentInformationList = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call list_students_by_class(?)}")
		) {
			cstmt.setString(1, className);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("full_name");
				String email = rs.getString("email");
				String birthDate = rs.getString("birth_date");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phone_number");
				String tuitionStatus = rs.getString("tuition_status");
				String studentStatus = rs.getString("student_status");

				StudentInformation infor = new StudentInformation(className, id, fullName, email, birthDate,
						address, phoneNumber, tuitionStatus, studentStatus);
				studentInformationList.add(infor);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return studentInformationList;
	}

//	@Override
//	public List<StudentInformation> findStudentByStatus(String statusName) {
//		List<StudentInformation> studentInformationList = new ArrayList<>();
//		try (
//				Connection conn = getConnection();
//				CallableStatement cstmt = conn.prepareCall("{call list_students_by_status(?)}")
//		) {
//			cstmt.setString(1, statusName);
//			ResultSet rs = cstmt.executeQuery();
//			while (rs.next()){
//
//			}
//		}
//		catch (SQLException e) {
//			printSQLException(e);
//		}
//		return studentInformationList;
//	}

	@Override
	public boolean add(Student student) {
		boolean success = false;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call add_student(?,?,?,?)}")
		) {
			cstmt.setInt(1, student.getUserID());
			cstmt.setInt(2, student.getTuitionStatusID());
			cstmt.setInt(3, student.getStudentStatusID());
			cstmt.setInt(4, student.getClassID());
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Insert failed!");
			}
			success = true;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return success;
	}

	@Override
	public Student findById(int id) {
		Student student = null;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_student(?)}")
		) {
			cstmt.setInt(1, id);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				int userID = rs.getInt("user_id");
				int tuitionStatusID = rs.getInt("tuition_status_id");
				int studentStatusID = rs.getInt("student_status_id");
				int classID = rs.getInt("class_id");
				student = new Student(id, userID, tuitionStatusID, studentStatusID, classID);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	@Override
	public StudentInformation findStudentByID(int id) {
		StudentInformation studentInfor = null;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_student_information(?)}")
		) {
			cstmt.setInt(1, id);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				String className = rs.getString("class");
				String fullName = rs.getString("full_name");
				String email = rs.getString("email");
				String birthDate = rs.getString("birth_date");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phone_number");
				String tuitionStatus = rs.getString("tuition_status");
				String studentStatus = rs.getString("student_status");

				studentInfor = new StudentInformation(className, id, fullName, email, birthDate,
						address, phoneNumber,tuitionStatus, studentStatus);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return studentInfor;
	}


	@Override
	public boolean update(Student student) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call update_student(?,?,?,?,?)}")
		) {
			cstmt.setInt(1, student.getId());
			cstmt.setInt(2, student.getUserID());
			cstmt.setInt(3, student.getTuitionStatusID());
			cstmt.setInt(4, student.getStudentStatusID());
			cstmt.setInt(5, student.getClassID());
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Update failed!");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return true;
	}

	@Override
	public boolean remove(int id) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call delete_student(?)}")
		) {
			cstmt.setInt(1, id);
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Student with id " + id + " not found");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return true;
	}

	@Override
	public Student findStudentByUserId(int userId) {
		Student student = null;
		try (
				Connection conn = getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_USER_ID)
		) {
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int userID = rs.getInt("user_id");
				int tuitionStatusID = rs.getInt("tuition_status_id");
				int studentStatusID = rs.getInt("student_status_id");
				int classID = rs.getInt("class_id");
				student = new Student(userID, tuitionStatusID, studentStatusID, classID);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	@Override
	public void updateStudentByUserID(Student student) {
		try (
				Connection conn = getConnection();
				CallableStatement callableStatement = conn.prepareCall(UPDATE_BY_USER_ID)
		) {
			callableStatement.setInt(1, student.getUserID());
			callableStatement.setInt(2, student.getTuitionStatusID());
			callableStatement.setInt(3, student.getStudentStatusID());
			callableStatement.setInt(4, student.getClassID());
			callableStatement.executeUpdate();
		} catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
		}
	}

	private void printSQLException(SQLException ex) {
		PrintSQLException.printSQLException(ex);
	}
}
