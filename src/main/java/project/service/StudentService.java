package project.service;

import project.DAO.IStudentDAO;
import project.DAO.StudentDAO;
import project.model.Student;
import project.model.StudentInformation;

import java.util.List;

public class StudentService implements IStudentService {
    IStudentDAO studentDAO = new StudentDAO();

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public List<StudentInformation> findStudentByClass(String className) {
        return studentDAO.findStudentByClass(className);
    }

    @Override
    public boolean add(Student student) {
        return studentDAO.add(student);
    }

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public StudentInformation findStudentByID(int studentID) {
        return studentDAO.findStudentByID(studentID);
    }

    @Override
    public List<StudentInformation> findAllStudents() {
        return studentDAO.findAllStudents();
    }

    @Override
    public boolean update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public boolean remove(int id) {
        return studentDAO.remove(id);
    }
}
