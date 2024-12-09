package project.service;

import project.DAO.ClazzDAO;
import project.DAO.IClazzDAO;
import project.model.Clazz;
import project.model.DTO.StudentAvgScoreDTO;

import java.util.List;

public class ClazzService implements IClazzService{
    IClazzDAO clazzDAO = new ClazzDAO();

    @Override
    public List<Clazz> findAll() {
        return clazzDAO.findAll();
    }

    @Override
    public boolean add(Clazz object) {
        return clazzDAO.add(object);
    }

    @Override
    public Clazz findById(int id) {
        return clazzDAO.findById(id);
    }

    @Override
    public boolean update(Clazz object) {
        return clazzDAO.update(object);
    }

    @Override
    public boolean remove(int id) {
        return clazzDAO.remove(id);
    }

    @Override
    public List<StudentAvgScoreDTO> getClassListWithStudentAvgScoreDTO() {
        return clazzDAO.getClassListWithStudentAvgScoreDTO();
    }
}
