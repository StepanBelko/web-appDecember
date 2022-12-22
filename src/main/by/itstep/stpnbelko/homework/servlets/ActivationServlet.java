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
import java.io.PrintWriter;


@WebServlet(name = "activationServlet", urlPatterns = "/activate")
public class ActivationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        UsersDAO usersDAO = new UsersDAO();

        if (usersDAO.getByEmail(email) != null && usersDAO.updateByEmail(email)) {
            resp.getWriter().println("<a>Mail " + req.getParameter("email")
                    + " successfully activated.<br>"
                    + "Now you can login<br></a>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("activationPage.html");
            requestDispatcher.include(req, resp);
        } else {
            resp.getWriter().println("<a>Mail " + req.getParameter("email")
                    + " was deleted from database .<br>"
                    + "<a href='registration'>Registration</a><br></a>");
        }
    }
}
