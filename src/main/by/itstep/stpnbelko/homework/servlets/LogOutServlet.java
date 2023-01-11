package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = ServletUtils.getSessionUser(req);
        if (user != null) {
            System.out.println(session.getId() + " session is active");
            System.out.println("Session " + session.getId() + " INVALIDATE");
            session.invalidate();
            req.getRequestDispatcher("login").forward(req, resp);
        } else {

        }
    }
}
