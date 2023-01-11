package org.example.servlets;

import by.itstep.stpnbelko.homework.logic.ServletLogic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HomeWork", urlPatterns = "/homeWork")
public class HomeWorkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("homeWorkPage.html");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hiNum = req.getParameter("hiNum");
        String loNum = req.getParameter("loNum");
        String avgNum = ServletLogic.countRandom(loNum, hiNum);

        if (avgNum != ServletLogic.NEGATIVE_ANSWER) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/positive");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/homeWorkPage.html");
            resp.getWriter().println("<a><font color = red>!!! " + avgNum + " !!!</font></a>");
            requestDispatcher.include(req, resp);
        }

    }
}
