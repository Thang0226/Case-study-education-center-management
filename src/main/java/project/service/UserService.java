package project.service;

import project.DAO.IUserDAO;
import project.DAO.UserDAO;
import project.model.Student;
import project.model.User;

import java.util.List;

public class UserService implements IUserService {
    IUserDAO userDAO = new UserDAO<>();

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean add(User user) {
        return userDAO.add(user);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean remove(int id) {
        return userDAO.remove(id);
    }

    @Override
    public void addStudentTransaction(User user, Student student) {
        userDAO.addStudentTransaction(user, student);
    }
}
