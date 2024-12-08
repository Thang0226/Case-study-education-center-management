package project.DAO;

import project.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements ISubjectDAO {

    private static final String SELECT_ALL_SUBJECTS = "SELECT * FROM subject ORDER BY ID";
    private static final String INSERT_SUBJECT = "INSERT INTO subject(name) VALUES (?)";
    private static final String SELECT_SUBJECT_BY_ID = "SELECT * FROM subject WHERE id = ?";
    private static final String UPDATE_SUBJECT = "UPDATE subject SET name = ? WHERE id = ?";
    private static final String DELETE_SUBJECT = "DELETE FROM subject WHERE id = ?";
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
        List<Subject> subjects = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBJECTS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Subject subject = new Subject(id, name);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return subjects;
    }

    @Override
    public boolean add(Subject subject) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT)) {

            preparedStatement.setString(1, subject.getName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Failed to add subject");
            }
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public Subject findById(int id) {
        Subject subject = null;

        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_ID)) {

            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    String name = rs.getString("name");
                    subject = new Subject(id, name);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return subject;
    }

    @Override
    public boolean update(Subject subject) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUBJECT)) {

            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getId());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Failed to update subject");
            }
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT)) {

            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Subject with id " + id + " was not found");
            }
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
            }
        }
    }
}
