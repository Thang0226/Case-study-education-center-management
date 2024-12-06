package project.DAO;

import project.model.TuitionStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TuitionStatusDAO implements ITuitionStatusDAO {
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
	public List<TuitionStatus> findAll() {
		return List.of();
	}

	@Override
	public boolean add(TuitionStatus object) {
		return false;
	}

	@Override
	public TuitionStatus findById(int id) {
		return null;
	}

	@Override
	public boolean update(TuitionStatus object) {
		return false;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}
}
