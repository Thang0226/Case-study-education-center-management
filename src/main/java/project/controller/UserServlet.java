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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    IClazzService clazzService = new ClazzService();
//    IExamResultService examResultService = new ExamResultService();
    IExamSessionService examSessionService = new ExamSessionService();
    IOfficerService officerService = new OfficerService();
    IRoleService roleService = new RoleService();
    IStudentService studentService = new StudentService();
    IStudentStatusService studentStatusService = new StudentStatusService();
    ITuitionStatusService tuitionStatusService = new TuitionStatusService();
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
                updateUser(req, resp);
                break;
            case "delete":
                deleteUser(req, resp);
                break;
            default:
                showList(req, resp);
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

        if (roleId == 1) {
            userService.addTutorTransaction(user);
        }

        if (roleId == 2 || roleId == 3) {
            userService.add(user);
        }
        if (roleId == 4) {
            int tuitionStatusID = Integer.parseInt(req.getParameter("tuitionStatusID"));
            int studentStatusID = Integer.parseInt(req.getParameter("studentStatusID"));
            int classID = Integer.parseInt(req.getParameter("classID"));
            Student student = new Student(tuitionStatusID, studentStatusID, classID);
            userService.addStudentTransaction(user, student);
        }

        List<Student> students = studentService.findAll();
        List<StudentStatus> studentStatuses = studentStatusService.findAll();
        List<TuitionStatus> tuitionStatuses = tuitionStatusService.findAll();
        List<Clazz> Classes = clazzService.findAll();
        List<Role> roles = roleService.findAll();

        req.setAttribute("students", students);
        req.setAttribute("studentStatuses", studentStatuses);
        req.setAttribute("tuitionStatuses", tuitionStatuses);
        req.setAttribute("Classes", Classes);
        req.setAttribute("roles", roles);

        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
        req.setAttribute("message", "New user added");
        try {
            dispatcher.forward(req, resp);

        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String fullName = req.getParameter("fullName");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String address = req.getParameter("address");
        String identity = req.getParameter("identity");

        User user = userService.findById(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setFullName(fullName);
        user.setDateOfBirth(dateOfBirth);
        user.setAddress(address);
        user.setIdentity(identity);
        userService.update(user);

        if (user.getRoleID() == 4) {
            int userID = user.getId();
            int tuitionStatusID = Integer.parseInt(req.getParameter("tuitionStatusID"));
            int studentStatusID = Integer.parseInt(req.getParameter("studentStatusID"));
            int classID = Integer.parseInt(req.getParameter("classID"));
            Student student = new Student(userID, tuitionStatusID, studentStatusID, classID);
            studentService.updateStudentByUserID(student);
        }

        Student student = studentService.findStudentByUserId(id);
        List<StudentStatus> studentStatuses = studentStatusService.findAll();
        List<TuitionStatus> tuitionStatuses = tuitionStatusService.findAll();
        List<Clazz> Classes = clazzService.findAll();

        req.setAttribute("student", student);
        req.setAttribute("studentStatuses", studentStatuses);
        req.setAttribute("tuitionStatuses", tuitionStatuses);
        req.setAttribute("Classes", Classes);
        req.setAttribute("user", user);
        req.setAttribute("message", "User was updated");
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findById(id);
        int roleId = user.getRoleID();
        if (roleId == 1) {
            userService.deleteTutor(id);
        } else if (roleId == 2 || roleId == 3) {
            userService.remove(id);
        } else if (roleId == 4) {
            userService.deleteStudent(id);
        }
//        List<User> users = userService.findAll();
//        req.setAttribute("users", users);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");

        try {
//            dispatcher.forward(req, resp);
            resp.sendRedirect("/users");
        } catch (IOException e) {
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
                showEditForm(req, resp);
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
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        List<Student> students = studentService.findAll();
        List<StudentStatus> studentStatuses = studentStatusService.findAll();
        List<TuitionStatus> tuitionStatuses = tuitionStatusService.findAll();
        List<Clazz> Classes = clazzService.findAll();

        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        req.setAttribute("students", students);
        req.setAttribute("studentStatuses", studentStatuses);
        req.setAttribute("tuitionStatuses", tuitionStatuses);
        req.setAttribute("Classes", Classes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
        try{
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findById(id);
        Student student = studentService.findStudentByUserId(id);
        List<StudentStatus> studentStatuses = studentStatusService.findAll();
        List<TuitionStatus> tuitionStatuses = tuitionStatusService.findAll();
        List<Clazz> Classes = clazzService.findAll();
        List<Role> roles = roleService.findAll();
        String formattedDateOfBirth = null;
        try {
            // Parse the date assuming the database format (e.g., "yyyy-MM-dd")
            SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust if the DB format differs
            Date parsedDate = dbFormat.parse(user.getDateOfBirth());

            // Format the date into "dd/MM/yyyy"
            SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
            formattedDateOfBirth = desiredFormat.format(parsedDate);
        } catch (ParseException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        req.setAttribute("roles", roles);
        req.setAttribute("userDoB", formattedDateOfBirth);
        req.setAttribute("user", user);
        req.setAttribute("student", student);
        req.setAttribute("studentStatuses", studentStatuses);
        req.setAttribute("tuitionStatuses", tuitionStatuses);
        req.setAttribute("Classes", Classes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
        try{
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

}
