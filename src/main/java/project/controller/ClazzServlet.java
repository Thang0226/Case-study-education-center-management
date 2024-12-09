package project.controller;

import project.model.DTO.StudentAvgScoreDTO;
import project.model.Subject;
import project.service.ClazzService;
import project.service.IClazzService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "ClazzServlet", urlPatterns = "/classes")
public class ClazzServlet extends HttpServlet {
    IClazzService clazzService = new ClazzService();

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
                break;
            case "delete":
                break;
            default:
                showClassList(req, resp);
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
                showClassList(req, resp);
        }
    }

    private void showClassList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentAvgScoreDTO> studentAvgScoreDTO = clazzService.getClassListWithStudentAvgScoreDTO();
        req.setAttribute("classList", studentAvgScoreDTO);
        RequestDispatcher dispatcher = req.getRequestDispatcher("class/list.jsp");
        try{
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
