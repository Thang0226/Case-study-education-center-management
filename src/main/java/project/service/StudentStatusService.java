package project.service;

import project.DAO.IStudentStatusDAO;
import project.DAO.StudentStatusDAO;
import project.model.StudentStatus;

import java.util.List;

public class StudentStatusService implements IStudentStatusService {
    IStudentStatusDAO studentStatusDAO = new StudentStatusDAO();

    @Override
    public List<StudentStatus> findAll() {
        return studentStatusDAO.findAll();
    }

    @Override
    public boolean add(StudentStatus StudentStatus) {
        return studentStatusDAO.add(StudentStatus);
    }

    @Override
    public StudentStatus findById(int id) {
        return studentStatusDAO.findById(id);
    }

    @Override
    public boolean update(StudentStatus studentStatus) {
        return studentStatusDAO.update(studentStatus);
    }

    @Override
    public boolean remove(int id) {
        return studentStatusDAO.remove(id);
    }
}
