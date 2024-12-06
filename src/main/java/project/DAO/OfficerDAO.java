package project.DAO;

import project.model.Officer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfficerDAO implements IOfficerDAO{
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcUsername = "root";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcPassword = "123456";

    private static final String SELECT_ALL_OFFICERS = "SELECT * FROM officer";
    private static final String SELECT_OFFICER_BY_ID = "SELECT * FROM officer WHERE id = ?";
    private static final String SELECT_OFFICER_BY_NAME = "SELECT * FROM officer WHERE name = ?";
    private static final String INSERT_OFFICER ="INSERT INTO officer (User_ID) VALUES(?)";
    private static final String UPDATE_OFFICER ="UPDATE officer SET User_ID = ? WHERE ID = ?";
    private static final String DELETE_OFFICER = "DELETE FROM officer WHERE id = ?";

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Officer> findAll() {
        List<Officer> officers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_OFFICERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println(preparedStatement);
            while (resultSet.next()) {
                Officer officer = new Officer();
                officer.setId(resultSet.getInt("id"));
                officer.setUserID(resultSet.getInt("User_ID"));
                officers.add(officer);
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return officers;
    }

    @Override
    public boolean add(Officer officer) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_OFFICER)) {
            int userID = officer.getUserID();
            preparedStatement.setInt(1, userID);
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public Officer findById(int id) {
        Officer officer = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OFFICER_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (resultSet.next()) {
                officer = new Officer();
                officer.setId(resultSet.getInt("id"));
                officer.setUserID(resultSet.getInt("User_ID"));
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return officer;
    }

    @Override
    public boolean update(Officer officer) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_OFFICER)) {
            preparedStatement.setInt(1, officer.getUserID());
            preparedStatement.setInt(2, officer.getId());
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(int id) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_OFFICER)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
