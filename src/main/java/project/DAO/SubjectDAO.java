package project.DAO;

import project.model.Clazz;
import project.model.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements ISubjectDAO {

    private static final String SELECT_ALL_CLASSES = "SELECT * FROM clazz";
    private static final String INSERT_CLASS = "INSERT INTO clazz (name, tutor_id, subject_id) VALUES (?, ?, ?)";
    private static final String SELECT_CLASS_BY_ID = "SELECT * FROM clazz WHERE id = ?";
    private static final String UPDATE_CLASS = "UPDATE clazz SET name = ?, tutor_id = ?, subject_id = ? WHERE id = ?";
    private static final String DELETE_CLASS = "DELETE FROM clazz WHERE id = ?";
//    private static final String SELECT_CLASSES_BY_TUTOR = "SELECT * FROM clazz WHERE tutor_id = ?";
//    private static final String SELECT_CLASSES_BY_SUBJECT = "SELECT * FROM clazz WHERE subject_id = ?";
    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            throw new SQLException("JDBC Driver not found", e);
        }
        String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
        String jdbcUsername = "root";
        String jdbcPassword = "123456";
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

    }

    @Override
    public List<Subject> findAll() {
        List<Clazz> classes = new ArrayList<>();
        return List.of();
    }

    @Override
    public boolean add(Subject object) {
        return false;
    }

    @Override
    public Subject findById(int id) {
        return null;
    }

    @Override
    public boolean update(Subject object) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
