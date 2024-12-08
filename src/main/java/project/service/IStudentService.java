package project.service;

import project.model.Student;
import project.model.StudentInformation;

import java.util.List;

public interface IStudentService extends IService<Student> {

	List<StudentInformation> findStudentByClass(String className);

	StudentInformation findStudentByID(int studentID);

	Student findStudentByUserId(int userId);

	void updateStudentByUserID(Student student);
}
