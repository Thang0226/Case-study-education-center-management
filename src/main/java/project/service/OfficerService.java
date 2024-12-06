package project.service;

import project.DAO.IOfficerDAO;
import project.DAO.OfficerDAO;
import project.model.Officer;

import java.util.List;

public class OfficerService implements IOfficerService{
    IOfficerDAO officerDAO = new OfficerDAO();

    @Override
    public List<Officer> findAll() {
        return officerDAO.findAll();
    }

    @Override
    public boolean add(Officer officer) {
        return officerDAO.add(officer);
    }

    @Override
    public Officer findById(int id) {
        return officerDAO.findById(id);
    }

    @Override
    public boolean update(Officer officer) {
        return officerDAO.update(officer);
    }

    @Override
    public boolean remove(int id) {
        return officerDAO.remove(id);
    }
}
