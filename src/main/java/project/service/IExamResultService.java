package project.service;

import project.model.ExamResult;

import java.util.List;

public interface IExamResultService extends IService<ExamResult> {
	ExamResult findExamResult(int sessionID, int studentID);

	boolean removeExamResult(int sessionID, int studentID);

	List<ExamResult> findStudentExamResults(int studentID);

	List<ExamResult> findExamSessionByStudent(int studentID);
}
