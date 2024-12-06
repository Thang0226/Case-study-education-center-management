package project.DAO;

import project.model.Admin;

import java.sql.Connection;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public List<Admin> findAll() {
        return List.of();
    }

    @Override
    public boolean add(Admin object) {
        return false;
    }

    @Override
    public Admin findById(int id) {
        return null;
    }

    @Override
    public boolean update(Admin object) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
