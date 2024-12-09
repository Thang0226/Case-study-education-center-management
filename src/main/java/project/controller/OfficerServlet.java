package project.controller;

import project.model.*;
import project.model.DTO.StudentInformation;
import project.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OfficerServlet", value = "/officer")
public class OfficerServlet extends HttpServlet {
	private StudentService studentService = new StudentService();
	private UserService userService = new UserService();
	private ClazzService clazzService = new ClazzService();
	private TuitionStatusService tuitionStatusService = new TuitionStatusService();
	private StudentStatusService studentStatusService = new StudentStatusService();
	private ExamResultService examResultService = new ExamResultService();
	private SubjectService subjectService = new SubjectService();
	private ExamSessionService examSessionService = new ExamSessionService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "":
				break;
			default:

				break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "list_students":
				listStudents(req, resp);
				break;
			case "edit_student":
				showEditStudentForm(req, resp);
				break;
			case "update_student":
				updateStudent(req, resp);
				break;
			case "edit_student_scores":
				showEditScoreForm(req, resp);
				break;
			case "update_student_scores":
				updateStudentScores(req, resp);
				break;
			case "add_student_scores":
				break;
			default:
				break;
		}
	}

	private void listStudents(HttpServletRequest req, HttpServletResponse resp) {
		List<StudentInformation> studentInformationList = studentService.findAllStudents();
		List<Clazz> clazzList = clazzService.findAll();
		List<StudentInformation> studentInforList = new ArrayList<>();

		String className = req.getParameter("clazz");
		boolean classFound = false;
		if (className != null) {
			for (Clazz clazz : clazzList) {
				if (className.equals(clazz.getName())) {
					classFound = true;
					break;
				}
			}
		}
		if (classFound) {
			for (StudentInformation infor : studentInformationList) {
				if (infor.getClassName().equals(className)) {
					studentInforList.add(infor);
				}
			}
			req.setAttribute("students", studentInforList);
		} else {
			req.setAttribute("students", studentInformationList);
		}
		req.setAttribute("clazzes", clazzList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("student/student_list.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showEditStudentForm(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("student_id"));
		Student student = studentService.findById(id);
		RequestDispatcher dispatcher;
		if (student == null) {
			dispatcher = req.getRequestDispatcher("error_404.jsp");
		} else {
			req.setAttribute("student", student);
			User user = userService.findById(student.getUserID());
			req.setAttribute("user", user);
			TuitionStatus tuitionStatus = tuitionStatusService.findById(student.getTuitionStatusID());
			req.setAttribute("tuition_status", tuitionStatus);

			List<StudentStatus> studentStatusList = studentStatusService.findAll();
			req.setAttribute("student_status_list", studentStatusList);
			StudentStatus studentStatus = studentStatusService.findById(student.getStudentStatusID());
			req.setAttribute("student_status", studentStatus);
			Clazz clazz = clazzService.findById(student.getClassID());
			req.setAttribute("clazz", clazz);
			dispatcher = req.getRequestDispatcher("student/student_edit.jsp");
		}
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("student_id"));
		int studentStatusID = Integer.parseInt(req.getParameter("student_status_id"));
		Student student = studentService.findById(id);
		student.setStudentStatusID(studentStatusID);
		studentService.update(student);
		req.setAttribute("student_id", id);
		req.setAttribute("message", "Student information was modified");

		showEditStudentForm(req, resp);
	}

	private void showEditScoreForm(HttpServletRequest req, HttpServletResponse resp) {
		int studentId = Integer.parseInt(req.getParameter("student_id"));
		Student student = studentService.findById(studentId);
		Clazz clazz = clazzService.findById(student.getClassID());
		Subject subject = subjectService.findById(clazz.getSubjectID());
		User user = userService.findById(student.getUserID());
		List<ExamResult> examResults = examResultService.findStudentExamResults(studentId);
		List<ExamSession> examSessions = examSessionService.findAll();

		req.setAttribute("student", student);
		req.setAttribute("clazz", clazz);
		req.setAttribute("subject", subject);
		req.setAttribute("user", user);
		req.setAttribute("sessions", examSessions);
		req.setAttribute("exam_results", examResults);
		RequestDispatcher dispatcher = req.getRequestDispatcher("student/student_score_edit.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateStudentScores(HttpServletRequest req, HttpServletResponse resp) {
		int studentID = Integer.parseInt(req.getParameter("student_id"));
		int sessionID = Integer.parseInt(req.getParameter("session_id"));
		BigDecimal theoryScore = BigDecimal.valueOf(Double.parseDouble(req.getParameter("theory_score")));
		BigDecimal practicalScore = BigDecimal.valueOf(Double.parseDouble(req.getParameter("practical_score")));
		ExamResult examResult = new ExamResult(sessionID, studentID, theoryScore, practicalScore, BigDecimal.ZERO);
		examResultService.update(examResult);

		req.setAttribute("student_id", studentID);
		req.setAttribute("message", "Student scores updated");
		showEditScoreForm(req, resp);
	}
}
