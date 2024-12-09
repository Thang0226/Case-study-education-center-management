package project.DAO;

import project.model.Clazz;
import project.model.DTO.StudentAvgScoreDTO;

import java.util.List;

public interface IClazzDAO extends IDAO<Clazz>{
    List<StudentAvgScoreDTO> getClassListWithStudentAvgScoreDTO();
}
