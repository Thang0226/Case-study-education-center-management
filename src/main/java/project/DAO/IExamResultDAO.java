package project.DAO;

import project.model.ExamResult;

import java.util.List;

public interface IExamResultDAO extends IDAO<ExamResult> {

	ExamResult findExamResult(int sessionID, int studentID);

	boolean removeExamResult(int sessionID, int studentID);

	List<ExamResult> findStudentExamResults(int studentID);
}
