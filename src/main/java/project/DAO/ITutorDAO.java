package project.DAO;

import project.model.Tutor;

import java.util.HashMap;

public interface ITutorDAO extends IDAO<Tutor>{
    HashMap<Integer,Integer> getStudentNumbersByTutor();
}
