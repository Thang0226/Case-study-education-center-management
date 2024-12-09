package project.controller;

import project.model.DTO.StudentInformation;
import project.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
	private final IStudentService studentService = new StudentService();
	private final IClazzService clazzService = new ClazzService();
	private final IUserService userService = new UserService();
	private final IStudentStatusService studentStatusService = new StudentStatusService();
	private final ITuitionStatusService tuitionStatusService = new TuitionStatusService();
	private final IExamResultService examResultService = new ExamResultService();
	private final IExamSessionService examSessionService = new ExamSessionService();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "list_students_by_class":
				listStudentsByClass(req, resp);
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
			case "list_students_by_class":
				listStudentsByClass(req, resp);
				break;
			case "view_student":
				findStudentByID(req, resp);
				break;
			case "list_students_by_status":
				listStudentsByStatus(req, resp);
				break;
			default:
				break;
		}
	}

	private void listStudentsByClass(HttpServletRequest req, HttpServletResponse resp) {
		List<StudentInformation> studentInformationList = null;
		String className = req.getParameter("class_name");
		studentInformationList = studentService.findStudentByClass(className);
		List<StudentStatus> studentStatusList = studentStatusService.findAll();

		req.setAttribute("students", studentInformationList);
		req.setAttribute("class_name", className);
		req.setAttribute("studentStatusList", studentStatusList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("student/student_list_by_class.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void findStudentByID(HttpServletRequest req, HttpServletResponse resp) {
		StudentInformation studentInfor = null;
		int id = Integer.parseInt(req.getParameter("id"));
		studentInfor = studentService.findStudentByID(id);
		req.setAttribute("student", studentInfor);
		RequestDispatcher dispatcher = req.getRequestDispatcher("student/student_view.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listStudentsByStatus(HttpServletRequest req, HttpServletResponse resp) {

		List<StudentInformation> studentInformationList = studentService.findAllStudents();
		List<StudentStatus> studentStatusList = studentStatusService.findAll();
		List<StudentInformation> studentInformationListByStatus = new ArrayList<>();

		// khi select
		String statusName = req.getParameter("status_name");

		for(StudentInformation studentInformation : studentInformationList) {
			// trong student information xem th nao co student status = status_name -> add -> show list
			if(studentInformation.getStudentStatus().equals(statusName)) {
				studentInformationListByStatus.add(studentInformation);
			}
		}
		req.setAttribute("studentStatusList", studentStatusList);
		req.setAttribute("students", studentInformationListByStatus);

		RequestDispatcher dispatcher = req.getRequestDispatcher("student/student_list_by_class.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
