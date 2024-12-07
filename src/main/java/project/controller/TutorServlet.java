package project.controller;

import project.model.Clazz;
import project.model.Tutor;
import project.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TutorServlet", urlPatterns = "/tutors")
public class TutorServlet extends HttpServlet {
//    static {
//        Map<Integer, Tutor> tutors = new HashMap<>();
//        tutors.put(1, new Tutor(1, 123));
//        tutors.put(2, new Tutor(2, 456));
//        tutors.put(3, new Tutor(3, 789));
//        tutors.put(4, new Tutor(4, 489));
//    }

    ITutorService tutorService = new TutorService();
    IClazzService clazzService = new ClazzService();
    ISubjectService subjectService = new SubjectService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
        super.doGet(req, resp);

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
        List<Clazz> clazzList = clazzService.findAll();
        req.setAttribute("clazzList", clazzList);
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
