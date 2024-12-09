package project.service;

import project.model.Student;
import project.model.User;

public interface IUserService extends IService<User> {
    void addStudentTransaction(User user, Student student);

    void addTutorTransaction(User user);

    void deleteStudent(int id);

    void deleteTutor(int id);
}
