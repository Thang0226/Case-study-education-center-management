package project.DAO;

import project.model.Student;
import project.model.StudentInformation;

import java.util.List;

public interface IStudentDAO extends IDAO<Student> {

	List<StudentInformation> findStudentByClass(String className);

	StudentInformation findStudentByID(int id);

//	List<StudentInformation> findStudentByStatus(String studentStatus);

	Student findStudentByUserId(int userId);

	void updateStudentByUserID(Student student);

	List<StudentInformation> findAllStudents();
}
