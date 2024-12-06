package project.DAO;

import project.model.Student;
import project.model.StudentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStatusDAO implements IStudentStatusDAO {
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
	public List<StudentStatus> findAll() {
		List<StudentStatus> statusList = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call list_student_status()}")
		) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				StudentStatus status = new StudentStatus(id, name);
				statusList.add(status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return statusList;
	}

	@Override
	public boolean add(StudentStatus status) {
		boolean success = false;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call add_student_status(?)}")
		) {
			cstmt.setString(1, status.getName());
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
	public StudentStatus findById(int id) {
		StudentStatus status = null;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_student_status(?)}")
		) {
			cstmt.setInt(1, id);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				String statusName = rs.getString("name");
				status = new StudentStatus(id, statusName);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}

	@Override
	public boolean update(StudentStatus status) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call update_student_status(?,?)}")
		) {
			cstmt.setInt(1, status.getId());
			cstmt.setString(2, status.getName());
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
				CallableStatement cstmt = conn.prepareCall("{call delete_student_status(?)}")
		) {
			cstmt.setInt(1, id);
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Student Status with id " + id + " not found");
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
