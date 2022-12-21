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

@WebServlet(name = "welcome", urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Welcome servlet doGet");
        System.out.println(req.getParameter("name"));
        resp.setContentType("text/html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Welcome servlet doPost");
        User user = new UsersDAO().getByEmail(req.getParameter("email"));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcomePage.html");
        resp.setContentType("text/html");
        resp.getWriter().println("<b> Welcome "+ user.getName() + "</b > <br>");
        requestDispatcher.include(req, resp);
    }
}
