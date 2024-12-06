package project.DAO;

import project.model.Clazz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClazzDAO implements IClazzDAO{
// TODO: change clazzes to clazz????
    private static final String SELECT_ALL_CLAZZES = "SELECT * FROM clazz";
    private static final String INSERT_CLAZZ = "INSERT INTO clazz (name, tutorID, subjectID) VALUES (?, ?, ?)";
    private static final String SELECT_CLAZZ_BY_ID = "SELECT * FROM clazz WHERE id = ?";
    private static final String UPDATE_CLAZZ = "UPDATE clazz SET name = ?, tutorID = ?, subjectID = ? WHERE id = ?";
    private static final String DELETE_CLAZZ = "DELETE FROM clazz WHERE id = ?";

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
    public List<Clazz> findAll() {
        List<Clazz> clazzes = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLAZZES);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int tutorID = rs.getInt("tutorID");
                int subjectID = rs.getInt("subjectID");

                Clazz clazz = new Clazz(id, name, tutorID, subjectID);
                clazzes.add(clazz);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return clazzes;
    }

    @Override
    public boolean add(Clazz clazz) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLAZZ)) {

            preparedStatement.setString(1, clazz.getName());
            preparedStatement.setInt(2, clazz.getTutorID());
            preparedStatement.setInt(3, clazz.getSubjectID());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public Clazz findById(int id) {
        Clazz clazz = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLAZZ_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int tutorID = rs.getInt("tutorID");
                    int subjectID = rs.getInt("subjectID");

                    clazz = new Clazz(id, name, tutorID, subjectID);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return clazz;
    }

    @Override
    public boolean update(Clazz clazz) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLAZZ)) {

            preparedStatement.setString(1, clazz.getName());
            preparedStatement.setInt(2, clazz.getTutorID());
            preparedStatement.setInt(3, clazz.getSubjectID());
            preparedStatement.setInt(4, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
