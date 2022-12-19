package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.logic.ServletLogic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "positive", urlPatterns = "/positive")
public class PositiveResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<a>lo number = " + req.getParameter("loNum") + "</a><br>");
        printWriter.println("hi number = " + req.getParameter("hiNum") + "</a><br>");
        printWriter.println("random between num = " + ServletLogic.getAnswer() + "</a><br>");
        printWriter.println("<a href = 'homeWorkServlets'>Try again</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
