package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doGet");
        HttpSession currentSession = req.getSession();
        User user = (User) currentSession.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("login").forward(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doPost");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("User in session : " + user.getName());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("userHomePage.jsp");
        resp.setContentType("text/html");
        requestDispatcher.forward(req, resp);
    }

}
