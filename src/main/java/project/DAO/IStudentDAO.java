package project.DAO;

import project.model.Student;
import project.model.StudentInformation;

import java.util.List;

public interface IStudentDAO extends IDAO<Student> {

	List<StudentInformation> findStudentByClass(String className);

	StudentInformation findStudentByID(int id);

	Student findStudentByUserId(int userId);

	void updateStudentByUserID(Student student);
}
