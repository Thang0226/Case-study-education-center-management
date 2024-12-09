package project.service;

import project.model.Tutor;

import java.util.HashMap;

public interface ITutorService extends IService<Tutor> {
    HashMap<Integer,Integer> getStudentNumbersByTutor();

}
