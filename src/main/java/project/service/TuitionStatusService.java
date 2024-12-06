package project.service;

import project.DAO.ITuitionStatusDAO;
import project.DAO.TuitionStatusDAO;
import project.model.TuitionStatus;

import java.util.List;

public class TuitionStatusService implements ITuitionStatusService {
    ITuitionStatusDAO tuitionStatusDAO = new TuitionStatusDAO();

    @Override
    public List<TuitionStatus> findAll() {
        return tuitionStatusDAO.findAll();
    }

    @Override
    public boolean add(TuitionStatus tuitionStatus) {
        return tuitionStatusDAO.add(tuitionStatus);
    }

    @Override
    public TuitionStatus findById(int id) {
        return tuitionStatusDAO.findById(id);
    }

    @Override
    public boolean update(TuitionStatus tuitionStatus) {
        return tuitionStatusDAO.update(tuitionStatus);
    }

    @Override
    public boolean remove(int id) {
        return tuitionStatusDAO.remove(id);
    }
}
