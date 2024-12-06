package project.DAO;

import project.model.Tutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TutorDAO implements ITutorDAO{

    private static final String SELECT_ALL_TUTORS = "SELECT * FROM tutor";
    private static final String INSERT_TUTOR = "INSERT INTO tutor (user_id) VALUES (?)";
    private static final String SELECT_TUTOR_BY_ID = "SELECT * FROM tutor WHERE id = ?";
    private static final String UPDATE_TUTOR = "UPDATE tutor SET user_id = ? WHERE id = ?";
    private static final String DELETE_TUTOR = "DELETE FROM tutor WHERE id = ?";

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
    public List<Tutor> findAll() {
        List<Tutor> tutors = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TUTORS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int userID = rs.getInt("user_id");
                Tutor tutor = new Tutor(id, userID);
                tutors.add(tutor);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return tutors;
    }

    @Override
    public boolean add(Tutor tutor) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TUTOR)) {

            preparedStatement.setInt(1, tutor.getUserID());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Failed to add tutor");
            }
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public Tutor findById(int id) {
        Tutor tutor = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TUTOR_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("user_id");

                    tutor = new Tutor(id, userID);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return tutor;
    }

    @Override
    public boolean update(Tutor tutor) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TUTOR)) {

            preparedStatement.setInt(1, tutor.getUserID());
            preparedStatement.setInt(2, tutor.getId());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Failed to update tutor");
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TUTOR)) {

            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Tutor with id " + id + " was not found");
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
