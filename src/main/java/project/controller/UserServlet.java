package project.controller;

import project.model.Role;
import project.model.User;
import project.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        // Thiết lập encoding cho request
        req.setCharacterEncoding("UTF-8");
        // Thiết lập encoding cho response
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                creatUser(req, resp);
                break;
            case "edit":

                break;
            default:
        }
    }

    private void creatUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String fullName = req.getParameter("fullName");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String address = req.getParameter("address");
        String identity = req.getParameter("identity");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        User user = new User(email, password, phone, fullName, dateOfBirth, address, identity, roleId);
        userService.add(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
        req.setAttribute("message", "New user added");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thiết lập encoding cho request
        req.setCharacterEncoding("UTF-8");
        // Thiết lập encoding cho response
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                break;
            default:
                showList(req, resp);

        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");

        try{
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
        List<User> users = userService.findAll();
        try{
            req.setAttribute("users", users);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }



}
