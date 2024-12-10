package project.controller;

import project.model.*;
import project.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private UserService userService = new UserService();
    private IStudentService studentService = new StudentService();

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
                break;
            default:
                showHomePage(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                routeUser(req, resp);
                break;
            default:
                break;
        }
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        try{
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void routeUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<User> users = userService.findAll();
        boolean isUser = false;
        User loginUser = null;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                isUser = true;
                loginUser = user;
                break;
            }
        }
        if (isUser) {
            switch (loginUser.getRoleID()) {
                case 1:     // tutor
                    resp.sendRedirect("tutors");
                    break;
                case 2:     // officer
                    resp.sendRedirect("student/student_list.jsp?click=yes");
                    break;
                case 3:     // admin
                    resp.sendRedirect("home?click=yes");
                    break;
                case 4:     // student
                    Student student = studentService.findStudentByUserId(loginUser.getId());
                    resp.sendRedirect("students?action=view_student&id=" + student.getId());
                    break;
                default:
                    resp.sendRedirect("login.jsp?error=invalid"); // Redirect back with error
                    break;
            }
        } else {
            resp.sendRedirect("login.jsp?error=invalid"); // Redirect back with error
        }
    }
}
