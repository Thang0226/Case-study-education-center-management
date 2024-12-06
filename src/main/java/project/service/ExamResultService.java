//package project.service;
//
//import project.DAO.ExamResultDAO;
//import project.DAO.IExamResultDAO;
//import project.model.ExamResult;
//
//import java.util.List;
//
//public class ExamResultService implements IExamResultService {
//    IExamResultDAO examResultDAO = new ExamResultDAO();
//
//    @Override
//    public List<ExamResult> findAll() {
//        return examResultDAO.findAll();
//    }
//
//    @Override
//    public boolean add(ExamResult examResult) {
//        return examResultDAO.add(examResult);
//    }
//
//    @Override
//    public ExamResult findById(int id) {
//        return examResultDAO.findById(id);
//    }
//
//    @Override
//    public boolean update(ExamResult examResult) {
//        return examResultDAO.update(examResult);
//    }
//
//    @Override
//    public boolean remove(int id) {
//        return examResultDAO.remove(id);
//    }
//}
