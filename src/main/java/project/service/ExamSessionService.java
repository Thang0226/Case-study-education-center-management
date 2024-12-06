package project.service;

import project.DAO.ExamSessionDAO;
import project.DAO.IExamSessionDAO;
import project.model.ExamSession;

import java.util.List;

public class ExamSessionService implements IExamSessionService {
    IExamSessionDAO examSessionDAO = new ExamSessionDAO();

    @Override
    public List<ExamSession> findAll() {
        return examSessionDAO.findAll();
    }

    @Override
    public boolean add(ExamSession examSession) {
        return examSessionDAO.add(examSession);
    }

    @Override
    public ExamSession findById(int id) {
        return examSessionDAO.findById(id);
    }

    @Override
    public boolean update(ExamSession examSession) {
        return examSessionDAO.update(examSession);
    }

    @Override
    public boolean remove(int id) {
        return examSessionDAO.remove(id);
    }
}
