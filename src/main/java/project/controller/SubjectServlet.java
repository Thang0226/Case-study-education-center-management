package project.controller;

import project.model.Subject;
import project.service.ISubjectService;
import project.service.SubjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "ClazzServlet", urlPatterns = "/subjects")
public class SubjectServlet extends HttpServlet {
    ISubjectService subjectService = new SubjectService();

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
                creatSubject(req, resp);
                break;
            case "delete":
                deleteSubject(req, resp);
                break;
            default:
                showSubjectList(req, resp);
        }
    }

    private void creatSubject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = new Subject();
        subject.setName(req.getParameter("subject"));
        subjectService.add(subject);

//        List<Subject>subjects = subjectService.findAll();
//        req.setAttribute("subjects", subjects);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("subject/list.jsp");
        try {
            resp.sendRedirect(req.getContextPath() + "/subjects");
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void deleteSubject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        subjectService.remove(Integer.parseInt(id));
        try {
            resp.sendRedirect(req.getContextPath() + "/subjects");
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
                break;
            default:
                showSubjectList(req, resp);
        }
    }

    private void showSubjectList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = subjectService.findAll();
        req.setAttribute("subjects", subjects);
        RequestDispatcher dispatcher = req.getRequestDispatcher("subject/list.jsp");
        try{
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
