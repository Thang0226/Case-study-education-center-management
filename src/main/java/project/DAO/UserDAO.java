package project.DAO;

import project.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcUsername = "root";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcPassword = "123456";

    private static final String SELECT_ALL_USERS = "SELECT * FROM user";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name = ?";
    private static final String INSERT_USER_SP ="CALL Insert_User(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_SP ="CALL Update_User(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

    public UserDAO() {}

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
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println(preparedStatement);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phoneNumber"));
                user.setFullName(resultSet.getString("fullName"));
                user.setDateOfBirth(resultSet.getString("dateOfBirth"));
                user.setAddress(resultSet.getString("address"));
                user.setIdentity(resultSet.getString("identity"));
                user.setRoleID(resultSet.getInt("role_ID"));
                users.add(user);
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SP)) {
            String email = user.getEmail();
            String password = user.getPassword();
            String phoneNumber = user.getPhone();
            String fullName = user.getFullName();
            String dateOfBirth = user.getDateOfBirth();
            String address = user.getAddress();
            String identity = user.getIdentity();
            int roleID = user.getRoleID();

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, fullName);
            preparedStatement.setString(5, dateOfBirth);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, identity);
            preparedStatement.setInt(8, roleID);
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public User findById(int id) {
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNumber");
                String fullName = resultSet.getString("fullName");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                String identity = resultSet.getString("identity");
                int roleID = resultSet.getInt("role_ID");
                user = new User(id,email, password, phoneNumber, fullName, dateOfBirth, address, identity, roleID);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(UPDATE_USER_SP)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setString(5, user.getDateOfBirth());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getIdentity());
            preparedStatement.setInt(8, user.getRoleID());
            preparedStatement.setInt(9, user.getId());
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(int id) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, id);
            rowUpdated = preparedStatement.executeUpdate() > 0;
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return rowUpdated;

    }
}
