package project.DAO;

import project.model.ExamResult;
import project.model.ExamSession;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamResultDAO implements IExamResultDAO {
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
	public List<ExamResult> findAll() {
		List<ExamResult> results = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call list_exam_results()}")
		) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int examSessionID = rs.getInt("exam_session_id");
				int studentID = rs.getInt("student_id");
				BigDecimal theoryScore = rs.getBigDecimal("theory_score");
				BigDecimal practicalScore = rs.getBigDecimal("practical_score");
				BigDecimal averageScore = rs.getBigDecimal("average_score");
				ExamResult result = new ExamResult(examSessionID, studentID, theoryScore, practicalScore, averageScore);
				results.add(result);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return results;
	}

	@Override
	public boolean add(ExamResult result) {
		boolean success = false;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call add_exam_result(?,?,?,?)}")
		) {
			cstmt.setInt(1, result.getExamSessionId());
			cstmt.setInt(2, result.getStudentId());
			cstmt.setBigDecimal(3, result.getTheoryScore());
			cstmt.setBigDecimal(4, result.getPracticalScore());
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
	public ExamResult findById(int id) {
		return null;
	}

	@Override
	public ExamResult findExamResult(int sessionID, int studentID) {
		ExamResult result = null;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_exam_result(?,?)}")
		) {
			cstmt.setInt(1, sessionID);
			cstmt.setInt(2, studentID);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				BigDecimal theoryScore = rs.getBigDecimal("theory_score");
				BigDecimal practicalScore = rs.getBigDecimal("practical_score");
				BigDecimal averageScore = rs.getBigDecimal("average_score");
				result = new ExamResult(sessionID, studentID, theoryScore, practicalScore, averageScore);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public List<ExamResult> findStudentExamResults(int studentID) {
		List<ExamResult> resultList = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_student_exam_result(?)}")
		) {
			cstmt.setInt(1, studentID);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int examSessionID = rs.getInt("exam_session_id");
				BigDecimal theoryScore = rs.getBigDecimal("theory_score");
				BigDecimal practicalScore = rs.getBigDecimal("practical_score");
				BigDecimal averageScore = rs.getBigDecimal("average_score");
				ExamResult result = new ExamResult(examSessionID, studentID, theoryScore, practicalScore, averageScore);
				resultList.add(result);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return resultList;
	}

	@Override
	public boolean update(ExamResult result) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call update_exam_result(?,?,?,?)}")
		) {
			cstmt.setInt(1, result.getExamSessionId());
			cstmt.setInt(2, result.getStudentId());
			cstmt.setBigDecimal(3, result.getTheoryScore());
			cstmt.setBigDecimal(4, result.getPracticalScore());
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
		return false;
	}

	@Override
	public boolean removeExamResult(int sessionID, int studentID) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call delete_exam_result(?,?)}")
		) {
			cstmt.setInt(1, sessionID);
			cstmt.setInt(2, studentID);
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Exam session not found!");
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
