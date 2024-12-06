package project.service;

import project.DAO.ExamResultDAO;
import project.DAO.IExamResultDAO;
import project.model.ExamResult;

import java.util.List;

public class ExamResultService implements IExamResultService {
    IExamResultDAO examResultDAO = new ExamResultDAO();

    @Override
    public List<ExamResult> findAll() {
        return examResultDAO.findAll();
    }

    @Override
    public boolean add(ExamResult examResult) {
        return examResultDAO.add(examResult);
    }

    @Override
    public ExamResult findById(int id) {
        return examResultDAO.findById(id);
    }

    @Override
    public boolean update(ExamResult examResult) {
        return examResultDAO.update(examResult);
    }

    @Override
    public boolean remove(int id) {
        return examResultDAO.remove(id);
    }

    @Override
    public ExamResult findExamResult(int sessionID, int studentID) {
        return examResultDAO.findExamResult(sessionID, studentID);
    }

    @Override
    public boolean removeExamResult(int sessionID, int studentID) {
        return examResultDAO.removeExamResult(sessionID, studentID);
    }
}
