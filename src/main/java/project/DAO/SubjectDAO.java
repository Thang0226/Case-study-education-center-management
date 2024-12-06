package project.DAO;

import project.model.Subject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SubjectDAO implements ISubjectDAO {

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public List<Subject> findAll() {
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
