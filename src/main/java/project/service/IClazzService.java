package project.service;

import project.model.Clazz;
import project.model.DTO.StudentAvgScoreDTO;

import java.util.List;

public interface IClazzService extends IService<Clazz> {
    List<StudentAvgScoreDTO> getClassListWithStudentAvgScoreDTO();

}
