package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registrationPage.html");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registrationPage.html");
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pwd1 = req.getParameter("password1");
        String pwd2 = req.getParameter("password2");

        User user = new UsersDAO().getByEmail(email);
        System.out.println(new UsersDAO().getByEmail(email));

        if (user == null) {

            if (pwd1.equals(pwd2)) {
                user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(pwd1);
                new UsersDAO().insert(user);
                requestDispatcher = req.getRequestDispatcher("welcome");
                resp.getWriter().println("<b>Successfully added </b>" + user.getName() + " " + user.getEmail() + "<br>");
                requestDispatcher.include(req, resp);
            } else {
                resp.getWriter().println("<a>Passwords do not match</a>");
                requestDispatcher.include(req, resp);
            }

        } else {
            System.out.println("User already exist " + user.getEmail());
            resp.getWriter().println("<b> User already exist </b > <br>");
            requestDispatcher.include(req, resp);
        }

    }
}
