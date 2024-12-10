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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TutorServlet", urlPatterns = "/tutors")
public class TutorServlet extends HttpServlet {
    IUserService userService = new UserService();
    private final ITutorService tutorService = new TutorService();
    private final IClazzService clazzService = new ClazzService();
    private final ISubjectService subjectService = new SubjectService();

    private final IStudentService studentService = new StudentService();
    private final IStudentStatusService studentStatusService = new StudentStatusService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Danh sach lop hoc");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "list":
                break;
            default:
                listClasses(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Danh sach lop hoc");
        System.out.println("==========================================");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "view":
                viewDetail(req, resp);
                break;
            default:
                listClasses(req, resp);
                break;
        }
    }

    private void viewDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void listClasses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tutor> tutorList;
        String tutorIDString = req.getParameter("tutor_id");
        if (tutorIDString == null || tutorIDString.isEmpty()) {
            tutorList = tutorService.findAll();
        } else {
            int tutorID = Integer.parseInt(tutorIDString);
            Tutor tutor = tutorService.findById(tutorID);
            tutorList = new ArrayList<>();
            tutorList.add(tutor);
        }
        List<User> users = userService.findAll();
        List<Clazz> clazzList = clazzService.findAll();
        List<Subject> subjectList = subjectService.findAll();
        List<Tutor> tutors = tutorService.findAll();
        HashMap<Integer, Integer> map = tutorService.getStudentNumbersByTutor();

        req.setAttribute("users", users);
        req.setAttribute("clazzList", clazzList);
        req.setAttribute("subjects", subjectList);
        req.setAttribute("tutors", tutors);
        req.setAttribute("map", map);
        req.setAttribute("tutorList", tutorList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/tutor/class.jsp");
        try {
            dispatcher.forward(req, resp);
        }
        catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
