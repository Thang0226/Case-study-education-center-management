package project.DAO;

import project.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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

	private void printSQLException(SQLException ex) {
		PrintSQLException.printSQLException(ex);
	}
}
