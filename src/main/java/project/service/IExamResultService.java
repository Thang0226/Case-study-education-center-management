package project.service;

import project.model.ExamResult;

public interface IExamResultService extends IService<ExamResult> {
	ExamResult findExamResult(int sessionID, int studentID);

	boolean removeExamResult(int sessionID, int studentID);
}
