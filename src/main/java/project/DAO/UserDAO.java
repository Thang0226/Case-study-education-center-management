package project.DAO;

import project.model.Student;
import project.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAO<T> implements IUserDAO {
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcURL = "jdbc:mysql://localhost:3306/center_management";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcUsername = "root";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcPassword = "123456";

    IStudentDAO studentDAO = new StudentDAO();
    ITutorDAO tutorDAO = new TutorDAO();

    private static final String SELECT_ALL_USERS = "SELECT * FROM user";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name = ?";
    private static final String INSERT_USER_SP ="CALL Insert_User(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_SP ="CALL Update_User(?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

    private static final String INSERT_STUDENT_SP = "CALL add_student(?,?,?,?)";
    private static final String DELETE_STUDENT_SP = "CALL delete_student_user(?)";

    private static final String INSERT_TUTOR= "INSERT INTO tutor Values(null, ?)";
    private static final String DELETE_TUTOR_SP = "CALL delete_tutor_user(?)";

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
             CallableStatement callableStatement = connection.prepareCall(INSERT_USER_SP)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), formatter);
            // Convert to java.sql.Date
            Date sqlDate = Date.valueOf(localDate);

            callableStatement.setString(1, user.getEmail());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getPhone());
            callableStatement.setString(4, user.getFullName());
            callableStatement.setDate(5, sqlDate);
            callableStatement.setString(6, user.getAddress());
            callableStatement.setString(7, user.getIdentity());
            callableStatement.setInt(8, user.getRoleID());
            System.out.println(callableStatement);
            rowUpdated = callableStatement.executeUpdate() > 0;
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

    @Override
    public void addStudentTransaction(User user, Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        CallableStatement callableStatementStudent = null;
        try {
            connection = getConnection();

            // set auto commit to false
            connection.setAutoCommit(false);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), formatter);
            // Convert to java.sql.Date
            Date sqlDate = Date.valueOf(localDate);
            callableStatement = connection.prepareCall(INSERT_USER_SP);
            callableStatement.setString(1, user.getEmail());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getPhone());
            callableStatement.setString(4, user.getFullName());
            callableStatement.setDate(5, sqlDate);
            callableStatement.setString(6, user.getAddress());
            callableStatement.setString(7, user.getIdentity());
            callableStatement.setInt(8, user.getRoleID());
            int rowAffected = callableStatement.executeUpdate();

            callableStatement.registerOutParameter(9, Types.INTEGER);
            int userId = callableStatement.getInt(9);

            if (rowAffected == 1) {
                if (user.getRoleID() == 4) {
                    callableStatementStudent = connection.prepareCall(INSERT_STUDENT_SP);
                    callableStatementStudent.setInt(1, userId);
                    callableStatementStudent.setInt(2, student.getTuitionStatusID());
                    callableStatementStudent.setInt(3, student.getStudentStatusID());
                    callableStatementStudent.setInt(4, student.getClassID());
                    callableStatementStudent.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } else {
                connection.rollback();
            }
        } catch (SQLException ex) {
            // roll back the transaction
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
            System.out.println(ex.getMessage());
            //noinspection CallToPrintStackTrace
            ex.printStackTrace();
        } finally {
            try {
                if (callableStatementStudent != null) callableStatementStudent.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addTutorTransaction(User user) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(INSERT_USER_SP);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TUTOR)) {

            connection.setAutoCommit(false);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), formatter);
            // Convert to java.sql.Date
            Date sqlDate = Date.valueOf(localDate);
            callableStatement.setString(1, user.getEmail());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getPhone());
            callableStatement.setString(4, user.getFullName());
            callableStatement.setDate(5, sqlDate);
            callableStatement.setString(6, user.getAddress());
            callableStatement.setString(7, user.getIdentity());
            callableStatement.setInt(8, user.getRoleID());
            int rowAffected = callableStatement.executeUpdate();

            callableStatement.registerOutParameter(9, Types.INTEGER);
            int userId = callableStatement.getInt(9);

            if (rowAffected == 1) {
                if (user.getRoleID() == 1) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        try (Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(DELETE_STUDENT_SP)) {
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            System.out.println(callableStatement);
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTutor(int id) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(DELETE_TUTOR_SP)) {
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            System.out.println(callableStatement);
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}

