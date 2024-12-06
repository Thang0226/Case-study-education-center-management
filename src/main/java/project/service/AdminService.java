package project.service;

import project.DAO.AdminDAO;
import project.DAO.IAdminDAO;
import project.model.Admin;

import java.util.List;

public class AdminService implements IAdminService {
    IAdminDAO adminDAO = new AdminDAO();

    @Override
    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    @Override
    public boolean add(Admin admin) {
        return adminDAO.add(admin);
    }

    @Override
    public Admin findById(int id) {
        return adminDAO.findById(id);
    }

    @Override
    public boolean update(Admin admin) {
        return adminDAO.update(admin);
    }

    @Override
    public boolean remove(int id) {
        return adminDAO.remove(id);
    }
}
