package project.service;

import project.DAO.ITutorDAO;
import project.DAO.TutorDAO;
import project.model.Tutor;

import java.util.List;

public class TutorService implements ITutorService {
    ITutorDAO tutorDAO = new TutorDAO();

    @Override
    public List<Tutor> findAll() {
        return tutorDAO.findAll();
    }

    @Override
    public boolean add(Tutor tutor) {
        return tutorDAO.add(tutor);
    }

    @Override
    public Tutor findById(int id) {
        return tutorDAO.findById(id);
    }

    @Override
    public boolean update(Tutor tutor) {
        return tutorDAO.update(tutor);
    }

    @Override
    public boolean remove(int id) {
        return tutorDAO.remove(id);
    }
}
