package project.service;

import project.model.Student;
import project.model.DTO.StudentInformation;

import java.util.List;

public interface IStudentService extends IService<Student> {

	List<StudentInformation> findStudentByClass(String className);

	StudentInformation findStudentByID(int studentID);

//	List<Student> findStudentsByStatus(int statusId);

	Student findStudentByUserId(int userId);

	void updateStudentByUserID(Student student);

	List<StudentInformation> findAllStudents();

}
