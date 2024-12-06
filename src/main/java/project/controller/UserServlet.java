package project.controller;

import project.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    IAdminService adminService = new AdminService();
    IClazzService clazzService = new ClazzService();
    IExamResultService examResultService = new ExamResultService();
    IExamSessionService examSessionService = new ExamSessionService();
    IOfficerService officerService = new OfficerService();
    IRoleService roleService = new RoleService();
    IStudentService studentService = new StudentService();
    IStudentStatusService studentStatusService = new StudentStatusService();
    ISubjectService subjectService = new SubjectService();
    ITuitionStatusService tuitionStatusService = new TuitionStatusService();
    ITutorService tutorService = new TutorService();
    IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
