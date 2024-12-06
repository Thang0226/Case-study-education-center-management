//package project.DAO;
//
//import project.model.ExamResult;
//import project.model.ExamSession;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExamResultDAO implements IExamResultDAO {
//	private String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
//	private String jdbcUsername = "root";
//	private String jdbcPassword = "123456";
//
//	@Override
//	public Connection getConnection() throws SQLException {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//		return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//	}
//
//	@Override
//	public List<ExamResult> findAll() {
//		List<ExamResult> results = new ArrayList<>();
//		try (
//				Connection conn = getConnection();
//				CallableStatement cstmt = conn.prepareCall("{call list_exam_results()}")
//		) {
//			ResultSet rs = cstmt.executeQuery();
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String name = rs.getString("name");
//				LocalDate examDate = rs.getDate("exam_date").toLocalDate();
//				ExamSession session = new ExamSession(id, name, examDate);
//				sessions.add(session);
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return sessions;
//	}
//
//	@Override
//	public boolean add(ExamSession session) {
//		boolean success = false;
//		try (
//				Connection conn = getConnection();
//				CallableStatement cstmt = conn.prepareCall("{call add_exam_session(?,?)}")
//		) {
//			cstmt.setString(1, session.getName());
//			cstmt.setDate(2, Date.valueOf(session.getExamDate()));
//			int rowAffected = cstmt.executeUpdate();
//			if (rowAffected == 0) {
//				throw new SQLException("Insert failed!");
//			}
//			success = true;
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return success;
//	}
//
//	@Override
//	public ExamSession findById(int id) {
//		ExamSession session = null;
//		try (
//				Connection conn = getConnection();
//				CallableStatement cstmt = conn.prepareCall("{call find_exam_session(?)}")
//		) {
//			cstmt.setInt(1, id);
//			ResultSet rs = cstmt.executeQuery();
//			if (rs.next()) {
//				String name = rs.getString("name");
//				LocalDate examDate = rs.getDate("exam_date").toLocalDate();
//				session = new ExamSession(id, name, examDate);
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return session;
//	}
//
//	@Override
//	public boolean update(ExamSession session) {
//		try (
//				Connection conn = getConnection();
//				CallableStatement cstmt = conn.prepareCall("{call update_exam_session(?,?,?)}")
//		) {
//			cstmt.setInt(1, session.getId());
//			cstmt.setString(2, session.getName());
//			cstmt.setDate(3, Date.valueOf(session.getExamDate()));
//			int rowAffected = cstmt.executeUpdate();
//			if (rowAffected == 0) {
//				throw new SQLException("Update failed!");
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return true;
//	}
//
//	@Override
//	public boolean remove(int id) {
//		try (
//				Connection conn = getConnection();
//				CallableStatement cstmt = conn.prepareCall("{call delete_exam_session(?)}")
//		) {
//			cstmt.setInt(1, id);
//			int rowAffected = cstmt.executeUpdate();
//			if (rowAffected == 0) {
//				throw new SQLException("Exam session with id " + id + " not found");
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return true;
//	}
//
//	private void printSQLException(SQLException ex) {
//		PrintSQLException.printSQLException(ex);
//	}
//}
