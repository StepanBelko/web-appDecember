package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ActivationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        UsersDAO usersDAO = new UsersDAO();

//        Новая логика с проверкой на активированность
        if (usersDAO.getByEmail(email) != null) {
            if (!usersDAO.isActive(email)) {
                if (usersDAO.updateByEmail(email)) {
                    resp.getWriter().println("<a>Mail " + req.getParameter("email")
                            + " successfully activated.<br>"
                            + "Now you can login<br></a>");
                } else {
                    System.out.println("Something wrong");
                }
            } else {
                resp.getWriter().println("<a>Mail " + req.getParameter("email")
                        + " is already activated.<br>"
                        + "Please login<br></a>");
            }
        } else {
                        resp.getWriter().println("<a>Mail <" + req.getParameter("email")
                                + "> is not exist .<br>"
                                + "<a href='registration'>Registration</a><br></a>");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("activationPage.html");
        requestDispatcher.include(req, resp);

/*        Старая логика только с проверкой на существование и правильный апдейт
        if (usersDAO.getByEmail(email) != null && usersDAO.updateByEmail(email)) {
            resp.getWriter().println("<a>Mail " + req.getParameter("email")
                    + " successfully activated.<br>"
                    + "Now you can login<br></a>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("activationPage.html");
            requestDispatcher.include(req, resp);
        } else {
            resp.getWriter().println("<a>Mail <" + req.getParameter("email")
                    + "> was deleted from database .<br>"
                    + "<a href='registration'>Registration</a><br></a>");

        }*/
    }
}
