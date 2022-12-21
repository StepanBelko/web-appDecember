package org.example.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("loginPage.html");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("loginPage.html");
        resp.setContentType("text/html");

        String password = req.getParameter("password");
        String email = req.getParameter("email").trim();

        User user = new UsersDAO().getByEmail(email);

        if (user == null) {
            resp.getWriter().println("<b> User does not exist </b > <br> <a href = 'registration'> Registration </a> <br> <a href = 'login'>Try again</a>");
            requestDispatcher.include(req, resp);
        } else {
            if (password.equals(user.getPassword())) {
                resp.getWriter().println("<b>Welcome from login servlet, </b>" + user.getName() + ", " + user.getEmail() + "<br>");
                requestDispatcher = req.getRequestDispatcher("welcome");
                requestDispatcher.include(req, resp);
            } else {
                resp.getWriter().println("<b> Wrong email or password </b > <br> <a href = 'registration'> Registration </a> <br>");
                requestDispatcher.include(req, resp);
            }
        }
    }
}
