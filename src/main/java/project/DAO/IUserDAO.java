package project.DAO;

import project.model.Student;
import project.model.User;

public interface IUserDAO extends IDAO<User> {
    void addStudentTransaction(User user, Student student);

    void addTutorTransaction(User user);

    void deleteStudent(int id);

    void deleteTutor(int id);
}

