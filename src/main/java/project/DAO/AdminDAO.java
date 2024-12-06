package project.DAO;

import project.model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements IAdminDAO {
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcUsername = "root";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcPassword = "123456";

    private static final String SELECT_ALL_ADMINS = "SELECT * FROM admin";
    private static final String SELECT_ADMIN_BY_ID = "SELECT * FROM admin WHERE id = ?";
    private static final String SELECT_ADMIN_BY_NAME = "SELECT * FROM admin WHERE name = ?";
    private static final String INSERT_ADMIN ="INSERT INTO admin (User_ID) VALUES(?)";
    private static final String UPDATE_ADMIN ="UPDATE admin SET User_ID = ? WHERE ID = ?";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE id = ?";

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
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMINS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println(preparedStatement);
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setUserID(resultSet.getInt("User_ID"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public boolean add(Admin admin) {
        boolean rowUpdated = false;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN)) {
                int userID = admin.getUserID();
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
    public Admin findById(int id) {
        Admin admin = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setUserID(resultSet.getInt("User_ID"));
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean update(Admin admin) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN)) {
            preparedStatement.setInt(1, admin.getUserID());
            preparedStatement.setInt(2, admin.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN)) {
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
