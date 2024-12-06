package project.DAO;

import project.model.ExamResult;

public interface IExamResultDAO extends IDAO<ExamResult> {

	ExamResult findExamResult(int sessionID, int studentID);

	boolean removeExamResult(int sessionID, int studentID);
}
