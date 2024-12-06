package project.service;

import project.DAO.IRoleDAO;
import project.DAO.RoleDAO;
import project.model.Role;

import java.util.List;

public class RoleService implements IRoleService {
    IRoleDAO roleDAO = new RoleDAO();

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public boolean add(Role role) {
        return roleDAO.add(role);
    }

    @Override
    public Role findById(int id) {
        return roleDAO.findById(id);
    }

    @Override
    public boolean update(Role object) {
        return roleDAO.update(object);
    }

    @Override
    public boolean remove(int id) {
        return roleDAO.remove(id);
    }
}
