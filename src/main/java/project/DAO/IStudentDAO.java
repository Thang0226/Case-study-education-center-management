package project.DAO;

import project.model.Student;
import project.model.DTO.StudentInformation;

import java.util.List;

public interface IStudentDAO extends IDAO<Student> {

	List<StudentInformation> findStudentByClass(String className);

	StudentInformation findStudentByID(int id);

	List<StudentInformation> findStudentByStatus(int statusId);


	Student findStudentByUserId(int userId);

	void updateStudentByUserID(Student student);

	List<StudentInformation> findAllStudents();
}
