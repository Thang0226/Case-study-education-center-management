package project.service;

import project.DAO.IStudentDAO;
import project.DAO.ISubjectDAO;
import project.DAO.SubjectDAO;
import project.model.Subject;

import java.util.List;

public class SubjectService implements ISubjectService {
    ISubjectDAO subjectDAO = new SubjectDAO();

    @Override
    public List<Subject> findAll() {
        return subjectDAO.findAll();
    }

    @Override
    public boolean add(Subject subject) {
        return subjectDAO.add(subject);
    }

    @Override
    public Subject findById(int id) {
        return subjectDAO.findById(id);
    }

    @Override
    public boolean update(Subject subject) {
        return subjectDAO.update(subject);
    }

    @Override
    public boolean remove(int id) {
        return subjectDAO.remove(id);
    }
}
